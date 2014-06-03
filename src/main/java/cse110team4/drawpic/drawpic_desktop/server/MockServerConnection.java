package cse110team4.drawpic.drawpic_desktop.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cse110team4.drawpic.drawpic_core.player.FourPlayerLobby;
import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;

/**
 * This is just a mock class that allows us to test other parts of the program
 * It also fakes network latency by blocking
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class MockServerConnection implements ServerConnection {
	String username;
	
	String[] names = { "Bob", "Rick", "Lobber", "Ashley", "Jonotan",
			"Ricky", "Parser", "Sean", "Parsley", "Ellis", "Jo",
			"Boba", "Jint", "Rhe", "Lila", "Panic", "Syd"};
	
	List<Lobby> mockLobbies;
	
	Random random = new Random();

	@Override
	public String connect() {
		// Create mock lobby
		mockLobbies = new ArrayList<Lobby>();
		
		for (String name : names) {
			Lobby lobby = DesktopBeans.getContext().getBean("defualtFourPlayerLobby", Lobby.class);
			lobby.setHost(name);
			
			mockLobbies.add(lobby);
		}
		
		return null;
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
	public String createLobby(Lobby lobby) {
		// Block for a bit
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getLobbies(List<Lobby> openLobbies) {
		// Choose the amount to send
		int size = random.nextInt(mockLobbies.size());
		
		// Send random lobbies
		List<Lobby> tempList = new ArrayList<Lobby>(mockLobbies);
		for (int i=0; i < size; i++) {
			openLobbies.add(tempList.remove(random.nextInt(tempList.size())));
		}
		
		// Block for a bit
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
