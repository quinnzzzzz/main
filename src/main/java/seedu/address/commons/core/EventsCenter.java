package seedu.address.commons.core;

import java.util.logging.Logger;

import com.google.common.eventbus.EventBus;

/**
 * Manages the event dispatching of the app.
 */
public class EventsCenter {
    private static final Logger logger = LogsCenter.getLogger(EventsCenter.class);
    private static EventsCenter instance;
    private final EventBus eventBus;

    private EventsCenter() {
        eventBus = new EventBus();
    }

    public static EventsCenter getInstance() {
        if (instance == null) {
            instance = new EventsCenter();
        }
        return instance;
    }

    public static void clearSubscribers() {
        instance = null;
    }

    public void registerHandler(Object handler) {
        eventBus.register(handler);
    }

    
}
