package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class PlayerDisplay extends JPanel {
	private static final Color BG_COLOR = new Color(0x69, 0xed, 0x5b);
	
	private String player;
	private boolean showKickButton;
	private JButton kickButton;
	
	public PlayerDisplay(String player, boolean showKickButton) {
		this.player = player;
		this.showKickButton = showKickButton;
		
		addContent();
	}
	
	private void addContent() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		// Set background color
		setBackground(BG_COLOR);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel nameLabel = new JLabel(player);
		add(nameLabel);
		
		JPanel kickButtonArea = new JPanel();
		kickButtonArea.setOpaque(false);
		add(kickButtonArea);
		
		if (showKickButton) {
			kickButton = new JButton("Kick");
			kickButtonArea.add(kickButton);
		}
	}
	
	public String getPlayer() {
		return player;
	}
	
	public JButton getButton() {
		return kickButton;
	}
}
