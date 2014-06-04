package cse110team4.drawpic.drawpic_desktop.server;

import java.util.List;

import cse110team4.drawpic.drawpic_core.player.ClientData;
import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.player.LobbySettings;

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
	 * Gets the polled lobby list from the server
	 * @return The lobby list or null if server hasn't been successfull polled
	 */
	List<Lobby> getLobbyList();
	
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
	 * @return null if lobby creation was successful, otherwise a reason for failure
	 */
	String createLobby();
	
	/**
	 * Obtains the lobby list from the server and stores it
	 * The list can be obtained from getLobbyList()
	 * @return null if lobby list retrieval was successful, otherwise a reason for failure
	 */
	 String pollLobbyList();
	
	/**
	 * Attempts to join the selected lobby
	 * If the join is successful, the game-timeline should continue on both the server and client
	 * @param lobby The lobby to join
	 * @return null if join is successful, otherwise a reason for failure
	 */
	String joinLobby(Lobby lobby);
	
	/**
	 * Leaves the current lobby
	 * @return null if successful, otherwise a reason for failure
	 */
	String leaveLobby();
	
	/**
	 * Attempts to change the settings of the current lobby
	 * @param settings The new settings to apply to the lobby
	 * @return null if successful, otherwise a reason for failure
	 */
	String changeLobbySettings(LobbySettings settings);
}
