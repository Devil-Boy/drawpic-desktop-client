package cse110team4.drawpic.drawpic_desktop.ui.console;

import java.io.Console;

/**
 * This is the starting point for our program's console user interface
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class ConsoleUI implements Runnable {
	
	Console console;

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
		String givenUsername = console.readLine("Choose a username: ");
	}
}
