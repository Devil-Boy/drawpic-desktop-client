package cse110team4.drawpic.drawpic_desktop.ui.swing.panel.drawing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cse110team4.drawpic.drawpic_core.drawing.Location;
import cse110team4.drawpic.drawpic_desktop.ui.IGameDrawController;
import cse110team4.drawpic.drawpic_desktop.ui.swing.panel.SwingView;

public class CanvasView extends SwingView {
	private static final Color BG_COLOR = Color.WHITE;
	private static final int PREFERRED_WIDTH = 800;
	private static final int PREFERRED_HEIGHT = 800;
	
	private DrawProcessor drawProcessor;
	
	public CanvasView(final DrawProcessor drawProcessor) {
		super(BG_COLOR, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		
		this.drawProcessor = drawProcessor;
	
		// Set listeners
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				drawProcessor.addMove(new MouseMove(new Location(e.getX(), e.getY()), true));
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				drawProcessor.addMove(new MouseMove(new Location(e.getX(), e.getY()), false));
			}
		});
	}
}
