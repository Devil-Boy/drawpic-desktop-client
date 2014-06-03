package cse110team4.drawpic.drawpic_desktop.ui;

import javax.swing.JOptionPane;

import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

/**
 * 
 * @author Kirk
 *
 */
public class LogInController implements ILogInController {

	ILogInView view;

	ServerConnection connection;
	
	public LogInController(ServerConnection connection){
		this.connection = connection;
	}
	
	@Override
	public void setView(ILogInView view) {
		this.view = view;
	}

	@Override
	public void login() {
		String givenUsername = view.getInput();
		
		String loginResult = "";
		if ((loginResult = connection.login(givenUsername)) != null) {
			JOptionPane.showMessageDialog(null, "Error with login:\n" + loginResult);
		}
	}

}
