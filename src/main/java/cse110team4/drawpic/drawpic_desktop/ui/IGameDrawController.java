package cse110team4.drawpic.drawpic_desktop.ui;

import java.awt.Color;

import cse110team4.drawpic.drawpic_core.drawing.Location;

public interface IGameDrawController {

	/**
	 * used to store and transfer location to server
	 * @param location
	 */
	public void markLocation(Location location);
	
	public void changeColor(Color color);
	
	public void erase ();
	
	public void changeSize (int size);
}
