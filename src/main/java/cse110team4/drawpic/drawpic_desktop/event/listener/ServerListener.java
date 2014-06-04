package cse110team4.drawpic.drawpic_desktop.event.listener;

import cse110team4.drawpic.drawpic_desktop.event.server.ServerLobbyListSetEvent;

public interface ServerListener {

	public void lobbyListSet(ServerLobbyListSetEvent event);
}
