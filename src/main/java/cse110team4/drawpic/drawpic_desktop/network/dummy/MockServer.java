package cse110team4.drawpic.drawpic_desktop.network.dummy;

import java.util.ArrayList;
import java.util.List;

import cse110team4.drawpic.drawpic_desktop.DrawLobby;
import cse110team4.drawpic.drawpic_desktop.Lobby;
import cse110team4.drawpic.drawpic_desktop.network.Server;

/**
 * This is just a mock class that allows us to test other parts of the program
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class MockServer implements Server {
	String username;
	
	List<String> mockLobbies;

	@Override
	public void connect() throws Exception {
		// Do nothing
	}

	@Override
	public String login(String username) {
		// Store the username
		this.username = username;
		
		// Return successful
		return null;
	}

	@Override
	public Lobby createLobby() {
		return new DrawLobby(username);
	}

	@Override
	public List<String> openLobbies() {
		// Create some mock lobbies
		if (mockLobbies == null) {
			mockLobbies = new ArrayList<String>();
			mockLobbies.add("Bob");
			mockLobbies.add("Rick");
			mockLobbies.add("Lobber");
		}
		return mockLobbies;
	}

	@Override
	public Lobby getLobby(String host) {
		// Return a new lobby
		return new DrawLobby(host);
	}

	@Override
	public String joinLobby(Lobby lobby) {
		// Return success
		return null;
	}

}
