# MyG User Guide
<img width="829" height="186" alt="Screenshot 2025-10-01 at 11 01 03 AM" src="https://github.com/user-attachments/assets/ec5455ac-9a24-44d1-8d57-a18f2d9e71ae" />

# MyG: Your Must-Have Chatbot Assistant

**MyG** is a lightweight, command-line interface (CLI) chatbot designed for students and professionals who need a quick, no-fuss way to manage daily tasks and appointments.

## Getting Started

To run MyG, ensure you have **Java 17 or later** installed. Simply execute the compiled JAR file from your terminal:
```bash
java -jar myg.jar
```

## Core Features
MyG manages three types of tasks: To-dos, Deadlines, and Events. All task commands are case-insensitive.

### 1. Adding Deadlines (`deadline`)
Deadlines are tasks that must be completed by a specific date and time, denoted by the `/by` flag.

Action and Outcome: Creates a new `[D]` task.

Example of usage:
`deadline submit report /by 2025-10-30 1800`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
 [D][ ] submit report (by: Oct 30 2025, 06:00 PM)
Now you have 1 tasks in the list.
____________________________________________________________
```

### 2. Adding To-dos (`todo`)
To-dos are simple, single-action tasks without a specific date or time attached.

Action and Outcome: Creates a new `[T]` task.

Example of usage:
`todo buy milk and eggs`

Expected outcome:

```
____________________________________________________________
Aight bro I got you. I've added this task
 [T][ ] buy milk and eggs
Now you have 2 tasks in the list.
____________________________________________________________
```

### 3. Adding Events (`event`)
Events are tasks that occur over a duration, specified by the `/from` and `/to` flags.

Action and Outcome: Creates a new `[E]` task and schedules it for the given time range.

Example of usage:
`event project meeting /from Mon 2pm /to 4pm`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
 [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

## Task Management
### 4. Listing All Tasks (`list`)
Displays all tasks currently stored in your list, showing their type, status, and description.

Action and Outcome: Prints every task in the list, prefixed with its index number (starting from 1).

Example of usage:
`list`

Expected outcome:

```
____________________________________________________________
 Here are the tasks in your list:
 1.[D][ ] submit report (by: Oct 30 2025, 06:00 PM)
 2.[T][ ] buy milk and eggs
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```

### 5. Marking Tasks (`mark` and `unmark`)
You can update the completion status of a task using its index number.

| Command  | Purpose                  | Format        | Status Icon |
|----------|--------------------------|---------------|-------------|
| `mark`   | Marks a task as completed.  | `mark INDEX`   | `[X]`       |
| `unmark` | Marks a task as not completed. | `unmark INDEX` | `[ ]`       |

Example of usage:
`mark 2`

Expected outcome:

```
____________________________________________________________
 Nice! I've marked this task as done:
  [T][X] buy milk and eggs
____________________________________________________________
```

### 6. Finding Tasks by Keyword (`find`)
Searches the description of all tasks in the list for a specific keyword.

Action and Outcome: MyG returns a list of tasks whose description contains the provided `KEYWORD`. The search is case-insensitive.

Example of usage:
`find milk`

Expected outcome:

```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][X] buy milk and eggs
____________________________________________________________
```

### 7. Deleting Tasks (`delete`)
Permanently removes a task from your list using its index number.

Action and Outcome: The specified task is removed, and MyG confirms the removal and shows the new task count.

Example of usage:
`delete 3`

Expected outcome:

```
____________________________________________________________
 Noted. I've removed this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### 8. Exiting MyG (`bye`)
Saves all current tasks to the hard disk and exits the application cleanly.

Example of usage:
`bye`

Expected outcome:

```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```
