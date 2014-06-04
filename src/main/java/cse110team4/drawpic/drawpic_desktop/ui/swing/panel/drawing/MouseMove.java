package cse110team4.drawpic.drawpic_desktop.ui.swing.panel.drawing;

import cse110team4.drawpic.drawpic_core.drawing.Location;

public class MouseMove {
	Location to;
	boolean firstPress;
	
	public MouseMove(Location to, boolean firstPress) {
		this.to = to;
		this.firstPress = firstPress;
	}
}
