package cse110team4.drawpic.drawpic_desktop.server;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.jms.Connection;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;

import cse110team4.drawpic.drawpic_core.ActiveMQConstants;
import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_core.protocol.CorrelationIDFactory;
import cse110team4.drawpic.drawpic_core.protocol.jms.JMSPacketReceiver;
import cse110team4.drawpic.drawpic_core.protocol.jms.JMSPacketSender;
import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketHandler;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketReply;
import cse110team4.drawpic.drawpic_core.protocol.packet.bidirectional.Packet01Connect;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet03LoginResponse;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet02Login;

public class JMSServerConnection implements ServerConnection {
	
	private static final long CONNECT_TIMEOUT = 6;
	private static final long LOGIN_TIMEOUT = 6;
	
	private Thread mainThread;
	
	private Connection connection;
	private Session session;
	
	private Queue serverQueue;
	private Queue clientQueue;
	
	private JMSPacketSender out;
	private JMSPacketReceiver in;
	
	private PacketHandler packetHandler;
	
	private boolean isConnected;
	
	public JMSServerConnection() {
	}
	
	public Thread getMainThread() {
		return mainThread;
	}

	@Override
	public void connect() throws Exception {
		mainThread = Thread.currentThread();
		
		connection = ActiveMQConnection.makeConnection(ActiveMQConstants.USERNAME, ActiveMQConstants.PASSWORD, ActiveMQConstants.ACTIVEMQ_URL);
		connection.start();
		
		// TODO: Add close hook to clean up on disconnect
		
		// Create the session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Get a hold of the server's queue
		serverQueue = session.createQueue(ActiveMQConstants.SERVER_QUEUE);
		
		// Create the queue to receive messages from
		clientQueue = session.createTemporaryQueue();
		
		// Prepare the packet sender
		out = new JMSPacketSender(session, serverQueue);
		out.setReplyTo(clientQueue);
		
		// Prepare the packet receiver
		in = new JMSPacketReceiver(session, clientQueue);
		in.addPacketHandler(packetHandler = new ClientPacketHandler());
		
		// Prepare the first packet
		Packet connectPacket = new Packet01Connect();
		connectPacket.setCorrelationID(CorrelationIDFactory.getFactory().randomCorrelationID());
		
		// Prepare for a reply
		PacketReply<Packet01Connect> reply = new PacketReply<Packet01Connect>(in, (byte) 0x01, connectPacket.getCorrelationID());
		
		// Send the packet
		out.sendPacket(connectPacket);
		
		// Wait for the reply
		reply.get(CONNECT_TIMEOUT, TimeUnit.SECONDS);
	}

	@Override
	public String login(String username) {
		// Prepare the login packet
		Packet loginPacket = new Packet02Login(username);
		loginPacket.setCorrelationID(CorrelationIDFactory.getFactory().randomCorrelationID());
		
		// Prepare for the reply
		PacketReply<Packet03LoginResponse> reply = new PacketReply<Packet03LoginResponse>(in, (byte) 0x03, loginPacket.getCorrelationID());
		
		// Send the login packet
		try {
			out.sendPacket(loginPacket);
		} catch (Exception e) {
			return e.getMessage();
		}
		
		// Wait for the reply
		try {
			Packet03LoginResponse loginResult = reply.get(LOGIN_TIMEOUT, TimeUnit.SECONDS);
			if (loginResult.getLoginSuccess()) {
				return null;
			} else {
				return loginResult.getFailReason();
			}
		} catch (TimeoutException e) {
			return e.getMessage();
		} catch (InterruptedException | ExecutionException e) {
			throw new UnexpectedServerException(e);
		}
	}

	@Override
	public Lobby createLobby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> openLobbies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lobby getLobby(String host) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String joinLobby(Lobby lobby) {
		// TODO Auto-generated method stub
		return null;
	}

}
