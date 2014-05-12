package cse110.team4.drawpic.drawpic_desktop_client;

/**
 * This class represents a game lobby that contains several players
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class Lobby {
	
	/**
	 * This is the list of players in the lobby
	 * The lobby host must be at index 0
	 */
	String[] players = new String[4];

	public Lobby(String host) {
		players[0] = host;
	}
}
