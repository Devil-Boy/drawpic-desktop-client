package cse110team4.drawpic.drawpic_desktop.event.client;

import cse110team4.drawpic.drawpic_core.player.ClientData;
import cse110team4.drawpic.drawpic_desktop.event.Event;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;

public abstract class ClientEvent implements Event<ClientListener> {

	private ClientData clientData;

	public ClientEvent(ClientData clientData) {
		this.clientData = clientData;
	}
	
	/**
	 * Gets the client data involved in this event
	 * @return The client data
	 */
	public ClientData getClientData() {
		return clientData;
	}
}
