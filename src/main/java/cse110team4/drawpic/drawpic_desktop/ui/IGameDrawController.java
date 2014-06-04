package cse110team4.drawpic.drawpic_desktop.ui;

import java.awt.Color;

import cse110team4.drawpic.drawpic_core.drawing.Step;

public interface IGameDrawController {

	public void addStep(Step step);
	
	public void changeToPen(Color color, int size);
	
	public void erase();
}
