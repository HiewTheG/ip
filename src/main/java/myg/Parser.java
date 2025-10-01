package myg;

import java.util.List;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the full command string and executes the action on the TaskList.
     *
     * @param fullCommand The full user command string.
     * @param tasks The TaskList to modify.
     * @param ui The Ui object for display.
     * @param storage The Storage object for saving changes.
     * @return true if the program should exit, false otherwise.
     * @throws MyGException If the command is invalid or arguments are missing.
     */
    public static boolean parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws MyGException {
        // ... (existing implementation)
        String command = fullCommand.split("\\s+")[0].toLowerCase();
        String arguments = fullCommand.substring(command.length()).trim();

        switch (command) {
            case "bye":
                storage.save(tasks);
                ui.showGoodbye();
                return true;

            case "list":
                tasks.listTasks(ui);
                break;

            case "todo":
                if (arguments.isEmpty()) {
                    throw new MyGException("The description of a todo cannot be empty.");
                }
                Task todo = new Todo(arguments);
                tasks.addTask(todo);
                ui.showLine();
                System.out.println("Aight bro I got you. I've added this task");
                System.out.println(" " + todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                storage.save(tasks);
                break;

            case "deadline":
                handleDeadline(arguments, tasks, ui, storage);
                break;

            case "event":
                handleEvent(arguments, tasks, ui, storage);
                break;

            case "mark":
                handleMark(arguments, tasks, ui, storage, true);
                break;

            case "unmark":
                handleMark(arguments, tasks, ui, storage, false);
                break;

            case "find":
                if (arguments.isEmpty()) {
                    throw new MyGException("Please specify a keyword to search for.");
                }
                TaskList matchingTasks = tasks.findTasks(arguments);
                handleFind(matchingTasks, ui);
                break;

            default:
                throw new MyGException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    /**
     * Displays the results of a find operation to the user.
     * This method is considered non-trivial due to output formatting logic.
     *
     * @param matchingTasks The TaskList containing the tasks that match the search keyword.
     * @param ui The Ui object for display.
     */
    private static void handleFind(TaskList matchingTasks, Ui ui) {
        ui.showLine();
        if (matchingTasks.size() == 0) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            List<Task> tasks = matchingTasks.getTasks();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        ui.showLine();
    }

    /**
     * Parses the arguments for a 'deadline' command, creates the task, and saves the list.
     * This method is considered non-trivial due to command parsing logic.
     *
     * @param arguments The part of the command after "deadline".
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui object for display.
     * @param storage The Storage object for saving changes.
     * @throws MyGException If the format is incorrect or arguments are empty.
     */
    private static void handleDeadline(String arguments, TaskList tasks, Ui ui, Storage storage) throws MyGException {
        int byIndex = arguments.toLowerCase().indexOf("/by");
        if (byIndex == -1) {
            throw new MyGException("Please use the format: deadline <desc> /by <time>");
        }
        String desc = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 3).trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new MyGException("Deadline needs a non-empty description and a /by time.");
        }
        Task deadline = new Deadline(desc, by);
        tasks.addTask(deadline);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks);
    }

    /**
     * Parses the arguments for an 'event' command, creates the task, and saves the list.
     * This method is considered non-trivial due to command parsing logic.
     *
     * @param arguments The part of the command after "event".
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui object for display.
     * @param storage The Storage object for saving changes.
     * @throws MyGException If the format is incorrect or arguments are empty.
     */
    private static void handleEvent(String arguments, TaskList tasks, Ui ui, Storage storage) throws MyGException {
        String lower = arguments.toLowerCase();
        int fromIndex = lower.indexOf("/from");
        int toIndex = lower.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
            throw new MyGException("Please use the format: event <desc> /from <start> /to <end>");
        }

        String desc = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 5, toIndex).trim();
        String to = arguments.substring(toIndex + 3).trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new MyGException("Event needs a non-empty description, /from and /to.");
        }
        Task event = new Event(desc, from, to);
        tasks.addTask(event);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + event);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks);
    }

    /**
     * Parses the arguments for 'mark' or 'unmark' commands, updates the task, and saves the list.
     * This method is considered non-trivial due to task manipulation and error handling.
     *
     * @param arguments The part of the command after "mark" or "unmark".
     * @param tasks The TaskList to modify.
     * @param ui The Ui object for display.
     * @param storage The Storage object for saving changes.
     * @param isMark True if the command is 'mark', false for 'unmark'.
     * @throws MyGException If the task number is invalid or out of range.
     */
    private static void handleMark(String arguments, TaskList tasks, Ui ui, Storage storage, boolean isMark) throws MyGException {
        if (arguments.isEmpty()) {
            throw new MyGException("Please provide the task number to " + (isMark ? "mark" : "unmark") + ".");
        }
        int index;
        try {
            index = Integer.parseInt(arguments.trim());
        } catch (NumberFormatException nfe) {
            throw new MyGException("Task number must be an integer.");
        }

        Task task;
        if (isMark) {
            task = tasks.markTask(index);
            ui.showLine();
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            task = tasks.unmarkTask(index);
            ui.showLine();
            System.out.println(" Aight bro, I've marked this task as not done yet:");
        }

        System.out.println("  " + task);
        ui.showLine();
        storage.save(tasks);
    }
}