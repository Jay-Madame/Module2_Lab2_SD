package edu.uca;

import java.time.Duration;
import java.time.LocalDateTime;

public enum Urgency {
    DISTANT, IMMINENT, OVERDUE;

    // Static field for threshold of imminence
    private static Duration thresholdOfImminence = Duration.ofHours(24); // Default threshold: 24 hours

    /**
     * Sets the threshold of imminence.
     *
     * @param d The new threshold duration.
     */
    public static void setThresholdOfImminence(Duration d) {
        thresholdOfImminence = d;
    }

    /**
     * Returns the urgency level of an event based on the event's dateTime.
     *
     * @param eventDateTime The date and time of the event.
     * @return The urgency level (DISTANT, IMMINENT, or OVERDUE).
     */
    public static Urgency getUrgency(LocalDateTime eventDateTime) {
        LocalDateTime now = LocalDateTime.now();

        // If the event is in the future
        if (eventDateTime.isAfter(now)) {
            Duration timeUntilEvent = Duration.between(now, eventDateTime);

            // Check if the event is distant or imminent
            if (timeUntilEvent.compareTo(thresholdOfImminence) > 0) {
                return DISTANT;
            } else {
                return IMMINENT;
            }
        }

        // If the event is in the past
        return OVERDUE;
    }
}
