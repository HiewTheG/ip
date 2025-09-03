import java.util.Scanner;

public class MyG {
    public static void main(String[] args) {
        // Response blocks
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

        // create storage for list
        String[] tasks = new String[100];
        int taskCount = 0;

        // Logic and flow control blocks
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
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println(listMsg);
            }  else if (line.equalsIgnoreCase("blah")) {
                System.out.println(blah);
            } else {
                // store inputs
                if (taskCount < 100) {
                    tasks[taskCount++] = line;
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
