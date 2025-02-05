package edu.uca;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDateTime;


public class EventTest extends TestCase {
    public static Test suite() {
        return new TestSuite(EventTest.class);
    }

    public void testEventCompareTo() {
        Event event = new Event("newEvent", LocalDateTime.now());
        Event ev2 = new Event("newEvent2", LocalDateTime.now().plusDays(1));
        assertEquals(-1, event.compareTo(ev2));
    }
}
