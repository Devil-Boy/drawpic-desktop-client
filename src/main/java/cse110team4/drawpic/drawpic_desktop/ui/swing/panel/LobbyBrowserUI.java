package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;
import java.awt.FlowLayout;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.listener.ServerListener;
import cse110team4.drawpic.drawpic_desktop.event.server.ServerLobbyListSetEvent;
import cse110team4.drawpic.drawpic_desktop.ui.ILobbyBrowsingController;
import cse110team4.drawpic.drawpic_desktop.ui.ILobbyBrowsingView;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.util.List;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LobbyBrowserUI extends SwingView implements ILobbyBrowsingView, ServerListener {
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private boolean block = true;
	
	private ILobbyBrowsingController controller;
	
	Lobby joinedLobby;
	
	JPanel lobbyListArea;
	private JButton backButton;
	private JButton refreshButton;
	
	public LobbyBrowserUI(EventDispatcher dispatcher) {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);

		dispatcher.register(ServerLobbyListSetEvent.class, this);
		
		// Add all the content
		addContent();
	}

	private void addContent() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "Open Lobbies:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lobbyListArea = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(lobbyListArea);
		panel.add(scrollPane);
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		add(buttonArea, BorderLayout.SOUTH);
		
		backButton = new JButton("Back");
		buttonArea.add(backButton);
		
		refreshButton = new JButton("Refresh");
		buttonArea.add(refreshButton);
	}
	
	@Override
	public void lobbyListSet(ServerLobbyListSetEvent event) {
		displayLobbies(event.getLobbyList());
	}
	
	private void displayLobbies(List<Lobby> lobbies) {
		// Empty the container
		lobbyListArea.removeAll();
		
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
				
				lobbyDisplay.setController(controller);
				
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

	@Override
	public void setController(final ILobbyBrowsingController controller) {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.goBack();
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.refresh();
			}
		});
	}
}
