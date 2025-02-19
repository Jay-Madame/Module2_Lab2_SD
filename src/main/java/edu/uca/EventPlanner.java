package edu.uca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

// EventPlanner.java
public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);

        frame.add(eventListPanel);
        frame.setVisible(true);
    }

    public static void addDefaultEvents(EventListPanel eventListPanel) {
        eventListPanel.addEvent(new Deadline("Finish Lab", LocalDateTime.now().plusDays(3)));
        eventListPanel.addEvent(new Meeting("Team Meeting", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4), ""));
    }
}

// EventListPanel.java
class EventListPanel extends JPanel {
    private ArrayList<Event> events = new ArrayList<>();
    private JPanel displayPanel;

    public EventListPanel() {
        setLayout(new BorderLayout());

        // Control panel
        JPanel controlPanel = new JPanel();
        JButton addEventButton = new JButton("Add Event");
        controlPanel.add(addEventButton);
        add(controlPanel, BorderLayout.NORTH);

        // Display panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        add(scrollPane, BorderLayout.CENTER);

        addEventButton.addActionListener(e -> {
            AddEventModal addEventModal = new AddEventModal(this);
            addEventModal.setVisible(true);
        });
    }

    public void addEvent(Event event) {
        events.add(event);
        refreshDisplay();
    }

    public void refreshDisplay() {
        displayPanel.removeAll();
        Collections.sort(events);
        for (Event event : events) {
            displayPanel.add(new EventPanel(event));
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}

// EventPanel.java
class EventPanel extends JPanel {
    private Event event;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel(event.getName() + " - " + event.getEventDate());
        add(nameLabel, BorderLayout.CENTER);

        if (event instanceof Completable) {
            JButton completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                completeButton.setEnabled(false);
            });
            add(completeButton, BorderLayout.EAST);
        }
    }
}

class AddEventModal extends JDialog {
    public AddEventModal(EventListPanel eventListPanel) {
        setTitle("Add Event");
        setSize(400, 300);
        setLayout(new GridLayout(0, 2));

        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();
        JLabel dateLabel = new JLabel("Event Date (yyyy-MM-ddTHH:mm):");
        JTextField dateField = new JTextField();

        JLabel typeLabel = new JLabel("Event Type:");
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Deadline", "Meeting"});

        JLabel endDateLabel = new JLabel("End Date (yyyy-MM-ddTHH:mm):");
        JTextField endDateField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            LocalDateTime dateTime = LocalDateTime.parse(dateField.getText());

            if (typeCombo.getSelectedItem().equals("Deadline")) {
                eventListPanel.addEvent(new Deadline(name, dateTime));
            } else {
                LocalDateTime endDateTime = LocalDateTime.parse(endDateField.getText());
                String location = locationField.getText();
                eventListPanel.addEvent(new Meeting(name, dateTime, endDateTime, location));
            }

            dispose();
        });

        add(nameLabel);
        add(nameField);
        add(dateLabel);
        add(dateField);
        add(typeLabel);
        add(typeCombo);
        add(endDateLabel);
        add(endDateField);
        add(locationLabel);
        add(locationField);
        add(addButton);
    }
}
