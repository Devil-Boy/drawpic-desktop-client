package cse110team4.drawpic.drawpic_desktop.ui;

import java.util.List;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings.JudgeSetting;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.server.JMSServerConnection;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public class TestApp {

	public static void main(String[] args) {
		ServerConnection sc = DesktopBeans.getContext().getBean(JMSServerConnection.class);
		
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		System.err.println(sc.connect());
		
		//System.err.println(sc.login("R2D2"));
		
		//System.err.println(sc.pollLobbyList());
		//System.err.println(sc.joinLobby(sc.getLobbyList().get(0)));
		
		//System.err.println(sc.createLobby());
		
		/*
		NormalLobbySettings newSettings = new NormalLobbySettings();
		newSettings.setJudging(JudgeSetting.IN_ORDER);
		newSettings.setDrawTime(10000);
		newSettings.setMaxWins(99999);
		newSettings.setRounds(-8);
		System.err.println(sc.changeLobbySettings(newSettings));*/
	}
	
	public static void printLobbies(List<Lobby> lobbyList) {
		if (lobbyList.isEmpty()) {
			System.out.println("No lobbies");
		} else {
			for (Lobby lobby : lobbyList) {
				System.out.println("Lobby: " + lobby.getHost());
				System.out.println("Settings:");
				System.out.println("\tJudging: " + ((NormalLobbySettings) lobby.getSettings()).getJudging());
				System.out.println("\tRounds: " + ((NormalLobbySettings) lobby.getSettings()).getRounds());
				System.out.println("\tMax wins: " + ((NormalLobbySettings) lobby.getSettings()).getMaxWins());
				System.out.println("\tDraw time: " + ((NormalLobbySettings) lobby.getSettings()).getDrawTime());
			}
		}
	}
}
