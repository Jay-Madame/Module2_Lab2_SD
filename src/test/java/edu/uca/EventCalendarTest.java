package edu.uca;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.Duration;
import java.time.LocalDateTime;


public class EventCalendarTest extends TestCase {
    public static Test suite() {
        return new TestSuite(EventCalendarTest.class);
    }

    // test in Event
    public void testEventCompareTo() {
        Event event = new Event("newEvent", LocalDateTime.now());
        Event ev2 = new Event("newEvent2", LocalDateTime.now().plusDays(1));
        assertEquals(-1, event.compareTo(ev2));
    }

    // test in Deadline
    public void testDeadlineIsComplete() {
        Deadline deadline = new Deadline("newDeadline", LocalDateTime.now());
        //test if auto-false
        assertFalse(deadline.isComplete());

        //test if complete works
        deadline.complete();
        assertTrue(deadline.isComplete());
    }

    // tests in Meeting
    public void testMeetingIsComplete() {
        Meeting meeting = new Meeting("newMeeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        // test auto-false default
        assertFalse(meeting.isComplete());

        // test complete
        meeting.complete();
        assertTrue(meeting.isComplete());
    }

    // check hour comparison, cut off at minutes to avoid nanosecond
    public void testMeetingGetDuration() {
        Meeting meeting = new Meeting("newMeeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        assertEquals(Duration.ofHours(1).toMinutes(), meeting.getDuration().toMinutes());
    }

}
