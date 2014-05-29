package cse110team4.drawpic.drawpic_desktop.server;

import cse110team4.drawpic.drawpic_core.protocol.packet.Packet;

public class ResponseNotifier {

	private byte responseID;
	private Packet response;
	
	private boolean responseReceived;
	
	public ResponseNotifier(byte responseID) {
		this.responseID = responseID;
	}
	
	public byte getResponseID() {
		return responseID;
	}
	
	public synchronized Packet getResponse(int timeout) {
		while (response == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		responseReceived = true;
		return response;
	}
	
	public synchronized void receivedResponse(Packet packet) {
		response = packet;
		notify();
	}
	
	public boolean getResponseReceived() {
		return responseReceived;
	}
}
