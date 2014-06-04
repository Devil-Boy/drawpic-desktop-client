package cse110team4.drawpic.drawpic_desktop.server;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import cse110team4.drawpic.drawpic_core.ActiveMQConstants;
import cse110team4.drawpic.drawpic_core.player.ClientData;
import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.protocol.CorrelationIDGenerator;
import cse110team4.drawpic.drawpic_core.protocol.jms.JMSPacketSender;
import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketDistributor;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketReply;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet03Response;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet09LobbyList;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet01Connect;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet02Login;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet07CreateLobby;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet08GetLobbies;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet0AJoinLobby;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientUsernameSetEvent;

/**
 * This represents a connection to a JMS server
 *
 * @author Devil Boy (Kervin Sam)
 *
 * @category Dependency Inversion Principle
 */
public class JMSServerConnection implements ServerConnection {
	
	private static final long CONNECTION_TIMEOUT = 6;
	
	private Connection connection;
	private Session session;
	
	private Queue serverQueue;
	private Queue clientQueue;
	
	private JMSPacketSender out;
	
	private PacketDistributor packetDistributor;
	
	private ClientData clientData;
	private List<Lobby> lobbyList;
	
	public JMSServerConnection(ClientData clientData) {
		this.clientData = clientData;
	}
	
	@Override
	public ClientData getClientData() {
		return clientData;
	}
	
	@Override
	public List<Lobby> getLobbyList() {
		return lobbyList;
	}
	
	private String cID() {
		return DesktopBeans.getContext().getBean(CorrelationIDGenerator.class).randomCorrelationID();
	}

