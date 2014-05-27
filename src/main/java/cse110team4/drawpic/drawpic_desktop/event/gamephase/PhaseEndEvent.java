package cse110team4.drawpic.drawpic_desktop.event.gamephase;

import cse110team4.drawpic.drawpic_core.gamesession.GamePhase;
import cse110team4.drawpic.drawpic_desktop.event.listener.PhaseListener;

/**
 * This event is called whenever a phase ends
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class PhaseEndEvent extends PhaseEvent {

	public PhaseEndEvent(GamePhase phase) {
		super(phase);
	}

	@Override
	public void notify(PhaseListener listener) {
		listener.phaseEnd(this);
	}

}
