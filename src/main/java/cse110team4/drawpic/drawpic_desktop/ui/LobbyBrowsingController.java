package cse110team4.drawpic.drawpic_desktop.ui;

import javax.swing.JOptionPane;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.DrawPicApp.ClientPhase;

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
		String result = connection.joinLobby(lobby);

		if(result != null){
			JOptionPane.showMessageDialog(null, "Error joining Lobby:\n" + result);
		}
	}

	@Override
	public void goBack() {
		DesktopBeans.getContext().getBean(DrawPicApp.class).setCurrentPhase(ClientPhase.LOBBY_OPTION);
	}

	@Override
	public void refresh() {
		connection.getLobbyList();

	}

}
