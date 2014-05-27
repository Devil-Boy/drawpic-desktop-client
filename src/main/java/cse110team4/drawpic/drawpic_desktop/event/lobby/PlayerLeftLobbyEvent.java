package cse110team4.drawpic.drawpic_desktop.event.lobby;

import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_desktop.event.listener.LobbyListener;

/**
 * This event is called whenever a player leaves the game lobby
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class PlayerLeftLobbyEvent extends LobbyEvent {

	private String player;
	
	public PlayerLeftLobbyEvent(Lobby lobby, String player) {
		super(lobby);
		
		this.player = player;
	}
	
	/**
	 * Gets the player who left the lobby
	 * @return The player who left
	 */
	public String getPlayer() {
		return player;
	}
	
	@Override
	public void notify(final LobbyListener listener) {
		listener.playerLeftLobby(this);
	}
}
