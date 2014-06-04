package cse110team4.drawpic.drawpic_desktop.ui;

import javax.swing.JOptionPane;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings.JudgeSetting;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.lobby.LobbySettingsChangedEvent;
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
			DesktopBeans.getContext().getBean(DrawPicApp.class).setCurrentPhase(ClientPhase.LOBBY_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, "Error with leaving lobby:\n" + leaveResult);
		}
	}

	@Override
	public void startGame() {
		String startResult = connection.startGame();
		if (startResult != null) {
			JOptionPane.showMessageDialog(null, "Error starting game:\n" + startResult);
		}
	}

	@Override
	public void changeSettingJudgeMode(JudgeSetting setting) {
		Lobby lobby = DesktopBeans.getContext().getBean(ServerConnection.class).getClientData().getLobby();
		NormalLobbySettings newSettings = (NormalLobbySettings) lobby.getSettings().clone();
		newSettings.setJudging(setting);
		
		String result = connection.changeLobbySettings(newSettings);
		if (result == null) {
			lobby.setSettings(newSettings);
			
			// Send the event
			DesktopBeans.getContext().getBean(EventDispatcher.class).call(new LobbySettingsChangedEvent(lobby));
		} else {
			JOptionPane.showMessageDialog(null, "Error with settings change:\n" + result);
		}
	}

	@Override
	public void changeSettingNumRounds(int num) {
		Lobby lobby = DesktopBeans.getContext().getBean(ServerConnection.class).getClientData().getLobby();
		NormalLobbySettings newSettings = (NormalLobbySettings) lobby.getSettings().clone();
		newSettings.setRounds(num);
		
		String result = connection.changeLobbySettings(newSettings);
		if (result == null) {
			lobby.setSettings(newSettings);
			
			// Send the event
			DesktopBeans.getContext().getBean(EventDispatcher.class).call(new LobbySettingsChangedEvent(lobby));
		} else {
			JOptionPane.showMessageDialog(null, "Error with settings change:\n" + result);
		}
	}

	@Override
	public void changeSettingNumWins(int num) {
		Lobby lobby = DesktopBeans.getContext().getBean(ServerConnection.class).getClientData().getLobby();
		NormalLobbySettings newSettings = (NormalLobbySettings) lobby.getSettings().clone();
		newSettings.setMaxWins(num);
		
		String result = connection.changeLobbySettings(newSettings);
		if (result == null) {
			lobby.setSettings(newSettings);
			
			// Send the event
			DesktopBeans.getContext().getBean(EventDispatcher.class).call(new LobbySettingsChangedEvent(lobby));
		} else {
			JOptionPane.showMessageDialog(null, "Error with settings change:\n" + result);
		}
	}

	@Override
	public void changeSettingDrawTime(int time) {
		Lobby lobby = DesktopBeans.getContext().getBean(ServerConnection.class).getClientData().getLobby();
		NormalLobbySettings newSettings = (NormalLobbySettings) lobby.getSettings().clone();
		newSettings.setDrawTime(time);
		
		String result = connection.changeLobbySettings(newSettings);
		if (result == null) {
			lobby.setSettings(newSettings);
			
			// Send the event
			DesktopBeans.getContext().getBean(EventDispatcher.class).call(new LobbySettingsChangedEvent(lobby));
		} else {
			JOptionPane.showMessageDialog(null, "Error with settings change:\n" + result);
		}
	}
}
