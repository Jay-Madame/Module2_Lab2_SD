package edu.uca;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reminder extends Event {

    //fields
    protected Duration timeBefore;
    protected Event event;

    public Reminder(String reminderName, LocalDateTime eventTime) {
        super(reminderName, eventTime); // creates an event reminder
    }

    public LocalDateTime getDateTime() {
        return super.getEventDate(); // get event date from Event
    }

    public String getName() {
        return super.getName(); // get event name from Event
    }
}