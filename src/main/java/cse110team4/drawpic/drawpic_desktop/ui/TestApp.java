package cse110team4.drawpic.drawpic_desktop.ui;

import cse110team4.drawpic.drawpic_desktop.DesktopBeans;
import cse110team4.drawpic.drawpic_desktop.server.JMSServerConnection;
import cse110team4.drawpic.drawpic_desktop.server.ServerConnection;

public class TestApp {

	public static void main(String[] args) {
		ServerConnection sc = DesktopBeans.getContext().getBean(JMSServerConnection.class);
		
		System.err.println(sc.connect());
		
		System.err.println(sc.login("AK"));
	}
}
