package cse110team4.drawpic.drawpic_desktop.server;

import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketHandler;
import cse110team4.drawpic.drawpic_core.protocol.packet.bidirectional.Packet01Connect;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet03Response;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.Packet02Login;

public class ClientPacketHandler implements PacketHandler {
	
	public ClientPacketHandler() {
		
	}

	@Override
	public void handlePacket(Packet packet) {
		byte packetID = packet.getID();
		
		if (packetID == 0x01) {
			Packet01Connect p = (Packet01Connect) packet;
		} else if (packetID == 0x02) {
			Packet02Login p = (Packet02Login) packet;
		} else if (packetID == 0x03) {
			Packet03Response p = (Packet03Response) packet;
		} else {
			// An unhandled packet arrived
		}
	}

}
