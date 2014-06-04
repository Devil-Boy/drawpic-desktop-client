package cse110team4.drawpic.drawpic_desktop.server;

import cse110team4.drawpic.drawpic_core.gamesession.GamePhase;
import cse110team4.drawpic.drawpic_core.player.GameData;
import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;
import cse110team4.drawpic.drawpic_core.protocol.packet.PacketHandler;
import cse110team4.drawpic.drawpic_core.protocol.packet.bidirectional.Packet0ELobbySettings;
import cse110team4.drawpic.drawpic_core.protocol.packet.bidirectional.Packet0FStartGame;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet0BPlayerJoined;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet0DPlayerLeft;
import cse110team4.drawpic.drawpic_core.protocol.packet.clientbound.Packet10SetJudge;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.game.GameJudgeSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.game.GamePhaseSetEvent;
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
		} else if (packetID == 0x0F) {
			handleGameStart((Packet0FStartGame) packet);
		} else if (packetID == 0x10) {
			handleJudgeSet((Packet10SetJudge) packet);
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
	
	public void handleGameStart(Packet0FStartGame packet) {
		// Set the game phase
		GameData gameData = DesktopBeans.getContext().getBean(JMSServerConnection.class).getGameData();
		gameData.setPhase(GamePhase.DRAW_PHASE);
		
		// Send the event
		DesktopBeans.getContext().getBean(EventDispatcher.class).call(new GamePhaseSetEvent(gameData));
	}
	
	public void handleJudgeSet(Packet10SetJudge packet) {
		// Set the game judge
		GameData gameData = DesktopBeans.getContext().getBean(JMSServerConnection.class).getGameData();
		gameData.setJudge(packet.getJudge());
		
		// Send the event
		DesktopBeans.getContext().getBean(EventDispatcher.class).call(new GameJudgeSetEvent(gameData));
	}
}
