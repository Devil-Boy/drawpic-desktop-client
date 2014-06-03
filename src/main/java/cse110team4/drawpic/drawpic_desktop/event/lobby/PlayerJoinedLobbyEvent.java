package cse110team4.drawpic.drawpic_desktop.event.lobby;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.event.listener.LobbyListener;

/**
 * This event is called whenever a player joins the game lobby
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class PlayerJoinedLobbyEvent extends LobbyEvent {

	private String player;
	
	public PlayerJoinedLobbyEvent(Lobby lobby, String player) {
		super(lobby);
		
		this.player = player;
	}
	
	/**
	 * Gets the player who joined the lobby
	 * @return The player who joined
	 */
	public String getPlayer() {
		return player;
	}
	
	@Override
	public void notify(final LobbyListener listener) {
		listener.playerJoinedLobby(this);
	}
}
