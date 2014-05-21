package cse110team4.drawpic.drawpic_desktop.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cse110team4.drawpic.drawpic_core.DrawLobby;
import cse110team4.drawpic.drawpic_core.Lobby;

/**
 * This is just a mock class that allows us to test other parts of the program
 * It also fakes network latency by blocking
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
		
		// Block for a bit
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Return successful
		return null;
	}

	@Override
	public Lobby createLobby() {
		// Block for a bit
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new DrawLobby(username);
	}

	@Override
	public List<String> openLobbies() {
		Random num = new Random();
		ArrayList<String> array = new ArrayList<String>();
		array.add("Bob");
		array.add("Rick");
		array.add("Lobber");
		array.add("Ashley");
		array.add("Jonotan");
		array.add("Ricky");
		array.add("Parser");
		array.add("Sean");
		// Create some mock lobbies
		if (mockLobbies == null) {
			mockLobbies = new ArrayList<String>();
			mockLobbies.add(array.get(num.nextInt(7)));
			mockLobbies.add(array.get(num.nextInt(7)));
			mockLobbies.add(array.get(num.nextInt(7)));
			mockLobbies.add(array.get(num.nextInt(7)));
		}
		
		// Block for a bit
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// Block for a bit
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Return success
		return null;
	}

}
