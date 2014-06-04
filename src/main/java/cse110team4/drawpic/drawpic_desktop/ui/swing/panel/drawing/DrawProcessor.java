package cse110team4.drawpic.drawpic_desktop.ui.swing.panel.drawing;

import java.awt.Color;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;

import cse110team4.drawpic.drawpic_core.drawing.Blot;
import cse110team4.drawpic.drawpic_core.drawing.Line;
import cse110team4.drawpic.drawpic_desktop.ui.IGameDrawController;

public class DrawProcessor implements Runnable {
	private volatile boolean isRunning = true;
	
	private IGameDrawController controller;
	
	private Queue<MouseMove> toProcess;
	private MouseMove lastMove;
	
	public DrawProcessor() {
		this.toProcess = new ConcurrentLinkedQueue<MouseMove>();
		
		Executors.newSingleThreadExecutor().execute(this);
	}
	
	public void setController(IGameDrawController controller) {
		this.controller = controller;
	}
	
	public synchronized void addMove(MouseMove move) {
		toProcess.offer(move);
		notifyAll();
	}

	@Override
	public void run() {
		processMove();
	}
	
	synchronized void processMove() {
		MouseMove move;
		
		while (isRunning) {
			// Wait until somebody adds to the queue
			try {
				wait();
			} catch (InterruptedException e) {}
			
			while ((move = toProcess.poll()) != null) {
				if (move.firstPress) {
					controller.addStep(new Blot(move.to));
				} else {
					controller.addStep(new Line(move.to, lastMove.to));
				}

				lastMove = move;
			}
		}
	}
	
	public void kill() {
		isRunning = false;
	}

}
