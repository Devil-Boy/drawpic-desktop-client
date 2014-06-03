package cse110team4.drawpic.drawpic_desktop.event.listener;

import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientUsernameSetEvent;

public interface ClientListener {

	public void usernameSet(ClientUsernameSetEvent event);
	
	public void lobbySet(ClientLobbySetEvent event);
}
