package cse110team4.drawpic.drawpic_desktop.ui;

import java.util.Arrays;

import cse110team4.drawpic.drawpic_desktop.ui.console.ConsoleUI;
import cse110team4.drawpic.drawpic_desktop.ui.swing.SwingUI;

/**
 * This is the main class (starting point) of our desktop application
 * 
 * @author Devil Boy (Kervin Sam)
 *
 */
public class App {
	
    public static void main(String[] args) {
    	if (Arrays.asList(args).contains("-console")) {
    		// Launch the console UI
    		(new ConsoleUI()).run();
    	} else {
    		// Launch the swing UI
    		(new SwingUI()).run();
    	}
    }
}
