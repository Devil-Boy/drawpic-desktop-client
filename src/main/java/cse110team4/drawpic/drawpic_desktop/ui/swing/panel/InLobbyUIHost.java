package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.IInLobbyController;
import cse110team4.drawpic.drawpic_desktop.ui.IInLobbyView;
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

public class InLobbyUIHost extends SwingView implements IInLobbyView {

	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private Logo logo;
	
	private JPanel playerListArea;
	
	private Map<Object, String> buttonMap;

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
		
		refreshPlayers();
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		
		JPanel panel2 = new JPanel();
		panel2.add(new LobbySettingsDisplay());
		
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
		
		// Initialize the button-to-player database
		buttonMap = new HashMap<Object, String>();
	}

	@Override
	public void setController(IInLobbyController controller) {
		// TODO Auto-generated method stub
		
	}
}
