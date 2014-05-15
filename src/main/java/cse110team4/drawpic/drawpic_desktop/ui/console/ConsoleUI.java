package cse110team4.drawpic.drawpic_desktop.ui.console;

import java.io.Console;
import java.util.List;

import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.MockServer;
import cse110team4.drawpic.drawpic_desktop.server.Server;

/**
 * This is the starting point for our program's console user interface
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class ConsoleUI implements Runnable {
	
	Console console;
	
	Server server;

	public ConsoleUI() {
		System.out.println("Initializing DrawPic Console UI");
		
		console = System.console();
		
		if (console == null) {
			throw new IllegalStateException("Could not connect to console. Are you running this in eclipse?");
		}
	}

	/**
	 * Here is where our program actually gets run
	 */
	@Override
	public void run() {
		server = new MockServer();
		
		try {
			server.connect();
		} catch (Exception e) {
			// There was an error while connecting
			e.printStackTrace();
			return;
		}
		
		String givenUsername = console.readLine("Choose a username: ");
		
		String loginResult = "";
		while ((loginResult = server.login(givenUsername)) != null) {
			System.out.println("Error with login: " + loginResult);
			givenUsername = console.readLine("Choose a different username: ");
		}
		
		System.out.println("Options:");
		System.out.println("\t1 - Create Lobby");
		System.out.println("\t2 - Join Lobby");
		
		byte lobbyOption = 0; // 1 - create lobby, 2 - join lobby
		while (lobbyOption == 0) {
			try {
				lobbyOption = Byte.parseByte(console.readLine());
				
				if (lobbyOption < 1 || lobbyOption > 2) {
					System.out.println("Invalid option. Try again");
					lobbyOption = 0;
				}
			} catch (NumberFormatException e) {
				System.out.println("Could not parse input. Try again");
			}
		}
		
		if (lobbyOption == 1) {
			// Create a lobby
			Lobby newLobby = server.createLobby();
		} else if (lobbyOption == 2) {
			// List the lobbies
			List<String> lobbyList = server.openLobbies();
		}
	}
	
	public void inLobby(Lobby lobby) {
		
	}
}
