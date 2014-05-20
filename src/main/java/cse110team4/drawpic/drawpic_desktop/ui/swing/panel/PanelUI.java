package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.server.Server;

public abstract class PanelUI extends JPanel {

	Server server;
	
	public PanelUI(Server server, Color bgColor, int preferredWidth, int preferredHeight) {
		this.server = server;
		
		// Set background color
		setBackground(bgColor);
		
		// Set the preferred size
		setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}
}
