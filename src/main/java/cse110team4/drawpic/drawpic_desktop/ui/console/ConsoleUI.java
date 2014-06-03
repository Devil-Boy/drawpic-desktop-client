package cse110team4.drawpic.drawpic_desktop.ui.console;

import java.io.Console;
import java.util.List;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.MockServerConnection;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

/**
 * This is the starting point for our program's console user interface
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class ConsoleUI implements Runnable {
	
	Console console;
	
	ServerConnection server;

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
		server = new MockServerConnection();
		
		try {
			server.connect();
		} catch (Exception e) {
			// There was an error while connecting
			e.printStackTrace();
			return;
		}
		
		String givenUsername = promptUsername();
		byte lobbyOption = promptCreateJoinLobby();
		Lobby joinedLobby = runLobbyOption(lobbyOption);
		inLobby(joinedLobby, givenUsername);
	}
	
	private String promptUsername() {
		String username = console.readLine("Choose a username: ");
		
		String loginResult = "";
		while ((loginResult = server.login(username)) != null) {
			System.out.println("Error with login: " + loginResult);
			username = console.readLine("Choose a different username: ");
		}
		
		return username;
	}
	
	private byte promptCreateJoinLobby() {
		System.out.println("Options:");
		System.out.println("\t1 - Create Lobby");
		System.out.println("\t2 - Join Lobby");
		
		byte choice = 0;
		
		while (choice == 0) {
			try {
				choice = Byte.parseByte(console.readLine());
				
				if (choice < 1 || choice > 2) {
					System.out.println("Invalid option. Try again");
					choice = 0;
				}
			} catch (NumberFormatException e) {
				System.out.println("Could not parse input. Try again");
			}
		}
		
		return choice;
	}
	
	private Lobby runLobbyOption(byte option) {
		if (option == 1) {
			System.out.println("Creating a lobby...");
			
			// Create a lobby
			Lobby newLobby = server.createLobby();
			newLobby.addPlayer("bob");
			newLobby.addPlayer("kirk");
			newLobby.addPlayer("kervin");
			// Move on
			return newLobby;
		} else if (option == 2) {
			// Select a lobby
			List<String> lobbyList = null;
			int lobbyChoice = 0;
			while (lobbyChoice == 0) {
				System.out.println("Open lobbies: ");
				
				// List the lobbies
				lobbyList = server.openLobbies();
				for (int i=0; i < lobbyList.size(); i++) {
					Lobby lobby = server.getLobby(lobbyList.get(i));
					System.out.printf("\t%d - %s (%d/%d)", i + 1, lobby.getPlayers()[0], lobby.numOfPlayers(), lobby.maxPlayers());
					System.out.println();
				}
				
				// Parse their selection
				try {
					lobbyChoice = Integer.parseInt(console.readLine());
					
					if (lobbyChoice < 1 || lobbyChoice > lobbyList.size()) {
						System.out.println("Invalid option. Try again");
						lobbyChoice = 0;
					} else {
						// Try joining the lobby
						Lobby lobby = server.getLobby(lobbyList.get(lobbyChoice - 1));
						String joinResult = server.joinLobby(lobby);
						if (joinResult == null) {
							System.out.println("Joined lobby hosted by " + lobby.getPlayers()[0]);
							
							// Move on
							return lobby;
						} else {
							System.out.println("Error joining lobby: " + joinResult);
							return null;
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Could not parse input. Try again");
				}
			}
		}
		return null;
	}
	
	private void displayLobby(Lobby lobby) {
		int numPlayer = 0;
		if (lobby == null) {
			System.out.println("Lobby is null");
		}
		System.out.println("Players in lobby:");
		for (String player : lobby.getPlayers()) {
			if (player!= null) {
				numPlayer++;
				System.out.println(numPlayer + " - " + player);
			}
		}
	}
	
	private void inLobby(Lobby lobby, String username) {
		if (username == lobby.getHost()) {
			int hostSelection = 0;
			while (hostSelection == 0) {
				System.out.println("\nLobby Host Options: ");
				System.out.println("\t1 - Refresh list of players");
				System.out.println("\t2 - Kick a player");
				System.out.println("\t3 - Change lobby session options");
				System.out.println("\t4 - End lobby");
				System.out.println("\t5 - Start game");
				
				try {
					hostSelection = Integer.parseInt(console.readLine());
					if (hostSelection < 1 || hostSelection > 5) {
						System.out.println("Invalid selection.");
						hostSelection = 0;
					} else {
						if (hostSelection == 1) {
							displayLobby(lobby);
						}
						else if (hostSelection == 2) {
							String kickPlayerResult = "";
							if (hostKickPlayer(lobby) == null) {
								System.out.println("Player has been successfully kicked");
							}
							else {
								System.out.println("Problem kicking player (" + kickPlayerResult + ")");
							}
						}
						else if (hostSelection == 3) {
							//insert options stuff here
						}
						else if (hostSelection == 4) {
							//yeah;
						}
						else if (hostSelection == 5) {
							//Start game
						}
						
						hostSelection = 0;
					}
				} catch (NumberFormatException e) {
					System.out.println("Could not parse input. Try again.");
				}
			}
		} 
	}
	
	private String hostKickPlayer(Lobby lobby) {
		System.out.println("Select a player to kick:");
		displayLobby(lobby);
		
		int choiceToKick = -1;
		
		String kickResult = "";
		
		while (choiceToKick == -1) {
			choiceToKick = Integer.parseInt(console.readLine());
			if (choiceToKick < 2 || choiceToKick > 4) {
				System.out.println("Invalid choice, try again.");
				displayLobby(lobby);
				choiceToKick = -1;
				continue;
			}
			
			int playerNum = 0;
			for (String player : lobby.getPlayers()) {
				if (player != null) {
					playerNum++;
					if  (playerNum == choiceToKick) {
						lobby.removePlayer(player);
						return null;
					}
				}
			}
		}
		kickResult = "Could not kick player";
		return kickResult;
	}
}