	@Override
	public String connect() {
		connection = DesktopBeans.getContext().getBean("activeMQConnection", Connection.class);
		try {
			connection.start();
			
			// TODO: Add close hook to clean up on disconnect
			
			// Create the session
			session = DesktopBeans.getContext().getBean("activeMQSession", Session.class);
			
			// Get a hold of the server's queue
			serverQueue = session.createQueue(ActiveMQConstants.SERVER_QUEUE);
			
			// Create the queue to receive messages from
			clientQueue = session.createTemporaryQueue();
			
			// Prepare the packet sender
			out = DesktopBeans.getContext().getBean("jmsPacketSender", JMSPacketSender.class);
			out.setProducer(session.createProducer(serverQueue));
			out.setReplyTo(clientQueue);
			
			// Prepare the packet receiver
			session.createConsumer(clientQueue).setMessageListener(DesktopBeans.getContext().getBean(JMSClientMessageListener.class));
			
			packetDistributor = DesktopBeans.getContext().getBean(JMSClientMessageListener.class).getDistributor();
			packetDistributor.addPacketHandler(DesktopBeans.getContext().getBean(ClientPacketHandler.class));
		} catch (JMSException e) {
			return "Failed to initialize ActiveMQ";
		}
		
		
		// Prepare the first packet
		Packet connectPacket = new Packet01Connect();
		connectPacket.setCorrelationID(cID());
		
		// Prepare for a reply
		PacketReply<Packet03Response> reply = new PacketReply<Packet03Response>(packetDistributor, (byte) 0x03, connectPacket.getCorrelationID());
		
		// Send the packet
		try {
			out.sendPacket(connectPacket);
		} catch (Exception e) {
			return "Could not send initial connection packet";
		}
		
		// Wait for the reply
		try {
			reply.get(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			return "Interrupted while waiting for response";
		} catch (ExecutionException e) {
			return "Error while attempting to get response: " + e.getCause().getMessage();
		} catch (TimeoutException e) {
			return "Connection timed out after " + CONNECTION_TIMEOUT + " seconds";
		}
		
		return null;
	}

	@Override
	public String login(String username) {
		// Prepare the login packet
		Packet loginPacket = new Packet02Login(username);
		loginPacket.setCorrelationID(cID());
		
		// Prepare for the reply
		PacketReply<Packet03Response> reply = new PacketReply<Packet03Response>(packetDistributor, (byte) 0x03, loginPacket.getCorrelationID());
		
		// Send the login packet
		try {
			out.sendPacket(loginPacket);
		} catch (Exception e) {
			return "Could not send login packet";
		}
		
		// Wait for the reply
		try {
			Packet03Response loginResult = reply.get(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
			if (loginResult.getSuccess()) {
				clientData.setUsername(username);
				DesktopBeans.getContext().getBean(EventDispatcher.class).call(new ClientUsernameSetEvent(clientData));
				return null;
			} else {
				return loginResult.getFailReason();
			}
		} catch (TimeoutException e) {
			return "Connection timed out after " + CONNECTION_TIMEOUT + " seconds";
		} catch (InterruptedException e) {
			return "Interrupted while waiting for response";
		} catch (ExecutionException e) {
			return "Error while attempting to get response: " + e.getCause().getMessage();
		}
	}

	@Override
	public String createLobby() {
		// Prepare packet
		Packet lobbyPacket = new Packet07CreateLobby();
		lobbyPacket.setCorrelationID(cID());
		
		// Prepare for the reply
		PacketReply<Packet03Response> reply = new PacketReply<Packet03Response>(packetDistributor, (byte) 0x03, lobbyPacket.getCorrelationID());
		
		// Send the packet
		try {
			out.sendPacket(lobbyPacket);
		} catch (Exception e) {
			return "Could not send packet to create lobby";
		}
		
		// Wait for the reply
		try {
			Packet03Response lobbyResult = reply.get(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
			if (lobbyResult.getSuccess()) {
				// Create our lobby instance
				Lobby newLobby = DesktopBeans.getContext().getBean("defaultFourPlayerLobby", Lobby.class);
				newLobby.setHost(clientData.getUsername());
				clientData.setLobby(newLobby);
				
				// Send the event
				DesktopBeans.getContext().getBean(EventDispatcher.class).call(new ClientLobbySetEvent(clientData));
				
				return null;
			} else {
				return lobbyResult.getFailReason();
			}
		} catch (TimeoutException e) {
			return "Connection timed out after " + CONNECTION_TIMEOUT + " seconds";
		} catch (InterruptedException e) {
			return "Interrupted while waiting for response";
		} catch (ExecutionException e) {
			return "Error while attempting to get response: " + e.getCause().getMessage();
		}
	}

	@Override
	public String pollLobbyList() {
		// Prepare packet
		Packet lobbyPacket = new Packet08GetLobbies();
		lobbyPacket.setCorrelationID(cID());
		
		// Prepare for the reply
		PacketReply<Packet09LobbyList> reply = new PacketReply<Packet09LobbyList>(packetDistributor, (byte) 0x09, lobbyPacket.getCorrelationID());
		
		// Send the packet
		try {
			out.sendPacket(lobbyPacket);
		} catch (Exception e) {
			return "Could not send packet to create lobby";
		}
		
		// Wait for the reply
		try {
			Packet09LobbyList lobbyResult = reply.get(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
			lobbyList = lobbyResult.getLobbies();
			return null;
		} catch (TimeoutException e) {
			return "Connection timed out after " + CONNECTION_TIMEOUT + " seconds";
		} catch (InterruptedException e) {
			return "Interrupted while waiting for response";
		} catch (ExecutionException e) {
			return "Error while attempting to get response: " + e.getCause().getMessage();
		}
	}

	@Override
	public String joinLobby(Lobby lobby) {
		// Prepare packet
		Packet lobbyPacket = new Packet0AJoinLobby(lobby);
		lobbyPacket.setCorrelationID(cID());
		
		// Prepare for the reply
		PacketReply<Packet03Response> reply = new PacketReply<Packet03Response>(packetDistributor, (byte) 0x03, lobbyPacket.getCorrelationID());
		
		// Send the packet
		try {
			out.sendPacket(lobbyPacket);
		} catch (Exception e) {
			return "Could not send packet to create lobby";
		}
		
		// Wait for the reply
		try {
			Packet03Response lobbyResult = reply.get(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
			if (lobbyResult.getSuccess()) {
				// Set the lobby we're in
				clientData.setLobby(lobby);
				
				// Send the event
				DesktopBeans.getContext().getBean(EventDispatcher.class).call(new ClientLobbySetEvent(clientData));
				
				return null;
			} else {
				return lobbyResult.getFailReason();
			}
		} catch (TimeoutException e) {
			return "Connection timed out after " + CONNECTION_TIMEOUT + " seconds";
		} catch (InterruptedException e) {
			return "Interrupted while waiting for response";
		} catch (ExecutionException e) {
			return "Error while attempting to get response: " + e.getCause().getMessage();
		}
	}

	@Override
	public String leaveLobby() {
		// TODO Auto-generated method stub
		return null;
	}

}
