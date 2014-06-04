package cse110team4.drawpic.drawpic_desktop.ui;

import cse110team4.drawpic.drawpic_core.player.Lobby;

/**
 * @author Kirk
 *
 */
public interface ILobbyBrowsingController {

	public void joinLobby(Lobby lobby);
	
	public void goBack();
	
	public void refresh();
}
