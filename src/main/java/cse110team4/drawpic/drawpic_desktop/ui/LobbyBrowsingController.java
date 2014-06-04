package cse110team4.drawpic.drawpic_desktop.ui;

import javax.swing.JOptionPane;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.ClientPhase;

/**
 * @author Kirk
 *
 */
public class LobbyBrowsingController implements ILobbyBrowsingController {

	ILobbyBrowsingView view;
	
	ServerConnection connection;
	
	/**
	 * 
	 */
	public LobbyBrowsingController(ServerConnection connection, ILobbyBrowsingView view) {
		this.view = view;
		this.connection = connection;
	}

	@Override
	public void joinLobby(Lobby lobby) {
		// Try to join the lobby
		String joinResult = "";
		if ((joinResult = connection.joinLobby(lobby)) == null) {
			DesktopBeans.getContext().getBean(DrawPicApp.class).setCurrentPhase(ClientPhase.IN_LOBBY_PLAYER);
		} else {
			JOptionPane.showMessageDialog(null, "Error with join:\n" + joinResult);
			
			// Refresh the lobby list
			refresh();
		} 
	}

	@Override
	public void goBack() {
		DesktopBeans.getContext().getBean(DrawPicApp.class).setCurrentPhase(ClientPhase.LOBBY_OPTION);
	}

	@Override
	public void refresh() {
		String result = connection.pollLobbyList();
		if(result != null){
			JOptionPane.showMessageDialog(null, "Error with refreshing lobbies:\n" + result);
		}
	}

}
