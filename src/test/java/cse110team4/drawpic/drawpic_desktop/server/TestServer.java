package cse110team4.drawpic.drawpic_desktop.server;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class will test the server interface. Does not test specific implementation
 * @author Kirk
 *
 */
public class TestServer {
	
	Server server;
	
	@Test
	public void testLogin() {
		assertNull(server.login("Bob"));
		assertNotNull(server.login("Bob")); //adding the same name
		assertNull(server.login(" "));
	}
	
	@Test
	public void testCreateLobby(){
		assertNotNull(server.createLobby());
	}
	
	@Test
	public void testOpenLobbies(){
		assertNotNull(server.openLobbies());
	}
	
	@Test
	public void testGetLobby(){
		assertNotNull(server.getLobby("Bob"));
	}
	
	@Test
	public void testJoinLobby(){
		server.login("Steve");
		assertNotNull(server.joinLobby((server.getLobby("Bob"))));
	}

}
