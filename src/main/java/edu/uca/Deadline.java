package edu.uca;

import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    //automatically assume that the task has not been done
    private boolean wasCompleted = false; // named wasCompleted instead of complete for readability

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
}