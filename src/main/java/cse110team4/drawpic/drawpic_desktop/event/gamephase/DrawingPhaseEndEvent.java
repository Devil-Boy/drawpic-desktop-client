package cse110team4.drawpic.drawpic_desktop.event.gamephase;

import cse110team4.drawpic.drawpic_desktop.event.Event;

/**
 * This event is called whenever the drawing phase of a game session ends
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class DrawingPhaseEndEvent implements Event<PhaseEndListener> {
	
	public DrawingPhaseEndEvent() {
	}

	@Override
	public void notify(final PhaseEndListener listener) {
		listener.drawingPhaseEnd();
	}
}
