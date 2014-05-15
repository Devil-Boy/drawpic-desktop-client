package cse110team4.drawpic.drawpic_desktop.event.gamephase;

import cse110team4.drawpic.drawpic_core.event.Event;

/**
 * This event is called whenever the results-showing phase of a game ends
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class ResultsPhaseEndEvent implements Event<PhaseEndListener> {

	public ResultsPhaseEndEvent() {
	}

	@Override
	public void notify(final PhaseEndListener listener) {
		listener.resultsPhaseEnd();
	}
}
