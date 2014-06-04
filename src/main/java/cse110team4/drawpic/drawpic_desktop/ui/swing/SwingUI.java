package cse110team4.drawpic.drawpic_desktop.ui.swing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.MockServerConnection;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.SwingView;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LobbyBrowserUI;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LobbyOptionView;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LoginView;

public class SwingUI implements Runnable {
	
	JFrame window;
	
	private BufferedImage icon;
	
	ServerConnection server;
	
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
		InputStream iconStream = getClass().getResourceAsStream("/graphics/icon.png");
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
		server = new MockServerConnection();
		
		try {
			server.connect();
		} catch (Exception e) {
			// There was an error while connecting
			e.printStackTrace();
			return;
		}
		
		// Start the login process
		String username = handleLogin();
		
		// Start the lobby joining process
		Lobby joinedLobby = null;
		while (joinedLobby == null) {
			int lobbyOption = handleLobbyOption();
			if (lobbyOption == 1) {
				// TODO: Handle creation of a lobby
				System.out.println("He's creating");
			} else if (lobbyOption == 2) {
				joinedLobby = handleLobbyChoose();
				
				if (joinedLobby != null) {
					// Move on
				}
			}
		}
	}
	
	private void setUI(SwingView ui) {
		window.setVisible(false);
		window.getContentPane().removeAll();
		window.add(ui);
		window.getRootPane().setDefaultButton(ui.getDefaultButton());
		window.pack();
		window.setVisible(true);
	}

	private String handleLogin() {
		LoginView ui = new LoginView(server);
		setUI(ui);
		return ui.getUsername();
	}
	
	private int handleLobbyOption() {
		LobbyOptionView ui = new LobbyOptionView(server);
		setUI(ui);
		return ui.getLobbyOption();
	}
	
	private Lobby handleLobbyChoose() {
		LobbyBrowserUI ui = new LobbyBrowserUI(server);
		setUI(ui);
		return ui.getLobbyJoined();
	}
}
