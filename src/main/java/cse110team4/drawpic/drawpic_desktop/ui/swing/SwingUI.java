package cse110team4.drawpic.drawpic_desktop.ui.swing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cse110team4.drawpic.drawpic_desktop.server.MockServer;
import cse110team4.drawpic.drawpic_desktop.server.Server;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LoginUI;

public class SwingUI implements Runnable {
	
	JFrame window;
	
	private BufferedImage icon;
	
	Server server;
	
	public SwingUI() {
		System.out.println("Initializing DrawPic GUI...");
		
		// Try to use Nimbus L&F
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (info.getName().equals("Nimbus")) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// Couldn't find Nimbus
		}
		
		// Construct the window
		this.window = new JFrame("DrawPic");
		
		// Get the icon
		InputStream iconStream = getClass().getResourceAsStream("/icon.png");
		try {
			icon = ImageIO.read(iconStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Set the icon
		window.setIconImage(icon);
		
		// End the program when window is closed
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

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
		
		// Start the login process
		handleLogin();
		
		System.out.println("Login done");
	}

	private void handleLogin() {
		LoginUI ui = new LoginUI(server);
		window.add(ui);
		window.pack();
		window.setVisible(true);
		
		ui.complete();
	}
}
