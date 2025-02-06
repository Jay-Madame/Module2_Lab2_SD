package edu.uca;

import java.time.LocalDateTime;

public class Event implements Comparable<Event> {

    // Event details
    private String eventName;
    private LocalDateTime eventDate;

    public Event(String name, LocalDateTime dateTime) {
        this.eventName = name;
        this.eventDate = dateTime;
    }

    public String getName() {
        return eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.eventDate = dateTime;
    }

    public void setName(String eventName) {
        this.eventName = eventName;
    }

    public int compareTo(Event e) {
        return this.eventDate.compareTo(e.eventDate);
    }
}