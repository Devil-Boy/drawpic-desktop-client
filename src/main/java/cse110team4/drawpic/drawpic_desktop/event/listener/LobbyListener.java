package cse110team4.drawpic.drawpic_desktop.event.listener;

import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerJoinedLobbyEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerLeftLobbyEvent;

public interface LobbyListener {

	public void playerJoinedLobby(PlayerJoinedLobbyEvent event);
	
	public void playerLeftLobby(PlayerLeftLobbyEvent event);
}
