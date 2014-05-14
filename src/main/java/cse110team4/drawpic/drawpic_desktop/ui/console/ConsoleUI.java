package cse110team4.drawpic.drawpic_desktop.ui.console;

import java.io.Console;

import cse110team4.drawpic.drawpic_desktop.network.Server;
import cse110team4.drawpic.drawpic_desktop.network.dummy.MockServer;

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
		
		byte lobbyOption = Byte.parseByte(console.readLine()); // 1 - create lobby, 2 - join lobby
		
	}
}
