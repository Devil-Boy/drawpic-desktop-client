package cse110team4.drawpic.drawpic_desktop;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLobby {

	@Test
	public void testMaxPlayers() {
		DrawLobby lob = new DrawLobby("Bob");
		assertEquals(4, lob.maxPlayers());
	}

}
