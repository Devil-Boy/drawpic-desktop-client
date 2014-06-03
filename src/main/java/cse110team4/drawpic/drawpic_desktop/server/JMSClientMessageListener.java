package cse110team4.drawpic.drawpic_desktop.server;

import javax.jms.Destination;

import cse110team4.drawpic.drawpic_core.protocol.jms.JMSMessageListener;
import cse110team4.drawpic.drawpic_core.protocol.jms.JMSStreamReader;
import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketDistributor;

public class JMSClientMessageListener extends JMSMessageListener {
	
	private PacketDistributor distributor;

	public JMSClientMessageListener(JMSStreamReader reader, PacketDistributor distributor) {
		super(reader);
		
		this.distributor = distributor;
	}
	
	public PacketDistributor getDistributor() {
		return distributor;
	}

	@Override
	public void packetReceived(Packet packet, Destination from) {
		distributor.distributePacket(packet);
	}

}
