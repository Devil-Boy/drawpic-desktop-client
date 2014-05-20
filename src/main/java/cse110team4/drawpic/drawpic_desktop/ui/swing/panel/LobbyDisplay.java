package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_core.Lobby;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

public class LobbyDisplay extends JPanel {
	private static final Color BG_COLOR = new Color(0x69, 0xed, 0x5b);

	Lobby lobby;
	
	public LobbyDisplay(Lobby lobby) {
		this.lobby = lobby;
		
		// Set background color
		setBackground(BG_COLOR);
		
		addContent();
	}

	private void addContent() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel hostLabel = new JLabel("Host:");
		panel.add(hostLabel);
		
		JLabel hostField = new JLabel(lobby.getHost());
		hostField.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel.add(hostField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel playerCountLabel = new JLabel("Players:");
		panel_1.add(playerCountLabel);
		
		JLabel playerCountField = new JLabel(lobby.numOfPlayers() + "/" + lobby.maxPlayers());
		playerCountField.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_1.add(playerCountField);
		
		JButton joinButton = new JButton("Join");
		joinButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		add(joinButton);
	}
}
