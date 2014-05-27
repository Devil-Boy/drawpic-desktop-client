package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.swing.Logo;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class InLobbyUI extends DrawPicUI {

	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private Logo logo;
	
	private JPanel playerListArea;
	
	private Map<Object, String> buttonMap;

	public InLobbyUI(ServerConnection server) {
		super(server, BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
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
		mainArea.add(playerListArea, BorderLayout.NORTH);
		playerListArea.setLayout(new GridLayout(4, 1, 0, 0));
		
		refreshPlayers();
		
		JScrollPane settingsArea = new JScrollPane();
		add(settingsArea, BorderLayout.EAST);
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		add(buttonArea, BorderLayout.SOUTH);
		
		JButton leaveButton = new JButton("Leave");
		buttonArea.add(leaveButton);
	}
	
	private void refreshPlayers() {
		// Empty the player list
		playerListArea.removeAll();
		
		// Initialize the button-to-player database
		buttonMap = new HashMap<Object, String>();
	}
}
