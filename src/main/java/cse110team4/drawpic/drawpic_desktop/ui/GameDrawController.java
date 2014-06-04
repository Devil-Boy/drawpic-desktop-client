package cse110team4.drawpic.drawpic_desktop.ui;

import java.awt.Color;

import cse110team4.drawpic.drawpic_core.drawing.Eraser;
import cse110team4.drawpic.drawpic_core.drawing.Pen;
import cse110team4.drawpic.drawpic_core.drawing.Step;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public class GameDrawController implements IGameDrawController {

	IGameDrawView view;

	ServerConnection connection;
	
	public GameDrawController (ServerConnection connection, IGameDrawView view){
		this.connection = connection;
		this.view = view;
	}
	
	@Override
	public void addStep(Step step) {
		connection.addStep(step);
	}

	@Override
	public void changeToPen(Color color, int size) {
		addStep(new Pen(color, size));
	}

	@Override
	public void erase() {
		addStep(new Eraser());
	}
}
