package edu.uca;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

public class Deadline extends Event implements Completable {
    //automatically assume that the task has not been done
    private boolean wasCompleted = false; // named wasCompleted instead of complete for readability
    ArrayList<Reminder> reminders = new ArrayList<>();

    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline); // creates an event
    }

    @Override
    public void complete() {
        wasCompleted = true;
    }

    @Override
    public boolean isComplete() {
        return wasCompleted;
    }

    void addReminder(Duration timeBefore) { // change to duration
        LocalDateTime reminderTime = this.getEventDate().minus(timeBefore);
        Reminder deadlineReminder = new Reminder(this.getName(), reminderTime);
        reminders.add(deadlineReminder);
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }
}