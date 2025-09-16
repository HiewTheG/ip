import java.util.Scanner;

public class MyG {
    public static void main(String[] args) {
        /** Response blocks */
        String intro =
                "____________________________________________________________\n" +
                " Hello! I'm MyG\n" +
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

        /** Storage for tasks */
        String[] tasks = new String[100];
        Task[] taskObjects = new Task[100];
        int taskCount = 0;

        /** Logic and flow control blocks */
        System.out.println(intro);
        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(bye);
                break;
            } else if (line.equalsIgnoreCase("list")) {
                System.out.println(listMsg);
                if (taskCount == 0) {
                    System.out.println("No tasks yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". [" + taskObjects[i].getStatusIcon() + "] " + tasks[i]);
                    }
                }
                System.out.println(listMsg);
            }  else if (line.equalsIgnoreCase("blah")) {
                System.out.println(blah);
            } else if (line.startsWith("mark")) {
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
            else {
                // store new task
                if (taskCount < 100) {
                    tasks[taskCount] = line;
                    taskObjects[taskCount] = new Task(line);
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
