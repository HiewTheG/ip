package myg;

import java.util.List;

/**
 * The main class for the MyG chatbot application.
 * MyG orchestrates the interaction between UI, Storage, TaskList, and Parser components.
 */
public class MyG {
    // Hard-coded file path for the storage file
    private static final String FILE_PATH = "data/myg.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for MyG. Initializes UI, Storage, and loads tasks.
     * @param filePath The path to the task file.
     */
    public MyG(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Load tasks into a List, then pass to TaskList
            List<Task> loadedTasks = storage.load();
            tasks = new TaskList(loadedTasks);
        } catch (MyGException e) {
            ui.showLoadingError();
            // Start with an empty list if loading fails
            tasks = new TaskList();
        }
    }

    /**
     * Main execution loop of the chatbot.
     * Reads user commands, parses them, executes the corresponding action, and handles exceptions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = Parser.parseAndExecute(fullCommand, tasks, ui, storage);
            } catch (MyGException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            }
        }

        ui.closeScanner();
    }

    /**
     * The entry point of the MyG application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Instantiate MyG with the file path and start the run loop
        new MyG(FILE_PATH).run();
    }
}