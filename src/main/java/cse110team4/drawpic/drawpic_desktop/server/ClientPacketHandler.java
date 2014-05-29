package cse110team4.drawpic.drawpic_desktop.server;

import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketHandler;
import cse110team4.drawpic.drawpic_core.protocol.packet.bidirectional.PacketConnect;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.PacketLoginResponse;
import cse110team4.drawpic.drawpic_core.protocol.packet.serverbound.PacketLogin;

public class ClientPacketHandler implements PacketHandler {
	
	private ResponseNotifier responseNotifier;
	
	public ClientPacketHandler() {
		
	}

	@Override
	public void handlePacket(Packet packet) {
		byte packetID = packet.getID();
		
		if (responseNotifier != null && responseNotifier.getResponseID() == packetID) {
			responseNotifier.receivedResponse(packet);
		}
		
		if (packetID == 0x01) {
			PacketConnect p = (PacketConnect) packet;
		} else if (packetID == 0x02) {
			PacketLogin p = (PacketLogin) packet;
		} else if (packetID == 0x03) {
			PacketLoginResponse p = (PacketLoginResponse) packet;
		} else {
			// An unhandled packet arrived
		}
	}

}
