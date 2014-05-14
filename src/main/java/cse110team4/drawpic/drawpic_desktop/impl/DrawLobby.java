package cse110team4.drawpic.drawpic_desktop.impl;

import java.util.ArrayList;
import java.util.List;

import cse110team4.drawpic.drawpic_desktop.Lobby;

/**
 * This class represents a lobby for the draw game with a max of 4 players
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class DrawLobby implements Lobby {
	static final int MAX_PLAYERS = 4;
	
	/**
	 * This is the list of players in the lobby
	 * The lobby host must be at index 0
	 */
	private List<String> players;

	/**
	 * Constructs a new lobby with the given host
	 * @param host The host's username
	 */
	public DrawLobby(String host) {
		players = new ArrayList<String>(MAX_PLAYERS);
		
		players.add(host);
	}
	
	/**
	 * The max for this lobby is 4
	 * @return The integer 4
	 */
	@Override
	public int maxPlayers() {
		return MAX_PLAYERS;
	}
	
	/**
	 * Gets the number of players currently in this lobby
	 * @return An integer representing the number of players
	 */
	@Override
	public int numOfPlayers() {
		return players.size();
	}
	
	/**
	 * Attempts to add the specified player to this lobby
	 * @param username The username of the player to add
	 * @return True if the lobby isn't full and the player isn't already in the lobby. False otherwise
	 */
	@Override
	public boolean addPlayer(String username) {
		// Check if lobby is full
		if (players.size() < MAX_PLAYERS) {
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
	@Override
	public String[] getPlayers() {
		return players.toArray(new String[MAX_PLAYERS]);
	}

	/**
	 * Attempts to remove a player from this lobby
	 * @param username The username of the player to remove
	 * @return True if the player could be found and the player wasn't the host. False otherwise
	 */
	@Override
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
