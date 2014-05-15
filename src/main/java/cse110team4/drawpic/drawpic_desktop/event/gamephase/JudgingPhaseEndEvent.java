package cse110team4.drawpic.drawpic_desktop.event.gamephase;

import cse110team4.drawpic.drawpic_core.event.Event;

/**
 * This event is called whenever the judging phase of the game session ends
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class JudgingPhaseEndEvent implements Event<PhaseEndListener> {
	
	public JudgingPhaseEndEvent() {
	}

	@Override
	public void notify(final PhaseEndListener listener) {
		listener.judgingPhaseEnd();
	}
}
