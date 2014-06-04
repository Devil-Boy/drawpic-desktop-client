package cse110team4.drawpic.drawpic_desktop.event.server;

import cse110team4.drawpic.drawpic_desktop.event.Event;
import cse110team4.drawpic.drawpic_desktop.event.listener.ServerListener;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public abstract class ServerEvent implements Event<ServerListener> {

	private ServerConnection connection;
	
	public ServerEvent(ServerConnection connection) {
		this.connection = connection;
	}
	
	/**
	 * Gets the server connection involved in this event
	 * @return The server connection
	 */
	public ServerConnection getServerConnection() {
		return connection;
	}
}
