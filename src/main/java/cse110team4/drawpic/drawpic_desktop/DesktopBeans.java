package cse110team4.drawpic.drawpic_desktop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cse110team4.drawpic.drawpic_core.CoreBeans;

public class DesktopBeans {

	public static final String BEANS_CONFIG = "spring/desktop/beans.xml";

	private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {BEANS_CONFIG}, CoreBeans.getContext());
	
	public static ApplicationContext getContext() {
		return context;
	}
}
