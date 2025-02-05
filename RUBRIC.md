# Object-Oriented Programming with Java
## Spring 2025
**Jon Baarsch**  
**Lab Two**  
**Due: Feb 15 end of day**

---

## 1. Event Calendar

Create an application that represents an event calendar. You want the calendar to be able to add and remove events of different types.

### Event (Abstract Class, implements `Comparable<Event>`)
- **Constructor**  
  `Event(name: String, dateTime: LocalDateTime)` (optional)
- **Fields**
    - `name: String` – Name of the event.
    - `dateTime: LocalDateTime` – The time and date the event starts.
- **Methods**
    - `getName(): String` – Abstract method that returns the name.
    - `getDateTime(): LocalDateTime` – Returns the dateTime.
    - `setDateTime(dateTime: LocalDateTime): void` – Sets the dateTime.
    - `setName(name: String): void` – Sets the name of the Event.
    - `compareTo(Event e): int` – Compares the date of this Event to another and returns:
        - A positive integer if this event comes later.
        - A negative integer if this event comes earlier.
        - `0` if the two events have the same dateTime.

### Completable (Interface)
- **Methods**
    - `complete(): void`
    - `isComplete(): boolean`

### Deadline (Class extends `Event`, implements `Completable`)
- **Constructor**  
  `Deadline(name: String, deadline: LocalDateTime)`
- **Fields**
    - `complete: boolean` – Tracks whether the task is complete.
- **Methods**
    - `complete(): void` – Sets `complete` to `true`.
    - `isComplete(): boolean` – Returns the `complete` status.

### Meeting (Class extends `Event`, implements `Completable`)
- **Constructor**  
  `Meeting(name: String, start: LocalDateTime, end: LocalDateTime, location: String)`
- **Fields**
    - `endDateTime: LocalDateTime` – The time the meeting ends.
    - `location: String` – The location of the event.
- **Methods**
    - `complete(): void` – Sets the `complete` boolean to `true`.
    - `isComplete(): boolean` – Returns the `complete` status.
    - `getEndTime(): LocalDateTime` – Returns the `endDateTime`.
    - `getDuration(): Duration` – Returns the meeting duration (`dateTime - endDateTime`).
    - `getLocation(): String` – Returns the location.
    - `setEndTime(end: Date): void` – Sets the end of the meeting.
    - `setLocation(location: String): void` – Sets the location.

### EventTester (Class)
- **Main Method**  
  `main(String… args): void` – Runs the test methods and displays results.

- **Testing Methods**  
  Create static methods to test the functionality of each class.

---

## EXTRA OPTIONS (Optional, for an Extra Challenge)

### Reminder (Class extends `Event`)
- **Fields**
    - `timeBefore: Duration` – Time before the event when the reminder appears.
    - `event: Event` – The event this reminder is attached to.
- **Methods**
    - `getDateTime(): LocalDateTime` – Returns the event's dateTime adjusted for the reminder. Overrides `Event.getDateTime()`.
    - `getName(): String` – Returns the event name with "Reminder: " and the event's dateTime added.

**Add to `Deadline` and `Meeting` Classes:**
- `reminders: ArrayList<Reminder>` – List of reminders.
- `addReminder(daysBefore: int, hoursBefore: int, minutesBefore: int): void` – Adds a reminder to the list.

---

### Urgency (Enum)
- **Values**
    - `DISTANT`, `IMMINENT`, `OVERDUE`
- **Static Fields and Methods**
    - `thresholdOfImminence: Duration` – Threshold for distinguishing between DISTANT and IMMINENT.
    - `setThresholdOfImminence(Duration d): void` – Sets the threshold.
    - `getUrgency(Date time): Urgency` – Returns an urgency based on the time comparison:
        - `DISTANT` if far in the future.
        - `IMMINENT` if soon.
        - `OVERDUE` if past.

---

## 2. Create a GUI for this Application

### EventPlanner (Class)
- **Main Method**  
  `main(String… args): void` – Creates a JFrame and adds an `EventListPanel`.
- **Static Method**  
  `addDefaultEvents(events: EventListPanel): void` – Adds default events to the `EventListPanel`.

---

### EventPanel (Class, extends `JPanel`)
- **Fields**
    - `event: Event` – The event this panel displays.
    - `completeButton: JButton` – Completes the event. Only visible for `Completable` events.
- **Methods**
    - `updateUrgency(): void` – Sets background color based on the event’s urgency:
        - Red for OVERDUE.
        - Yellow for IMMINENT.
        - Green for DISTANT.

---

### EventListPanel (Class, extends `JPanel`)
- **Fields**
    - `events: ArrayList<Event>` – List of events.
    - `controlPanel: JPanel` – Controls for the event display.
    - `displayPanel: JPanel` – Holds `EventPanels` corresponding to events.
    - `sortDropDown: JComboBox` – Dropdown to sort events by name, date, or reverse order.
    - `filterDisplay: JCheckBox` – Filters to remove completed events, deadlines, meetings, etc.
    - `addEventButton: JButton` – Opens an `AddEventModal`.

---

### AddEventModal (Class, extends `JDialog`)
Used to add an event (Deadline or Meeting) to the planner.

---

## EXTRA OPTIONS (Completely Optional, for an Extra Challenge)

- **Implement Reminders:** Add reminders as events that disappear once their time passes.
- **Implement Urgency:** Include an urgency field in the `EventPanel` and periodically update urgency colors.
- **CalendarDisplay:** Add a monthly calendar view for events.

---

## Test Cases

### Logic (Part 1)
**EventTester Class** will include tests for the logic classes.

### GUI (Part 2)
- **Click "Add Event" Button:** `AddEventModal` appears.
- **Successfully Add a Meeting:** Meeting appears with name, dateTime, duration, and location.
- **Successfully Add a Deadline:** Deadline appears with name and dateTime.
- **Sort by Name:** Events sorted alphabetically.
- **Sort by Reverse Name:** Events sorted in reverse order.
- **Sort by DateTime:** Events sorted by time.
- **Complete an Event:** Checkbox for completion gets checked.
- **Filter Completed Events:** Completed events disappear from the list.
- **Filter Deadlines:** Deadlines disappear from the list.
- **Filter Meetings:** Meetings disappear from the list.
- **Remove All Filters:** All events appear on the list.  
