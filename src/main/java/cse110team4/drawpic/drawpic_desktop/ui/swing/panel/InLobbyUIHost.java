package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_core.player.Lobby;
<<<<<<< HEAD
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientUsernameSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;
import cse110team4.drawpic.drawpic_desktop.event.lobby.LobbySettingsChangedEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerJoinedLobbyEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerLeftLobbyEvent;
import cse110team4.drawpic.drawpic_desktop.server.JMSServerConnection;
=======
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
>>>>>>> branch 'master' of git@bitbucket.org:kirkkw/drawpic-desktop-client.git
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.IInLobbyController;
import cse110team4.drawpic.drawpic_desktop.ui.IInLobbyView;
import cse110team4.drawpic.drawpic_desktop.ui.ILobbyBrowsingController;
import cse110team4.drawpic.drawpic_desktop.ui.swing.Logo;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class InLobbyUIHost extends SwingView implements IInLobbyView, ClientListener {

	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private Logo logo;
	
	private JPanel playerListArea;
	private JPanel panel2;
	
	private Map<Object, String> buttonMap;

	private IInLobbyController controller;
	
	public InLobbyUIHost() {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		// Try getting the logo
		try {
			logo = new Logo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Add the content
		addContent();
	}

	private void addContent() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel mainArea = new JPanel();
		mainArea.setOpaque(false);
		add(mainArea, BorderLayout.CENTER);
		mainArea.setLayout(new BorderLayout(0, 0));
		
		if (logo != null) {
			mainArea.add(logo, BorderLayout.NORTH);
		}
		
		playerListArea = new JPanel();
		playerListArea.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Players:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainArea.add(playerListArea, BorderLayout.CENTER);
		playerListArea.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		
		panel2 = new JPanel();
		
		JScrollPane settingsArea = new JScrollPane(panel2);
		panel.add(settingsArea);
		
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		add(buttonArea, BorderLayout.SOUTH);
		
		JButton btnStart = new JButton("Start");
		buttonArea.add(btnStart);
		
		JButton leaveButton = new JButton("Leave");
		buttonArea.add(leaveButton);
	}
	
	private void refreshPlayers() {
		// Empty the player list
		playerListArea.removeAll();
		ServerConnection connection = DesktopBeans.getContext().getBean(JMSServerConnection.class);
		for (String player : connection.getClientData().getLobby().getPlayers()) {
			JLabel playerLabel = new JLabel(player);
			playerListArea.add(playerLabel);
		}
		// Initialize the button-to-player database
		buttonMap = new HashMap<Object, String>();
	}

	@Override
	public void setController(IInLobbyController controller) {
		// TODO Auto-generated method stub
		this.controller = controller;
	}

	@Override
	public void usernameSet(ClientUsernameSetEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lobbySet(ClientLobbySetEvent event) {
		// TODO Auto-generated method stub
		System.err.println("Lobby set!!!!");
		refreshPlayers();
	}
	
	private void refreshSettings(){
		panel2.removeAll();
		panel2.add(new LobbySettingsDisplay((NormalLobbySettings) DesktopBeans.getContext().getBean(ServerConnection.class).getClientData().getLobby().getSettings()));
	
	}
}
