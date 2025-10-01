package myg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the file specified by filePath.
     * @return A list of tasks loaded from the file.
     * @throws MyGException If an error occurs during loading or data is corrupted.
     */
    public List<Task> load() throws MyGException {
        List<Task> tasks = new ArrayList<>();

        try {
            // Ensure data directory exists before checking file existence
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            if (Files.notExists(filePath)) {
                return tasks; // Return empty list if file doesn't exist
            }

            try (BufferedReader br = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        Task t = parseTaskFromFile(line);
                        if (t != null) {
                            tasks.add(t);
                        }
                    } catch (MyGException e) {
                        // Skip corrupted task, but inform user (optional print)
                        // System.out.println("⚠️ Corrupted task skipped: " + e.getMessage() + " -> Line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            throw new MyGException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current task list to the file.
     * @param tasks The TaskList containing the tasks to save.
     */
    public void save(TaskList tasks) {
        try {
            // Ensure data directory exists (again, though load() should cover it)
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task t : tasks.getTasks()) {
                    writer.write(t.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Helper method to parse a single line from the data file into a Task object.
     * (Retained the logic from MyG.java, now in Storage).
     */
    private Task parseTaskFromFile(String line) throws MyGException {
        // Split on ' | ' to strictly match the file format
        String[] parts = line.split(" \\| ", -1);
        if (parts.length < 3) {
            throw new MyGException("Missing essential task components (type, status, description)");
        }

        String type = parts[0].trim();
        boolean isDone;
        try {
            isDone = parts[1].trim().equals("1");
        } catch (Exception e) {
            throw new MyGException("Invalid task status format");
        }

        String desc = parts[2].trim();
        if (desc.isEmpty()) {
            throw new MyGException("Empty task description");
        }

        Task task;
        switch (type) {
            case "T":
                task = new Todo(desc);
                if (parts.length > 3) {
                    throw new MyGException("Extraneous data for Todo task");
                }
                break;
            case "D":
                if (parts.length < 4) throw new MyGException("Deadline missing date/time");
                String by = parts[3].trim();
                task = new Deadline(desc, by);
                if (parts.length > 4) {
                    throw new MyGException("Extraneous data for Deadline task");
                }
                break;
            case "E":
                if (parts.length < 4) throw new MyGException("Event missing date/time");
                String fromTo = parts[3].trim();
                int dashIndex = fromTo.indexOf(" - ");
                if (dashIndex == -1) {
                    throw new MyGException("Event time format is corrupted (expected 'from - to')");
                }
                String from = fromTo.substring(0, dashIndex).trim();
                String to = fromTo.substring(dashIndex + 3).trim();

                task = new Event(desc, from, to);
                if (parts.length > 4) {
                    throw new MyGException("Extraneous data for Event task");
                }
                break;
            default:
                throw new MyGException("Unknown task type: " + type);
        }

        if (isDone) task.markAsDone();
        return task;
    }
}