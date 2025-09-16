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
        String blah =
                "    ____________________________________________________________\n" +
                "     blah\n" +
                "    ____________________________________________________________\n";
        String bye =
                "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        /** ---------------- Storage for tasks ---------------- */
        String[] tasks = new String[100];
        Task[] taskObjects = new Task[100];
        int taskCount = 0;

        /** ---------------- Start of program ---------------- */
        System.out.println(intro);
        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine(); // Read user input

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
                        System.out.println(" " + (i + 1) + ". [" + taskObjects[i].getStatusIcon() + "] " + tasks[i]);
                    }
                }
                System.out.println(listMsg);
            }
            /** Placeholder command */
            else if (line.equalsIgnoreCase("blah")) {
                System.out.println(blah);
            }
            /** Mark a task as done */
            else if (line.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        taskObjects[index].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("  [" + taskObjects[index].getStatusIcon() + "] " + taskObjects[index].getDescription());
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input for mark command.");
                }
            }
            /** Unmark a task */
            else if (line.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        taskObjects[index].unmark();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Aight bro, I've marked this task as not done yet:");
                        System.out.println("  [" + taskObjects[index].getStatusIcon() + "] " + taskObjects[index].getDescription());
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input for unmark command.");
                }
            }
            /** Add a new task */
            else {
                if (taskCount < 100) {
                    tasks[taskCount] = line; // Store description
                    taskObjects[taskCount] = new Task(line); // create Task object
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + line);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list full! Cannot add more.");
                    System.out.println("____________________________________________________________");
                }
            }
        }
    }
}
