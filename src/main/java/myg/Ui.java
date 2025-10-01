package myg;

import java.util.Scanner;

/**
 * Deals with interactions with the user, including reading input and printing output.
 */
public class Ui {
    private final Scanner scanner;
    private static final String DIVIDER = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println(" What's good myg.MyG");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDER);
    }

    public void showGoodbye() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public void showLoadingError() {
        showError("Failed to load tasks from file. Starting with an empty list.");
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println(" OOPS!!! " + message);
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        // Read user input and trim whitespace
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        scanner.close();
    }
}