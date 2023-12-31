package cse110team4.drawpic.drawpic_desktop.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.server.ServerLobbyListSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.GameDrawView;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.InLobbyUIHost;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.InLobbyUIPlayer;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LobbyBrowserUI;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LobbyOptionView;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.LoginView;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.SwingView;

public class SwingDisplayer implements UIDisplayer {
	private JFrame window;
	private BufferedImage icon;
	
	public SwingDisplayer() {
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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setUI(SwingView ui) {
		window.setVisible(false);
		window.getContentPane().removeAll();
		window.add(ui);
		window.getRootPane().setDefaultButton(ui.getDefaultButton());
		window.pack();
		window.setVisible(true);
	}

	@Override
	public void viewPhase(ClientPhase phase) {
		switch (phase) {
		case LOGIN:
			LoginView loginView = DesktopBeans.getContext().getBean(LoginView.class);
			loginView.setController(DesktopBeans.getContext().getBean(LogInController.class));
			setUI(loginView);
			break;
		case LOBBY_OPTION:
			LobbyOptionView lobbyOptionView = DesktopBeans.getContext().getBean(LobbyOptionView.class);
			lobbyOptionView.setController(DesktopBeans.getContext().getBean(LobbyChoiceController.class));
			setUI(lobbyOptionView);
			break;
		case LOBBY_BROWSING:
			LobbyBrowserUI lobbyBrowserUI = DesktopBeans.getContext().getBean(LobbyBrowserUI.class);
			lobbyBrowserUI.setController(DesktopBeans.getContext().getBean(LobbyBrowsingController.class));
			lobbyBrowserUI.displayLobbies(DesktopBeans.getContext().getBean(ServerConnection.class).getLobbyList());
			DesktopBeans.getContext().getBean(EventDispatcher.class).register(ServerLobbyListSetEvent.class, lobbyBrowserUI);
			setUI(lobbyBrowserUI);
			break;
		case IN_LOBBY_HOST:
			InLobbyUIHost inLobbyUIHost = DesktopBeans.getContext().getBean(InLobbyUIHost.class);
			inLobbyUIHost.setController(DesktopBeans.getContext().getBean("swingInLobbyControllerHost", InLobbyController.class));
			inLobbyUIHost.refreshSettings();
			setUI(inLobbyUIHost);
			break;
		case IN_LOBBY_PLAYER:
			InLobbyUIPlayer inLobbyUIPlayer = DesktopBeans.getContext().getBean(InLobbyUIPlayer.class);
			inLobbyUIPlayer.setController(DesktopBeans.getContext().getBean("swingInLobbyControllerPlayer", InLobbyController.class));
			
			setUI(inLobbyUIPlayer);
			break;
		case GAME_DRAW:
			GameDrawView gameDrawView = DesktopBeans.getContext().getBean(GameDrawView.class);
			gameDrawView.setController(DesktopBeans.getContext().getBean("swingGameDrawController", GameDrawController.class));
			
			setUI(gameDrawView);
			break;
		case GAME_JUDGE:
			break;
		case GAME_RESULTS:
			break;
		}
	}
}
