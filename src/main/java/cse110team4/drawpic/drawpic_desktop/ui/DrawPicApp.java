package cse110team4.drawpic.drawpic_desktop.ui;

import java.util.Arrays;

import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientUsernameSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

/**
 * This is the main class (starting point) of our desktop application
 * 
 * @author Devil Boy (Kervin Sam)
 *
 */
public class DrawPicApp implements ClientListener{
	
	ServerConnection connection;
	
	UIDisplayer displayer;

	public DrawPicApp(ServerConnection connection){
		this.connection = connection;
	}
	
	public void setDisplayer(UIDisplayer displayer) {
		this.displayer = displayer;
	}
	
	private void registerEvents() {
		DesktopBeans.getContext().getBean(EventDispatcher.class).register(ClientUsernameSetEvent.class, this);
		DesktopBeans.getContext().getBean(EventDispatcher.class).register(ClientLobbySetEvent.class, this);
	}
	
	public void start() {
		registerEvents();
		
		connection.connect();
		
		displayer.viewPhase(ClientPhase.LOGIN);
	}
	
	private ClientPhase currentPhase;
	
    public static void main(String[] args) {
    	DrawPicApp app = DesktopBeans.getContext().getBean(DrawPicApp.class);
    	if (Arrays.asList(args).contains("-console")) {
    		// TODO: Not yet implemented
    	}
    	else {
    		app.setDisplayer(DesktopBeans.getContext().getBean(SwingDisplayer.class));
    	}
    	app.start();
    }

	@Override
	public void usernameSet(ClientUsernameSetEvent event) {
		if(event.getUsername() != null){
			this.setCurrentPhase(ClientPhase.LOBBY_OPTION);
		}
	}

	@Override
	public void lobbySet(ClientLobbySetEvent event) {
		if(event.getLobby() != null){
			if (event.getLobby().getHost() == connection.getClientData().getUsername()) {
				this.setCurrentPhase(ClientPhase.IN_LOBBY_HOST);
			}
			else {
				this.setCurrentPhase(ClientPhase.IN_LOBBY_PLAYER);
			}
		}
		
	}

	public ClientPhase getCurrentPhase() {
		return currentPhase;
	}

	public void setCurrentPhase(ClientPhase currentPhase) {
		displayer.viewPhase(this.currentPhase = currentPhase);
	}
}
