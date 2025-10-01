package myg;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list, which includes operations to add/delete/mark tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with tasks loaded from storage.
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task by its 1-based index (user input).
     * @param oneBasedIndex The 1-based index of the task to delete.
     * @return The deleted task.
     * @throws MyGException If the index is out of range.
     */
    public Task deleteTask(int oneBasedIndex) throws MyGException {
        if (oneBasedIndex <= 0 || oneBasedIndex > tasks.size()) {
            throw new MyGException("That task number doesn't exist.");
        }
        return tasks.remove(oneBasedIndex - 1);
    }

    /**
     * Marks a task as done by its 1-based index.
     * @param oneBasedIndex The 1-based index of the task to mark.
     * @return The marked task.
     * @throws MyGException If the index is out of range.
     */
    public Task markTask(int oneBasedIndex) throws MyGException {
        if (oneBasedIndex <= 0 || oneBasedIndex > tasks.size()) {
            throw new MyGException("That task number doesn't exist.");
        }
        Task task = tasks.get(oneBasedIndex - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a task by its 1-based index.
     * @param oneBasedIndex The 1-based index of the task to unmark.
     * @return The unmarked task.
     * @throws MyGException If the index is out of range.
     */
    public Task unmarkTask(int oneBasedIndex) throws MyGException {
        if (oneBasedIndex <= 0 || oneBasedIndex > tasks.size()) {
            throw new MyGException("That task number doesn't exist.");
        }
        Task task = tasks.get(oneBasedIndex - 1);
        task.unmark();
        return task;
    }

    public void listTasks(Ui ui) {
        ui.showLine();
        System.out.println(" Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        ui.showLine();
    }
}
