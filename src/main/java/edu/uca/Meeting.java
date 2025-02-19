package edu.uca;

import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable {
    private LocalDateTime meetingEndTime;
    private String location;
    private boolean isMeetingOver; // complete is renamed for readability

    public Meeting(String name, LocalDateTime startTime, LocalDateTime endTime, String meetingLocation) {
        super(name, startTime);// creates an event, only takes startTime
        location = meetingLocation;
        this.meetingEndTime = endTime;
    }

    // implement completable
    @Override
    public void complete() {
        this.isMeetingOver = true;
    }

    @Override
    public boolean isComplete() {
        return isMeetingOver;
    }

    // accessors
    public LocalDateTime getEndTime() {
        return meetingEndTime;
    }

    public Duration getDuration() {
        return Duration.between(this.getEventDate(), this.getEndTime());
    }

    String getLocation() {
        return location;
    }

    // mutators
    void setLocation(String meetingLocation) {
        this.location = meetingLocation;
    }

    void setEndTime(LocalDateTime endTime) {
        this.meetingEndTime = endTime;
    }
}