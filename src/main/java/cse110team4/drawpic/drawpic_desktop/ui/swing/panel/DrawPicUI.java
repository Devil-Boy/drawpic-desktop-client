package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public abstract class DrawPicUI extends JPanel {
	
	JButton defaultButton;
	
	public DrawPicUI(Color bgColor, int preferredWidth, int preferredHeight) {
		
		// Set background color
		setBackground(bgColor);
		
		// Set the preferred size
		setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}
	
	public void setDefaultButton(JButton button) {
		this.defaultButton = button;
	}
	
	public JButton getDefaultButton() {
		return defaultButton;
	}
}
