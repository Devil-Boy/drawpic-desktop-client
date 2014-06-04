package cse110team4.drawpic.drawpic_desktop.event.game;

import cse110team4.drawpic.drawpic_core.player.GameData;
import cse110team4.drawpic.drawpic_desktop.event.Event;
import cse110team4.drawpic.drawpic_desktop.event.listener.GameListener;

/**
 * This represents an event relating to game phases
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public abstract class GameEvent implements Event<GameListener> {

	private GameData gameData;
	
	public GameEvent(GameData gameData) {
		this.gameData = gameData;
	}
	
	/**
	 * Gets the game data
	 * @return The object storing the data
	 */
	public GameData getGameData() {
		return gameData;
	}
}
