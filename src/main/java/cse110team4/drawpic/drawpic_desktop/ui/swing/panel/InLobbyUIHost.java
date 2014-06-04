package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientUsernameSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;
import cse110team4.drawpic.drawpic_desktop.event.listener.LobbyListener;
import cse110team4.drawpic.drawpic_desktop.event.lobby.LobbySettingsChangedEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerJoinedLobbyEvent;
import cse110team4.drawpic.drawpic_desktop.event.lobby.PlayerLeftLobbyEvent;
import cse110team4.drawpic.drawpic_desktop.server.JMSServerConnection;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class InLobbyUIHost extends SwingView implements IInLobbyView, ClientListener, LobbyListener {

	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 500;
	private static final int PREFERRED_HEIGHT = 300;
	
	private Logo logo;
	
	private JPanel playerListArea;
	private JPanel panel2;
	
	private Map<Object, String> buttonMap;

	private IInLobbyController controller;
	
	private JButton leaveButton;
	private JButton startButton;
	private JScrollPane settingsArea;
	
	public InLobbyUIHost(EventDispatcher dispatch) {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		dispatch.register(ClientLobbySetEvent.class, this);
		dispatch.register(PlayerJoinedLobbyEvent.class, this);
		dispatch.register(PlayerLeftLobbyEvent.class, this);
		dispatch.register(LobbySettingsChangedEvent.class, this);
		
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
		
		settingsArea = new JScrollPane(panel2);
		panel.add(settingsArea);
		
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		add(buttonArea, BorderLayout.SOUTH);
		
		startButton = new JButton("Start");
		buttonArea.add(startButton);
		
		leaveButton = new JButton("Leave");
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
		
		revalidate();
	}

	@Override
	public void setController(final IInLobbyController controller) {
		// TODO Auto-generated method stub
		this.controller = controller;
		
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.leaveLobby();
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.startGame();
			}
		});
		
	}

	@Override
	public void usernameSet(ClientUsernameSetEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lobbySet(ClientLobbySetEvent event) {
		// TODO Auto-generated method stub
		refreshPlayers();
	}
	
	public void refreshSettings(){
		panel2.removeAll();
		panel2.add(new LobbySettingsDisplay((NormalLobbySettings) DesktopBeans.getContext().getBean(ServerConnection.class).getClientData().getLobby().getSettings(), this.controller));
	
		revalidate();
	}

	@Override
	public void playerJoinedLobby(PlayerJoinedLobbyEvent event) {
		// TODO Auto-generated method stub
		refreshPlayers();
	}

	@Override
	public void playerLeftLobby(PlayerLeftLobbyEvent event) {
		// TODO Auto-generated method stub
		refreshPlayers();
	}

	@Override
	public void settingsChanged(LobbySettingsChangedEvent event) {
		System.err.println("SETTINGS CHANGED");
		refreshSettings();
	}
}
