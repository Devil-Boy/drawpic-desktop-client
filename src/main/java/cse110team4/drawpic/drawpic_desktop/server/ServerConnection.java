package cse110team4.drawpic.drawpic_desktop.server;

import java.util.List;

import cse110team4.drawpic.drawpic_core.Lobby;

/**
 * This will be the interface for the client to the server
 * 
 * @author Devil Boy (Kervin Sam)
 *
 */
public interface ServerConnection {
	
	/**
	 * This method handles the initial network connection
	 * It should block until the connection is completed or times out
	 * @throws Exception if there is a connection issue or timeout
	 */
	void connect() throws Exception;

	/**
	 * This method can be used to check if a username is available
	 * If the given username is available, the game-timeline should continue on both the server and client
	 * @param username The username to login with
	 * @return null if login is successful, otherwise a reason for failure
	 */
	String login(String username);
	
	/**
	 * Given the timeline is correct, this method should obtain a new lobby from the server
	 * @return The newly created lobby
	 * @throws UnexpectedServerException If an unexpected error occurs
	 */
	Lobby createLobby();
	
	/**
	 * This will return a list of currently open lobbies (identified by the hosts' usernames)
	 * @return A list of lobby host usernames
	 * @throws UnexpectedServerException If an unexpected error occurs
	 */
	List<String> openLobbies();
	
	/**
	 * This will return the lobby hosted by the given username
	 * @param host The username of the lobby host
	 * @return The lobby object of the specified host
	 * @throws UnexpectedServerException If an unexpected error occurs
	 */
	Lobby getLobby(String host);
	
	/**
	 * Attempts to join the selected lobby
	 * If the join is successful, the game-timeline should continue on both the server and client
	 * @param lobby The lobby to join
	 * @return null if join is successful, otherwise a reason for failure
	 */
	String joinLobby(Lobby lobby);
}
