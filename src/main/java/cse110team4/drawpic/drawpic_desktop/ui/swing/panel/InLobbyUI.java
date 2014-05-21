package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.server.Server;

public class InLobbyUI extends DrawPicUI {

	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;

	public InLobbyUI(Server server) {
		super(server, BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		addContent();
	}

	private void addContent() {
		
	}
}
