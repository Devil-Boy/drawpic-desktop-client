package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings.JudgeSetting;
import cse110team4.drawpic.drawpic_desktop.ui.IInLobbyController;
import cse110team4.drawpic.drawpic_desktop.ui.InLobbyController;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LobbySettingsDisplay extends JPanel {

	/**
	 * Create the panel.
	 */
	public LobbySettingsDisplay(NormalLobbySettings settings, final IInLobbyController controller) {
		
		setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel judgePanel = new JPanel();
		add(judgePanel);
		
		JLabel lbljudgeLabel = new JLabel("Judge Mode:");
		judgePanel.add(lbljudgeLabel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(JudgeSetting.values()));
		comboBox.setSelectedItem(settings.getJudging());
		judgePanel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changeSettingJudgeMode((JudgeSetting)(comboBox.getSelectedItem()));
			}
		});
		
		JPanel rounds = new JPanel();
		add(rounds);
		
		JLabel lblNumberOfRounds = new JLabel("Number of Rounds:");
		rounds.add(lblNumberOfRounds);
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setValue(settings.getRounds());
		rounds.add(spinner);
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int num = (int) spinner.getValue();
				System.err.println("VALUE IS " + num);
				controller.changeSettingNumRounds((int)spinner.getValue());
			}
		});
		
		JPanel wins = new JPanel();
		add(wins);
		
		JLabel lblNumberOfWins = new JLabel("Number of Wins Required:");
		wins.add(lblNumberOfWins);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner_1.setValue(settings.getMaxWins());
		wins.add(spinner_1);
		
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				controller.changeSettingNumWins((int)spinner_1.getValue());
			}
		});
		
		JPanel time = new JPanel();
		add(time);
		
		JLabel lblDrawingTime = new JLabel("Drawing Time (Seconds):");
		time.add(lblDrawingTime);
		
		final JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(10, 10, 60, 5));
		spinner_2.setValue(settings.getDrawTime());
		time.add(spinner_2);
		
		spinner_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				controller.changeSettingDrawTime((int)spinner_2.getValue());
			}
		});

	}

}
