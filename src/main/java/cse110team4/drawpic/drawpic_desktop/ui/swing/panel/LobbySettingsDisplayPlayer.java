package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings.JudgeSetting;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class LobbySettingsDisplayPlayer extends JPanel {

	/**
	 * Create the panel.
	 */
	public LobbySettingsDisplayPlayer() {
		
		setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel judgePanel = new JPanel();
		add(judgePanel);
		
		JLabel lbljudgeLabel = new JLabel("Judge Mode:");
		judgePanel.add(lbljudgeLabel);
		
		JLabel lblNewLabel = new JLabel("New label");
		judgePanel.add(lblNewLabel);
		
		JPanel rounds = new JPanel();
		add(rounds);
		
		JLabel lblNumberOfRounds = new JLabel("Number of Rounds:");
		rounds.add(lblNumberOfRounds);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		rounds.add(lblNewLabel_1);
		
		JPanel wins = new JPanel();
		add(wins);
		
		JLabel lblNumberOfWins = new JLabel("Number of Wins Required:");
		wins.add(lblNumberOfWins);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		wins.add(lblNewLabel_2);
		
		JPanel time = new JPanel();
		add(time);
		
		JLabel lblDrawingTime = new JLabel("Drawing Time (Seconds):");
		time.add(lblDrawingTime);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		time.add(lblNewLabel_3);

	}

}

