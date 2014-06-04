package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LobbyBrowserUI extends SwingView {
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private boolean block = true;
	
	Lobby joinedLobby;
	
	List<Lobby> lobbies;
	Map<Object, Lobby> buttonMap;
	JPanel lobbyListArea;
	
	public LobbyBrowserUI(ServerConnection server) {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
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
		
		refreshLobbies();
		
		JScrollPane scrollPane = new JScrollPane(lobbyListArea);
		panel.add(scrollPane);
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		add(buttonArea, BorderLayout.SOUTH);
		
		JButton backButton = new JButton("Back");
		buttonArea.add(backButton);
		
		JButton refreshButton = new JButton("Refresh");
		buttonArea.add(refreshButton);
		
		// Listeners
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unblock();
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshLobbies();
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
					unblock();
				} else {
					JOptionPane.showMessageDialog(null, "Error with join:\n" + joinResult);
					
					// Refresh the lobby list
					refreshLobbies();
				}
			}
		};
		
		if (lobbies.isEmpty()) {
			lobbyListArea.setLayout(new FlowLayout());
			
			lobbyListArea.add(new JLabel("There are no open lobbies"));
		} else {
			// Use grid layout
			lobbyListArea.setLayout(new GridLayout(0, 1, 0, 0));
			
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
		
		// Display the changes
		revalidate();
	}
	
	/**
	 * This will cause the guarded block to unblock
	 */
	public synchronized void unblock() {
		block = false;
		this.notify();
	}

	/**
	 * Gets the lobby that the player joined
	 * It will block thread execution until login is successful or player uses back button
	 * @return The lobby object, or null if they didn't choose one
	 */
	public synchronized Lobby getLobbyJoined() {
		while (block) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return joinedLobby;
	}
}
