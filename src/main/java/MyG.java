import java.util.Scanner;

public class MyG {
    public static void main(String[] args) {
        /** ---------------- Response blocks ---------------- */
        String intro =
                "____________________________________________________________\n" +
                " What's good MyG\n" +
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

            /** Exit command */
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(bye);
                break;
            }
            /** List command */
            else if (line.equalsIgnoreCase("list")) {
                System.out.println(listMsg);
                if (taskCount == 0) {
                    System.out.println("No tasks yet.");
                } else {
                    // Print all tasks with their done/not done status
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i]);
                    }
                }
                System.out.println(listMsg);
            }
            /** Mark a task as done */
            else if (line.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("  [" + tasks[index]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input for mark command.");
                }
            }
            /** Unmark a task */
            else if (line.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].unmark();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Aight bro, I've marked this task as not done yet:");
                        System.out.println("  [" + tasks[index]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input for unmark command.");
                }
            }
            /** Add Todo */
            else if (line.startsWith("todo ")) {
                String desc = line.substring(5);
                tasks[taskCount++] = new Todo(desc);
                System.out.println("____________________________________________________________");
                System.out.println("Aight bro I got you. I've added this task");
                System.out.println(" " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
            /** Add a Deadline */
            else if (line.startsWith("deadline ")) {
                try {
                    String[] parts = line.substring(9).split(" /by", 2);
                    String desc = parts[0];
                    String by = parts[1];
                    tasks[taskCount++] = new Deadline(desc, by);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Your deadline format is invalid. Pls use: deadline <desc> /by <time>");
                }
            }
            /** Add an Event */
            else if (line.startsWith("event ")) {
                try {
                    String[] parts1 = line.substring(6).split(" /from", 2);
                    String desc = parts1[0];
                    String[] parts2 = parts1[1].split(" /to ", 2);
                    String from = parts2[0];
                    String to = parts2[1];
                    tasks[taskCount++] = new Event(desc, from, to);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Your event format is invalid. Pls use: event <desc> /from <start> /to <end>");
                }
            }
            /** Unknown command */
            else {
                System.out.println("Invalid command: " + line);
            }
        }
    }
}
