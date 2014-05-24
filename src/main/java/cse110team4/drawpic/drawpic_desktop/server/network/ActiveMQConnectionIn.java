package cse110team4.drawpic.drawpic_desktop.server.network;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class ActiveMQConnectionIn implements ServerConnectionIn {
	
	private Session session;
	private Destination receiveQueue;
	private MessageConsumer toReceive;

	public ActiveMQConnectionIn(Session session) throws JMSException {
		this.session = session;
		
		// Create the receiver for a newly generated queue
		receiveQueue = session.createTemporaryQueue();
		toReceive = session.createConsumer(receiveQueue);
	}
}
