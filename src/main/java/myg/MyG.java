package myg;

import java.util.Scanner;

public class MyG {
    public static void main(String[] args) {
        /** ---------------- Response blocks ---------------- */
        String intro =
                "____________________________________________________________\n" +
                        " What's good myg.MyG\n" +
                        " What can I do for you?\n";
        String listMsg =
                "    ____________________________________________________________";
        String bye =
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";

        /** ---------------- Storage for tasks ---------------- */
        Task[] tasks = new Task[100];
        int taskCount = 0;

        /** ---------------- Start of program ---------------- */
        System.out.println(intro);
        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine().trim(); // Read user input

            try {
                if (line.equalsIgnoreCase("bye")) {
                    System.out.println(bye);
                    break;
                }
                else if (line.equalsIgnoreCase("list")) {
                    System.out.println(listMsg);
                    if (taskCount == 0) {
                        System.out.println(" No tasks yet.");
                    } else {
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println(" " + (i + 1) + "." + tasks[i]);
                        }
                    }
                    System.out.println(listMsg);
                }
                else if (line.equalsIgnoreCase("todo") || line.toLowerCase().startsWith("todo ")) {
                    if (line.equalsIgnoreCase("todo")) {
                        throw new MyGException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String desc = line.substring(5).trim();
                    if (desc.isEmpty()) {
                        throw new MyGException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks[taskCount++] = new Todo(desc);
                    System.out.println("____________________________________________________________");
                    System.out.println("Aight bro I got you. I've added this task");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                else if (line.toLowerCase().startsWith("deadline")) {
                    if (line.equalsIgnoreCase("deadline")) {
                        throw new MyGException("OOPS!!! The description of a deadline cannot be empty. Use: deadline <desc> /by <time>");
                    }
                    String rest = line.substring(9).trim(); // after 'deadline '
                    int byIndex = rest.toLowerCase().indexOf("/by");
                    if (byIndex == -1) {
                        throw new MyGException("OOPS!!! Please use the format: deadline <desc> /by <time>");
                    }
                    String desc = rest.substring(0, byIndex).trim();
                    String by = rest.substring(byIndex + 3).trim();
                    if (desc.isEmpty() || by.isEmpty()) {
                        throw new MyGException("OOPS!!! myg.Deadline needs a non-empty description and a /by time.");
                    }
                    tasks[taskCount++] = new Deadline(desc, by);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                else if (line.toLowerCase().startsWith("event")) {
                    if (line.equalsIgnoreCase("event")) {
                        throw new MyGException("OOPS!!! The description of an event cannot be empty. Use: event <desc> /from <start> /to <end>");
                    }
                    String rest = line.substring(6).trim(); // after 'event '
                    String lower = rest.toLowerCase();
                    int fromIndex = lower.indexOf("/from");
                    int toIndex = lower.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
                        throw new MyGException("OOPS!!! Please use the format: event <desc> /from <start> /to <end>");
                    }
                    String desc = rest.substring(0, fromIndex).trim();
                    String from = rest.substring(fromIndex + 5, toIndex).trim();
                    String to = rest.substring(toIndex + 3).trim();
                    if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new MyGException("OOPS!!! myg.Event needs a non-empty description, /from and /to.");
                    }
                    tasks[taskCount++] = new Event(desc, from, to);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                else if (line.toLowerCase().startsWith("mark ")) {
                    String[] parts = line.split("\\s+", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new MyGException("OOPS!!! Please provide the task number to mark.");
                    }
                    int index;
                    try {
                        index = Integer.parseInt(parts[1].trim()) - 1;
                    } catch (NumberFormatException nfe) {
                        throw new MyGException("OOPS!!! myg.Task number must be an integer.");
                    }
                    if (index < 0 || index >= taskCount) {
                        throw new MyGException("OOPS!!! That task number doesn't exist.");
                    }
                    tasks[index].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index]);
                    System.out.println("____________________________________________________________");
                }
                else if (line.toLowerCase().startsWith("unmark ")) {
                    String[] parts = line.split("\\s+", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new MyGException("OOPS!!! Please provide the task number to unmark.");
                    }
                    int index;
                    try {
                        index = Integer.parseInt(parts[1].trim()) - 1;
                    } catch (NumberFormatException nfe) {
                        throw new MyGException("OOPS!!! myg.Task number must be an integer.");
                    }
                    if (index < 0 || index >= taskCount) {
                        throw new MyGException("OOPS!!! That task number doesn't exist.");
                    }
                    tasks[index].unmark();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Aight bro, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index]);
                    System.out.println("____________________________________________________________");
                }
                else if (line.toLowerCase().startsWith("delete ")) {
                    String[] parts = line.split("\\s+", 2);
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new MyGException("OOPS!!! Please provide the task number to delete.");
                    }
                    int index;
                    try {
                        index = Integer.parseInt(parts[1].trim()) - 1;
                    } catch (NumberFormatException nfe) {
                        throw new MyGException("OOPS!!! Task number must be an integer.");
                    }
                    if (index < 0 || index >= taskCount) {
                        throw new MyGException("OOPS!!! That task number doesn't exist.");
                    }
                    Task removed = tasks[index];
                    // Shift all tasks down
                    for (int i = index; i < taskCount - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    tasks[--taskCount] = null;
                    System.out.println("____________________________________________________________");
                    System.out.println("Removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                else {
                    throw new MyGException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (MyGException e) {
                printError(e.getMessage());
            } catch (Exception e) {
                printError("Something went wrong: " + e.getMessage());
            }
        }

        input.close();
    }

    private static void printError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }
}
