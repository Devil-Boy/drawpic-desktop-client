package cse110team4.drawpic.drawpic_desktop.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcher {

	private Map<Class, List> eventListeners;
	
	public EventDispatcher() {
		eventListeners = new HashMap<Class, List>();
	}
	
	public <L> void register(Class<? extends Event<L>> eventClass, L listener) {
		List<L> listeners = listenersOf(eventClass);
		
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public <L> void unregister(Class<? extends Event<L>> eventClass, L listener) {
		List<L> listeners = listenersOf(eventClass);
		
		listeners.remove(listener);
	}
	
	private <L> List<L> listenersOf(Class<? extends Event<L>> eventClass) {
		List<L> listeners = eventListeners.get(eventClass);
		
		if (listeners == null) {
			listeners = new ArrayList<L>();
			eventListeners.put(eventClass, listeners);
		}
		
		return listeners;
	}
	
	public <L> void call(Event<L> event) {
		Class<Event<L>> eventClass = (Class<Event<L>>) event.getClass();
		
		for (L listener : listenersOf(eventClass)) {
			event.notify(listener);
		}
	}
}
