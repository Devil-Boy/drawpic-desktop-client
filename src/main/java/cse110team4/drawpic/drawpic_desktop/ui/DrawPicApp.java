package cse110team4.drawpic.drawpic_desktop.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.event.EventDispatcher;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientLobbySetEvent;
import cse110team4.drawpic.drawpic_desktop.event.client.ClientUsernameSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.listener.ClientListener;
import cse110team4.drawpic.drawpic_desktop.server.JMSServerConnection;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;
import cse110team4.drawpic.drawpic_desktop.ui.console.ConsoleUI;
import cse110team4.drawpic.drawpic_desktop.ui.swing.SwingUI;

/**
 * This is the main class (starting point) of our desktop application
 * 
 * @author Devil Boy (Kervin Sam)
 *
 */
public class DrawPicApp implements ClientListener{
	
	ServerConnection server;
	
	public DrawPicApp(){
		server = DesktopBeans.getContext().getBean(JMSServerConnection.class);
		DesktopBeans.getContext().getBean(EventDispatcher.class).register(ClientUsernameSetEvent.class, this);
		DesktopBeans.getContext().getBean(EventDispatcher.class).register(ClientLobbySetEvent.class, this);
	}
	
	public enum ClientPhase{
		LOGIN,
		LOBBY_OPTION,
		LOBBY_BROWSING,
		IN_LOBBY,
		GAME_DRAW,
		GAME_JUDGE,
		GAME_RESULTS
	}
	
	ClientPhase currentPhase;
	
    public static void main(String[] args) {
    	new DrawPicApp();
    	
    }

	@Override
	public void usernameSet(ClientUsernameSetEvent event) {
		if(event.getUsername() != null){
			this.currentPhase = ClientPhase.LOBBY_OPTION;
		}
	}

	@Override
	public void lobbySet(ClientLobbySetEvent event) {
		if(event.getLobby() != null){
			this.currentPhase = ClientPhase.IN_LOBBY;
		}
		
	}
}
