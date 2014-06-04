package cse110team4.drawpic.drawpic_desktop.event.game;

import cse110team4.drawpic.drawpic_core.player.GameData;
import cse110team4.drawpic.drawpic_desktop.event.listener.GameListener;

public class GameJudgeSetEvent extends GameEvent {

	public GameJudgeSetEvent(GameData gameData) {
		super(gameData);
	}
	
	/**
	 * Gets the newly set judge
	 * @return The judge set to
	 */
	public String getJudge() {
		return getGameData().getJudge();
	}

	@Override
	public void notify(GameListener listener) {
		listener.judgeSet(this);
	}
}
