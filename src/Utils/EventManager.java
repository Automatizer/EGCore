package Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class EventManager {

	private HashMap<String, Collection<EventListener>> listeners = new HashMap<String, Collection<EventListener>>();
	
	public void registerListener(String type, EventListener el) {
		if(listeners.containsKey(type)) {
			Collection<EventListener> c = listeners.get(type);
			c.add(el);
			listeners.put(type, c);
		}else {
			Collection<EventListener> c = new ArrayList<EventListener>();
			c.add(el);
			listeners.put(type, c);
		}
	}
	
	public void callAll(String type) {
		Collection<EventListener> c = listeners.get(type);
		if((!c.isEmpty()) || (c != null)) {
			for(EventListener el : c) {
				el.listener();
			}
		}
	}
	
}
