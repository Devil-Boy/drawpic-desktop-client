package cse110team4.drawpic.drawpic_desktop.event.server;

import java.util.List;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.event.listener.ServerListener;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public class ServerLobbyListSetEvent extends ServerEvent {

	public ServerLobbyListSetEvent(ServerConnection connection) {
		super(connection);
	}

	/**
	 * Gets the newly set lobby list
	 * @return The lobby list object set
	 */
	public List<Lobby> getLobbyList() {
		return getServerConnection().getLobbyList();
	}

	@Override
	public void notify(ServerListener listener) {
		listener.lobbyListSet(this);
	}
}
