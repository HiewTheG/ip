package myg;

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
     * @throws MyGException If the command is invalid.
     */
    public static boolean parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws MyGException {
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

            default:
                throw new MyGException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

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