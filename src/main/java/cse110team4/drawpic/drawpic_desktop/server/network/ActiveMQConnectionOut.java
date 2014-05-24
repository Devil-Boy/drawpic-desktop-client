package cse110team4.drawpic.drawpic_desktop.server.network;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;

import cse110team4.drawpic.drawpic_core.ActiveMQConstants;
import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;

/**
 * This handles the sending of packets to the server through ActiveMQ
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class ActiveMQConnectionOut implements ServerConnectionOut {
	
	private Session session;
	private Destination serverQueue;
	private MessageProducer toServer;
	
	/**
	 * Creates a new instance of this class that will send messages through the given session
	 * @param session The session to send messages through
	 * @throws JMSException if there is an issue with initialization
	 */
	public ActiveMQConnectionOut(Session session) throws JMSException {
		this.session = session;
		
		// Create the message sender with the server's queue
		serverQueue = session.createQueue(ActiveMQConstants.SERVER_QUEUE);
		toServer = session.createProducer(serverQueue);
	}

	@Override
	public void sendPacket(Packet packet) throws JMSException {
		StreamMessage message = session.createStreamMessage();
		packet.writeToMessage(message);
		toServer.send(message);
	}
}
