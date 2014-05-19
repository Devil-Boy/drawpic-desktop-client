package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.ui.swing.Logo;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class LoginUI extends JPanel {
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);

	private Logo logo;
	
	public LoginUI() {
		// Try getting the logo
		try {
			logo = new Logo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Set background color
		setBackground(BG_COLOR);
		
		// Set the preferred size
		setPreferredSize(new Dimension(300, 300));
		
		// Add all the content
		addContent();
	}

	private void addContent() {
		setLayout(new GridLayout(0, 1, 0, 0));

		if (logo != null) {
			add(logo);
		}
		
		JPanel loginArea = new JPanel();
		loginArea.setOpaque(false);
		add(loginArea);
		loginArea.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel labelArea = new JPanel();
		labelArea.setOpaque(false);
		loginArea.add(labelArea);
		
		JLabel instrText = new JLabel("Choose a Username:");
		instrText.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelArea.add(instrText);
		
		JPanel inputArea = new JPanel();
		inputArea.setOpaque(false);
		loginArea.add(inputArea);
		
		JFormattedTextField usernameField = new JFormattedTextField();
		usernameField.setColumns(20);
		inputArea.add(usernameField);
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		loginArea.add(buttonArea);
		
		JButton connectButton = new JButton("Connect");
		buttonArea.add(connectButton);
	}
}
