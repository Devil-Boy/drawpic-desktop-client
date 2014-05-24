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
import cse110team4.drawpic.drawpic_desktop.server.network.ActiveMQConnectionOut;
import cse110team4.drawpic.drawpic_desktop.server.network.ServerConnectionIn;
import cse110team4.drawpic.drawpic_desktop.server.network.ServerConnectionOut;

public class ActiveMQServer implements Server {
	
	private Connection connection;
	private Session session;
	
	private ServerConnectionOut out;
	private ServerConnectionIn in;
	
	public ActiveMQServer() {
	}

	@Override
	public void connect() throws Exception {
		connection = ActiveMQConnection.makeConnection(ActiveMQConstants.USERNAME, ActiveMQConstants.PASSWORD, ActiveMQConstants.ACTIVEMQ_URL);
		connection.start();
		
		// TODO: Add close hook to clean up on disconnect
		
		// Create the session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Initialize the outbound handler
		out = new ActiveMQConnectionOut(session);
		
		// Initialize the inbound handler
		
		
		/*
		
		//Creates the client queue
		clientQueue = session.createTemporaryQueue();
		
		// Create the message receiver
		receiver = session.createConsumer(clientQueue);*/
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
