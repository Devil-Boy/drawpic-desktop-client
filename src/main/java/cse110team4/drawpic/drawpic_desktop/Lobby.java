package cse110team4.drawpic.drawpic_desktop;

/**
 * This interface represents a game lobby that can contain a certain amount of players and has a specified host player
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public interface Lobby {
	
	/**
	 * Gets the maximum amount of players allowed in this lobby
	 * @return An integer
	 */
	public int maxPlayers();
	
	/**
	 * Gets the number of players currently in this lobby
	 * @return An integer representing the number of players
	 */
	public int numOfPlayers();
	
	/**
	 * Attempts to add the specified player to this lobby
	 * @param username The username of the player to add
	 * @return True if the lobby isn't full and the player isn't already in the lobby. False otherwise
	 */
	public boolean addPlayer(String username);
	
	/**
	 * Gets the players in this lobby
	 * @return An array of size equal to the maximum player amount containing either player names or null for missing players
	 */
	public String[] getPlayers();
	
	/**
	 * Attempts to remove a player from this lobby
	 * @param username The username of the player to remove
	 * @return True if the player could be found and the player wasn't the host. False otherwise
	 */
	public boolean removePlayer(String username);
}
