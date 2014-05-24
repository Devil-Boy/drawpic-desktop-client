package cse110team4.drawpic.drawpic_desktop.server;

import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;

import cse110team4.drawpic.drawpic_core.ActiveMQConstants;
import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_core.network.PacketReceiver;
import cse110team4.drawpic.drawpic_core.network.PacketSender;
import cse110team4.drawpic.drawpic_core.network.jms.JMSPacketReceiver;
import cse110team4.drawpic.drawpic_core.network.jms.JMSPacketSender;

public class JMSServer implements Server {
	
	private Connection connection;
	private Session session;
	
	private Queue serverQueue;
	private Queue clientQueue;
	
	private JMSPacketSender out;
	private JMSPacketReceiver in;
	
	public JMSServer() {
	}

	@Override
	public void connect() throws Exception {
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
		in.setPacketHandler(null); // TODO: Set this to an actual packet handler
	}

	@Override
	public String login(String username) {
		// TODO Auto-generated method stub
		return null;
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
