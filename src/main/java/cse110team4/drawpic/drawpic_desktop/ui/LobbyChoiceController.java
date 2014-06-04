package cse110team4.drawpic.drawpic_desktop.ui;

import javax.swing.JOptionPane;

import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

/**
 * @author Kirk
 *
 */
public class LobbyChoiceController implements ILobbyChoiceController {

	ILobbyChoiceView view;
	
	ServerConnection connection;
	
	public LobbyChoiceController(ServerConnection connection, ILobbyChoiceView view){
		this.connection = connection;
		this.view = view;
	}

	@Override
	public void createLobby() {
		String connect = connection.createLobby();
		if(connect != null){
			JOptionPane.showMessageDialog(null, "Error creating Lobby:\n" + connect);
		}
		
	}

	@Override
	public void joinLobby() {
		String connect = connection.pollLobbyList();
		if(connect != null){
			JOptionPane.showMessageDialog(null, "Error retrieving Lobby list:\n" + connect);
		}
	}

}
