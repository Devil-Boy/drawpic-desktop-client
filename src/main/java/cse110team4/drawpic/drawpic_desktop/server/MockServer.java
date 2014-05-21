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
	
	List<Lobby> mockLobbies;
	
	Random random = new Random();

	@Override
	public void connect() throws Exception {
		// Create mock lobby
		mockLobbies = new ArrayList<Lobby>();
		
		mockLobbies.add(new DrawLobby("Bob"));
		mockLobbies.add(new DrawLobby("Rick"));
		mockLobbies.add(new DrawLobby("Lobber"));
		mockLobbies.add(new DrawLobby("Ashley"));
		mockLobbies.add(new DrawLobby("Jonotan"));
		mockLobbies.add(new DrawLobby("Ricky"));
		mockLobbies.add(new DrawLobby("Parser"));
		mockLobbies.add(new DrawLobby("Sean"));
		mockLobbies.add(new DrawLobby("Parsley"));
		mockLobbies.add(new DrawLobby("Ellis"));
		mockLobbies.add(new DrawLobby("Jo"));
		mockLobbies.add(new DrawLobby("Boba"));
		mockLobbies.add(new DrawLobby("Jint"));
		mockLobbies.add(new DrawLobby("Rhe"));
		mockLobbies.add(new DrawLobby("Lila"));
		mockLobbies.add(new DrawLobby("Panic"));
		mockLobbies.add(new DrawLobby("Syd"));
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
		List<String> openLobbies = new ArrayList<String>();
		
		// Choose the amount to send
		int size = random.nextInt(mockLobbies.size());
		
		// Send random lobbies
		List<Lobby> tempList = new ArrayList<Lobby>(mockLobbies);
		for (int i=0; i < size; i++) {
			openLobbies.add(tempList.remove(random.nextInt(tempList.size())).getHost());
		}
		
		// Block for a bit
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return openLobbies;
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
