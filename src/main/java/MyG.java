import java.util.Scanner;

public class MyG {
    public static void main(String[] args) {
        // Response blocks
        String intro =
                "____________________________________________________________\n" +
                " Hello! I'm MyG\n" +
                " What can I do for you?\n";
        String list =
                "    ____________________________________________________________\n" +
                "     list\n" +
                "    ____________________________________________________________";
        String blah =
                "    ____________________________________________________________\n" +
                "     blah\n" +
                "    ____________________________________________________________\n";
        String bye =
                "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        // Logic and flow control blocks
        System.out.println(intro);
        Scanner input = new Scanner(System.in);

        while (true) {
            String line = input.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(bye);
                break;
            } else if (line.equalsIgnoreCase("list")) {
                System.out.println(list);
            }  else if (line.equalsIgnoreCase("blah")) {
                System.out.println(blah);
            }
        }
    }
}
