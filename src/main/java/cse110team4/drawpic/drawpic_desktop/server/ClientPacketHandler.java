package cse110team4.drawpic.drawpic_desktop.server;

import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketHandler;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet0BPlayerJoined;

public class ClientPacketHandler implements PacketHandler {

	@Override
	public void handlePacket(Packet packet) {
		byte packetID = packet.getID();
		
		System.out.println("Received a packet: " + packetID);
		
		if (packetID == 0x0B) {
			handlePlayerJoined((Packet0BPlayerJoined) packet);
		}
	}

	public void handlePlayerJoined(Packet0BPlayerJoined packet) {
		System.out.println(packet.getUsername() + " joined the lobby");
	}
}
