package cse110team4.drawpic.drawpic_desktop.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This handles the registering and notifying of event listeners
 *
 * @author Devil Boy (Kervin Sam)
 *
 */
public class EventDispatcher {

	/**
	 * The map of registered listeners
	 */
	private Map<Class, List> eventListeners;
	
	/**
	 * Constructs a new EventDispatcher
	 */
	public EventDispatcher() {
		eventListeners = new HashMap<Class, List>();
	}
	
	/**
	 * Registers a listener to listen for a specific event
	 * @param eventClass The event to listen to
	 * @param listener The listener to register
	 */
	public <L> void register(Class<? extends Event<L>> eventClass, L listener) {
		List<L> listeners = listenersOf(eventClass);
		
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
	}
	
	/**
	 * Unregisters a listener
	 * @param eventClass The class the listener was listening to
	 * @param listener The listener to unregister
	 */
	public <L> void unregister(Class<? extends Event<L>> eventClass, L listener) {
		List<L> listeners = listenersOf(eventClass);
		
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}
	
	/**
	 * Gets the listeners of a certain event
	 * @param eventClass The event to find the listeners for
	 * @return A list containing listeners
	 */
	private <L> List<L> listenersOf(Class<? extends Event<L>> eventClass) {
		synchronized (eventListeners) {
			List<L> listeners = eventListeners.get(eventClass);
			
			if (listeners == null) {
				listeners = new ArrayList<L>();
				eventListeners.put(eventClass, listeners);
			}
			
			return listeners;
		}
	}
	
	/**
	 * Notifies all the listeners of the given event
	 * @param event The event to distribute
	 */
	public <L> void call(Event<L> event) {
		Class<Event<L>> eventClass = (Class<Event<L>>) event.getClass();
		
		for (L listener : listenersOf(eventClass)) {
			event.notify(listener);
		}
	}
}
