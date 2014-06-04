package cse110team4.drawpic.drawpic_desktop.ui;

import javax.swing.JOptionPane;

import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings.JudgeSetting;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public class InLobbyController implements IInLobbyController {

	IInLobbyView view;
	
	ServerConnection connection;
	
	public InLobbyController(ServerConnection connection, IInLobbyView view) {
		this.connection = connection;
		this.view = view;
	}
	
	@Override
	public void leaveLobby() {
		String leaveResult = connection.leaveLobby();
		if (leaveResult == null) {
			DesktopBeans.getContext().getBean(DrawPicApp.class).setCurrentPhase(ClientPhase.LOBBY_BROWSING);
		} else {
			JOptionPane.showMessageDialog(null, "Error with leaving lobby:\n" + leaveResult);
		}
	}

	@Override
	public boolean startGame() {
		String startResult = connection.startGame();
		if (startResult == null) {
			
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Error starting game:\n" + startResult);
			return false;
		}
	}

	@Override
	public void changeSettingJudgeMode(JudgeSetting setting) {

	}

	@Override
	public boolean changeSettingNumRounds(int num) {
		return false;
	}

	@Override
	public boolean changeSettingNumWins(int num) {
		return false;
	}

	@Override
	public void changeSettingDrawTime(int time) {

	}
	
	public String[] getPlayers() {
		return connection.getClientData().getLobby().getPlayers();
	}

}
