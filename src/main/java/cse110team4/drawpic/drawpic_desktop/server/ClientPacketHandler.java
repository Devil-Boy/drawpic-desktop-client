package cse110team4.drawpic.drawpic_desktop.server;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketHandler;
import cse110team4.drawpic.drawpic_core.protocol.packet.bidirectional.Packet0ELobbySettings;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet0BPlayerJoined;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet0DPlayerLeft;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.lobby.LobbySettingsChangedEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerJoinedLobbyEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerLeftLobbyEvent;

public class ClientPacketHandler implements PacketHandler {

	@Override
	public void handlePacket(Packet packet) {
		byte packetID = packet.getID();
		
		System.out.println("Received a packet: " + packetID);////
		
		if (packetID == 0x0B) {
			handlePlayerJoined((Packet0BPlayerJoined) packet);
		} else if (packetID == 0x0D) {
			handlePlayerLeft((Packet0DPlayerLeft) packet);
		} else if (packetID == 0x0E) {
			handleLobbySettingsChanges((Packet0ELobbySettings) packet);
		}
	}

	public void handlePlayerJoined(Packet0BPlayerJoined packet) {
		System.out.println(packet.getUsername() + " joined the lobby");////
		
		// Add the player to our lobby instance
		Lobby lobby = DesktopBeans.getContext().getBean(JMSServerConnection.class).getClientData().getLobby();
		lobby.addPlayer(packet.getUsername());
		
		// Send the event
		DesktopBeans.getContext().getBean(EventDispatcher.class).call(new PlayerJoinedLobbyEvent(lobby, packet.getUsername()));
	}
	
	public void handlePlayerLeft(Packet0DPlayerLeft packet) {
		System.out.println(packet.getUsername() + " left the lobby");////
		
		// Remove the player from the lobby instance
		Lobby lobby = DesktopBeans.getContext().getBean(JMSServerConnection.class).getClientData().getLobby();
		lobby.removePlayer(packet.getUsername());
		
		// Send the event
		DesktopBeans.getContext().getBean(EventDispatcher.class).call(new PlayerLeftLobbyEvent(lobby, packet.getUsername()));
	}
	
	public void handleLobbySettingsChanges(Packet0ELobbySettings packet) {
		// Set out settings instance
		Lobby lobby = DesktopBeans.getContext().getBean(JMSServerConnection.class).getClientData().getLobby();
		lobby.setSettings(packet.getSettings());
		
		// Send the event
		DesktopBeans.getContext().getBean(EventDispatcher.class).call(new LobbySettingsChangedEvent(lobby));
	}
}
