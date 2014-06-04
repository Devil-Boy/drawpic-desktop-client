package cse110team4.drawpic.drawpic_desktop.event.listener;

import cse110team4.drawpic.drawpic_desktop.event.game.GameJudgeSetEvent;
import cse110team4.drawpic.drawpic_desktop.event.game.GamePhaseSetEvent;

/**
 * This is an interface that allows for listening to phase endings
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public interface GameListener {

	public void phaseSet(GamePhaseSetEvent event);
	
	public void judgeSet(GameJudgeSetEvent event);
}
