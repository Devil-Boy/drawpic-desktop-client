package cse110team4.drawpic.drawpic_desktop.event.lobby;

import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_desktop.event.Event;
import cse110team4.drawpic.drawpic_desktop.event.listener.LobbyListener;

/**
 * This represents an event relating to the game lobby
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public abstract class LobbyEvent implements Event<LobbyListener> {

	private Lobby lobby;
	
	public LobbyEvent(Lobby lobby) {
		this.lobby = lobby;
	}
	
	/**
	 * Gets the lobby involved in this event
	 * @return The lobby
	 */
	public Lobby getLobby() {
		return lobby;
	}
}
