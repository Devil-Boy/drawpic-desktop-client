package cse110team4.drawpic.drawpic_desktop.server;

import java.util.List;

import cse110team4.drawpic.drawpic_core.player.ClientData;
import cse110team4.drawpic.drawpic_core.player.Lobby;

/**
 * This will be the interface for the client to the server
 * 
 * @author Devil Boy (Kervin Sam)
 *
 */
public interface ServerConnection {
	
	/**
	 * Gets the object storing this client's data
	 * @return The ClientData object
	 */
	ClientData getClientData();
	
	/**
	 * This method handles the initial network connection
	 * It should block until the connection is completed or times out
	 * @return null if successful, otherwise the reason for failure
	 */
	String connect();

	/**
	 * This method will attempt to log the user into the server
	 * @param username The username to login with
	 * @return null if login is successful, otherwise a reason for failure
	 */
	String login(String username);
	
	/**
	 * Tells the server to create the given lobby
	 * @param lobby The lobby to create
	 * @return null if lobby creation was successful, otherwise a reason for failure
	 */
	String createLobby(Lobby lobby);
	
	/**
	 * Fills the given list with the currently available lobbies
	 * @param lobbies The list to fill
	 * @return null if lobby creation was successful, otherwise a reason for failure
	 */
	 String getLobbies(List<Lobby> lobbies);
	
	/**
	 * Attempts to join the selected lobby
	 * If the join is successful, the game-timeline should continue on both the server and client
	 * @param lobby The lobby to join
	 * @return null if join is successful, otherwise a reason for failure
	 */
	String joinLobby(Lobby lobby);
}
