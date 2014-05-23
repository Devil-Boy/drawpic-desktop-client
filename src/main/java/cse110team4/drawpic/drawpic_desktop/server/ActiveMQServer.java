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

public class ActiveMQServer implements Server {
	
	private Connection connection;
	private Session session;
	
	private Destination serverQueue;
	private Queue clientQueue;
	
	private MessageProducer sender;
	private MessageConsumer receiver;
	
	public ActiveMQServer() {
	}

	@Override
	public void connect() throws Exception {
		connection = ActiveMQConnection.makeConnection(ActiveMQConstants.USERNAME, ActiveMQConstants.PASSWORD, ActiveMQConstants.ACTIVEMQ_URL);
		connection.start();
		
		// TODO: Add close hook to clean up on disconnect
		
		// Create the session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Get the server's queue
		serverQueue = session.createQueue(ActiveMQConstants.SERVER_QUEUE);
		
		//Creates the client queue
		clientQueue = session.createTemporaryQueue();
		
		// Create the message producer
		sender = session.createProducer(serverQueue);
		
		// Create the message receiver
		receiver = session.createConsumer(clientQueue);
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
