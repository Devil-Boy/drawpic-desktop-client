package cse110team4.drawpic.drawpic_desktop.event.client;

import cse110team4.drawpic.drawpic_core.player.ClientData;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;

public class ClientUsernameSetEvent extends ClientEvent {

	public ClientUsernameSetEvent(ClientData clientData) {
		super(clientData);
	}
	
	/**
	 * Gets the newly set username
	 * @return THe username set to
	 */
	public String getUsername() {
		return getClientData().getUsername();
	}

	@Override
	public void notify(ClientListener listener) {
		listener.usernameSet(this);
	}
}
