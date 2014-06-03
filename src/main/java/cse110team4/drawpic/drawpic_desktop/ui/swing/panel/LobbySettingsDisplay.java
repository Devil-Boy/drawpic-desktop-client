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

public class LobbySettingsDisplay extends JPanel {

	/**
	 * Create the panel.
	 */
	public LobbySettingsDisplay() {
		
		setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel judgePanel = new JPanel();
		add(judgePanel);
		
		JLabel lbljudgeLabel = new JLabel("Judge Mode:");
		judgePanel.add(lbljudgeLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(JudgeSetting.values()));
		judgePanel.add(comboBox);
		
		JPanel rounds = new JPanel();
		add(rounds);
		
		JLabel lblNumberOfRounds = new JLabel("Number of Rounds:");
		rounds.add(lblNumberOfRounds);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		rounds.add(spinner);
		
		JPanel wins = new JPanel();
		add(wins);
		
		JLabel lblNumberOfWins = new JLabel("Number of Wins Required:");
		wins.add(lblNumberOfWins);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		wins.add(spinner_1);
		
		JPanel time = new JPanel();
		add(time);
		
		JLabel lblDrawingTime = new JLabel("Drawing Time (Seconds):");
		time.add(lblDrawingTime);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(10, 10, 60, 5));
		time.add(spinner_2);

	}

}
