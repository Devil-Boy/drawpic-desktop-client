package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.server.Server;
import cse110team4.drawpic.drawpic_desktop.ui.swing.Logo;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;

public class InLobbyUI extends DrawPicUI {

	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private Logo logo;

	public InLobbyUI(Server server) {
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
		mainArea.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel logoArea = new JPanel();
		logoArea.setOpaque(false);
		if (logo != null) {
			logoArea.add(logo);
		}
		mainArea.add(logoArea);
		
		JScrollPane settingsArea = new JScrollPane();
		add(settingsArea, BorderLayout.EAST);
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		add(buttonArea, BorderLayout.SOUTH);
		
		JButton leaveButton = new JButton("Leave");
		buttonArea.add(leaveButton);
	}
}
