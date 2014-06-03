package cse110team4.drawpic.drawpic_desktop.ui;

import java.util.ArrayList;
import java.util.List;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.server.JMSServerConnection;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public class TestApp {

	public static void main(String[] args) {
		ServerConnection sc = DesktopBeans.getContext().getBean(JMSServerConnection.class);
		
		System.err.println(sc.connect());
		
		System.err.println(sc.login("AK34277"));
		
		List<Lobby> lobbyList = new ArrayList<Lobby>();
		System.err.println(sc.getLobbies(lobbyList));
		
		if (lobbyList.isEmpty()) {
			System.out.println("No lobbies");
		} else {
			for (Lobby lobby : lobbyList) {
				System.out.println("Lobby: " + lobby.getHost());
			}
		}
	}
}
