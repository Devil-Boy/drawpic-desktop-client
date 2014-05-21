package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import javax.swing.JPanel;

import cse110team4.drawpic.drawpic_desktop.server.Server;
import cse110team4.drawpic.drawpic_desktop.ui.swing.Logo;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginUI extends DrawPicUI {
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private static final String USERNAME_FORMAT = "([A-Z]|[a-z]|[0-9]){1,16}";

	private Logo logo;
	
	private String username;
	
	public LoginUI(Server server) {
		super(server, BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		// Try getting the logo
		try {
			logo = new Logo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
		final JTextField usernameField = new JTextField();
		usernameField.setColumns(18);
		usernameField.setHorizontalAlignment(JLabel.CENTER);
		inputArea.add(usernameField);
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		loginArea.add(buttonArea);
		
		final JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		loginButton.setEnabled(false);
		buttonArea.add(loginButton);
		
		// Listeners
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				loginButton.setEnabled(usernameField.getText().matches(USERNAME_FORMAT));
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String givenUsername = usernameField.getText();
				
				String loginResult = "";
				if ((loginResult = server.login(givenUsername)) == null) {
					username = givenUsername;
					notifyGuard();
				} else {
					JOptionPane.showMessageDialog(null, "Error with login:\n" + loginResult);
				}
			}
		});
	}
	
	/**
	 * This will cause the guarded block to unblock
	 */
	public synchronized void notifyGuard() {
		this.notify();
	}

	/**
	 * Gets the username the player logged in with
	 * It will block thread execution until login is successful
	 * @return The String username
	 */
	public synchronized String getUsername() {
		while (username == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return username;
	}
}
