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
			System.out.println("Creating a lobby...");
			
			// Create a lobby
			Lobby newLobby = server.createLobby();
			
			// Move on
			inLobby(newLobby);
		} else if (lobbyOption == 2) {
			// Select a lobby
			List<String> lobbyList = null;
			int lobbyChoice = -1;
			while (lobbyChoice == -1) {
				System.out.println("Open lobbies: ");
				
				// List the lobbies
				lobbyList = server.openLobbies();
				for (int i=0; i < lobbyList.size(); i++) {
					Lobby lobby = server.getLobby(lobbyList.get(i));
					System.out.printf("\t%d - %s (%d/%d)", i, lobby.getPlayers()[0], lobby.numOfPlayers(), lobby.maxPlayers());
				}
				
				// Parse their selection
				try {
					lobbyChoice = Integer.parseInt(console.readLine());
					
					if (lobbyChoice < 1 || lobbyChoice > lobbyList.size()) {
						System.out.println("Invalid option. Try again");
						lobbyChoice = -1;
					}
					
					// Try joining the lobby
					Lobby lobby = server.getLobby(lobbyList.get(lobbyChoice));
					String joinResult = server.joinLobby(lobby);
					if (joinResult == null) {
						System.out.println("Joined lobby hosted by " + lobby.getPlayers()[0]);
						
						// Move on
						inLobby(lobby);
					} else {
						System.out.println("Error joining lobby: " + joinResult);
					}
				} catch (NumberFormatException e) {
					System.out.println("Could not parse input. Try again");
				}
			}
		}
	}
	
	public void inLobby(Lobby lobby) {
		
	}
}
