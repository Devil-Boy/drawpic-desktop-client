package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.ILobbyChoiceController;
import cse110team4.drawpic.drawpic_desktop.ui.ILobbyChoiceView;
import cse110team4.drawpic.drawpic_desktop.ui.ILogInController;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LobbyOptionView extends SwingView implements ILobbyChoiceView{
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private JButton createLobbyButton;
	private JButton joinLobbyButton;
	
	public LobbyOptionView() {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		// Add all the content
		addContent();
	}

	private void addContent() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel topPadding = new JPanel();
		topPadding.setOpaque(false);
		add(topPadding);
		
		JPanel createLobbyArea = new JPanel();
		createLobbyArea.setOpaque(false);
		add(createLobbyArea);
		
		createLobbyButton = new JButton("Create Lobby");
		createLobbyButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		createLobbyButton.putClientProperty("JComponent.sizeVariant", "large");
		createLobbyArea.add(createLobbyButton);
		
		JPanel joinLobbyArea = new JPanel();
		joinLobbyArea.setOpaque(false);
		add(joinLobbyArea);
		
		joinLobbyButton = new JButton("Join Lobby");
		joinLobbyButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		joinLobbyButton.putClientProperty("JComponent.sizeVariant", "large");
		joinLobbyArea.add(joinLobbyButton);
		
		JPanel bottomPadding = new JPanel();
		bottomPadding.setOpaque(false);
		add(bottomPadding);
	}
	
	/**
	 * This will cause the guarded block to unblock
	 */
	public synchronized void notifyGuard() {
		this.notify();
	}

	@Override
	public void setController(final ILobbyChoiceController controller) {
		
		createLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createLobby();
			}
		});
		
		joinLobbyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.joinLobby();
			}
		});
	}
}
