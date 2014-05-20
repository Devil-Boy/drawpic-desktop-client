package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import cse110team4.drawpic.drawpic_core.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.Server;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LobbyBrowserUI extends PanelUI {
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	Lobby joinedLobby;
	
	List<Lobby> lobbies;
	Map<Object, Lobby> buttonMap;
	JPanel lobbyListArea;
	
	public LobbyBrowserUI(Server server) {
		super(server, BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		// Add all the content
		addContent();
	}
	
	private void getLobbies() {
		lobbies = new ArrayList<Lobby>();
		
		for (String host : server.openLobbies()) {
			lobbies.add(server.getLobby(host));
		}
	}

	private void addContent() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "Open Lobbies:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lobbyListArea = new JPanel();
		lobbyListArea.setLayout(new GridLayout(0, 1, 0, 0));
		
		refreshLobbies();
		
		JScrollPane scrollPane = new JScrollPane(lobbyListArea);
		panel.add(scrollPane);
		
		JPanel backButtonArea = new JPanel();
		backButtonArea.setOpaque(false);
		add(backButtonArea, BorderLayout.SOUTH);
		
		JButton backButton = new JButton("Back");
		backButtonArea.add(backButton);
		
		// Listeners
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Handle back button
			}
		});
	}
	
	private void refreshLobbies() {
		// Empty the container
		lobbyListArea.removeAll();
		
		// Obtain the lobby list from the server
		getLobbies();
		
		// Initialize the button-to-lobby database
		buttonMap = new HashMap<Object, Lobby>();
		
		// Create the action listener
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the lobby they chose
				Lobby chosenLobby = buttonMap.get(e.getSource());
				
				// Try to join the lobby
				String joinResult = "";
				if ((joinResult = server.joinLobby(chosenLobby)) == null) {
					joinedLobby = chosenLobby;
					notifyGuard();
				} else {
					JOptionPane.showMessageDialog(null, "Error with join:\n" + joinResult);
					
					// Refresh the lobby list
					refreshLobbies();
				}
			}
		};
		
		// Iterate through the lobbies we got from the server
		for (Lobby lobby : lobbies) {
			// Wrap lobbies in displays
			LobbyDisplay lobbyDisplay = new LobbyDisplay(lobby);
			
			// Map the join button the lobby
			buttonMap.put(lobbyDisplay.getButton(), lobby);
			
			// Set the button's listener
			lobbyDisplay.getButton().addActionListener(buttonListener);
			
			// Add the display to the container
			lobbyListArea.add(lobbyDisplay);
		}
	}
	
	/**
	 * This will cause the guarded block to unblock
	 */
	public synchronized void notifyGuard() {
		this.notify();
	}

	/**
	 * Gets the lobby that the player joined
	 * It will block thread execution until login is successful
	 * @return The lobby object
	 */
	public synchronized Lobby getLobbyJoined() {
		while (joinedLobby == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return joinedLobby;
	}
}
