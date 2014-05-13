package cse110.team4.drawpic.drawpic_desktop_client;

import java.util.ArrayList;
import java.util.List;

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
	private List<String> players;

	/**
	 * Constructs a new lobby with the given host
	 * @param host The host's username
	 */
	public Lobby(String host) {
		players = new ArrayList<String>(4);
		
		players.add(host);
	}
	
	/**
	 * Gets the number of players currently in this lobby
	 * @return An integer representing the number of players
	 */
	public int numOfPlayers() {
		return players.size();
	}
	
	/**
	 * Attempts to add the specified player to this lobby
	 * @param username The username of the player to add
	 * @return True if the lobby isn't full and the player isn't already in the lobby. False otherwise
	 */
	public boolean addPlayer(String username) {
		// Check if lobby is full
		if (players.size() < 4) {
			// Check if the user is already in the lobby
			if (!players.contains(username)) {
				players.add(username);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the players in this lobby
	 * @return A 4-element array containing either player names or null for missing players
	 */
	public String[] getPlayers() {
		return players.toArray(new String[4]);
	}
	
	/**
	 * Attempts to remove a player from this lobby
	 * @param username The username of the player to remove
	 * @return True if the player could be found and the player wasn't the host. False otherwise
	 */
	public boolean removePlayer(String username) {
		// Check that the specified player is in the lobby
		if (players.contains(username)) {
			// Make sure they aren't removing the host
			if (!username.equals(players.get(0))) {
				players.remove(username);
				return true;
			}
		}
		return false;
	}
}
