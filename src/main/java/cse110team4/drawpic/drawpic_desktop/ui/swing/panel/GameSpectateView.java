package cse110team4.drawpic.drawpic_desktop.ui.swing.panel;

import java.awt.Color;

import cse110team4.drawpic.drawpic_desktop.ui.IGameSpectateController;
import cse110team4.drawpic.drawpic_desktop.ui.IGameSpectateView;

public class GameSpectateView extends SwingView implements IGameSpectateView {
	private static final Color BG_COLOR = new Color(0x00, 0x9c, 0xff);
	private static final int PREFERRED_WIDTH = 300;
	private static final int PREFERRED_HEIGHT = 300;
	
	private IGameSpectateController controller;
	
	public GameSpectateView() {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		addContent();
	}
	
	private void addContent() {
		//Two buttons I guess
		//cycle through canvases
		//then just need to display the canvas (??)
	}
	
	@Override
	public void setController(IGameSpectateController controller) {
		this.controller = controller;
	}

}
