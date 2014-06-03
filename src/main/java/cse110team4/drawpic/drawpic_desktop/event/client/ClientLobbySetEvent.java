package cse110team4.drawpic.drawpic_desktop.event.client;

import cse110team4.drawpic.drawpic_core.player.ClientData;
import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;

public class ClientLobbySetEvent extends ClientEvent {

	public ClientLobbySetEvent(ClientData clientData) {
		super(clientData);
	}
	
	/**
	 * Gets the newly set lobby
	 * @return The lobby object set to
	 */
	public Lobby getLobby() {
		return getClientData().getLobby();
	}

	@Override
	public void notify(ClientListener listener) {
		listener.lobbySet(this);
	}

}
