package cse110team4.drawpic.drawpic_desktop.event.game;

import cse110team4.drawpic.drawpic_core.gamesession.GamePhase;
import cse110team4.drawpic.drawpic_core.player.GameData;
import cse110team4.drawpic.drawpic_desktop.event.listener.GameListener;

/**
 * This event is called when the game phase changes
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class GamePhaseSetEvent extends GameEvent {

	public GamePhaseSetEvent(GameData gameData) {
		super(gameData);
	}
	
	/**
	 * Gets the newly set phase
	 * @return The game phase set to
	 */
	public GamePhase getPhase() {
		return getGameData().getPhase();
	}

	@Override
	public void notify(GameListener listener) {
		listener.phaseSet(this);
	}

}
