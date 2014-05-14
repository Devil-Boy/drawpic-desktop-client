package cse110team4.drawpic.drawpic_desktop.event.gamephase;

/**
 * This is an interface that allows for listening to phase endings
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public interface PhaseEndListener {

	public void drawingPhaseEnd();
	
	public void judgingPhaseEnd();
	
	public void resultsPhaseEnd();
}
