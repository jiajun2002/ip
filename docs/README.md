# Lebron User Guide
## Introduction
Lebron is a command line interface (CLI) based task management application that helps you to organise your tasks efficiently.
With the help of Lebron, users will be able to add, delete, find, mark, and list tasks easily. They don't call him the King for nothing.

## Features

### Adding Tasks
Lebron supports three different types of tasks - Todos, Deadlines, and Events.

### Adding a Todo
Adds a Todo - a task containing only a description.

**Format:** `todo DESCRIPTION`

**Example:** `todo play basketball`

**Output:**
```
Alright! Get ready to do this: 
--> [T][ ] play basketball
Be confident in your ability! Current tasks: 1
```

### Adding a Deadline

Adds a Deadline - a task containing a description and a due date.

**Format:** `deadline DESCRIPTION /by DATE`

**Example:** `deadline beat the nuggets /by tmr 6pm`

**Output:**
```
Alright! Get ready to do this: 
--> [D][ ] beat the nuggets (by: tmr 6pm)
Be confident in your ability! Current tasks: 2
```

### Adding an Event
Adds an Event - a task containing a description, start date, and end date.

**Format:** `event DESCRIPTION /from START_DATE /to END_DATE`

**Example:** `event game vs warriors /from 6pm /to 8pm`

**Output:**
```
Alright! Get ready to do this: 
--> [E][ ] game vs warriors (from: 6pm to: 8pm)
Be confident in your ability! Current tasks: 3
```

### Listing Tasks
Displays the current list of tasks.

**Format:** `list`

**Output:**
```
1. [T][ ] play basketball
2. [D][ ] beat the nuggets (by: tmr 6pm)
3. [E][ ] game vs warriors (from: 6pm to: 8pm)
```

### Marking a Task
Marks a task as completed.

**Format:** `mark INDEX`

**Example:** `mark 2`

**Output:**
```
Done and dusted, you're gonna shatter my records in no time!
[D][X] beat the nuggets (by: tmr 6pm)
```

### Unarking a Task
Unmarks a task.

**Format:** `unmark INDEX`

**Example:** `unmark 2`

**Output:**
```
I ain't worried, we'll clear that soon!
[D][ ] beat the nuggets (by: tmr 6pm)
```

### Deleting a Task
Deletes a task from the list.

**Format:** `delete INDEX`

**Example:** `delete 2`

**Output:**
```
Traded! We won't be seeing him ever again: 
[D][ ] beat the nuggets (by: tmr 6pm)
Remaining tasks: 2
```

### Finding a Task
Finds and displays the tasks containing a keyword.

**Format:** `find KEYWORD`

**Example:** `find basketball`

**Output:** 
```
Got em! Here are the matching tasks!
1. [T][ ] play basketball
2. [T][ ] play basketball again
3. [D][ ] play another game of basketball (by: tmr)
```
### Exiting Lebron
Ends the application.

**Format:** `bye`

**Output:** 
```
Aight catch you later, see you at the game!
```
 
## Command Summary


| command    | description                       | format |
|------------|-----------------------------------| --- |
| `todo`     | Adds a todo task                  |`todo DESCRIPTION` |
| `deadline` | Adds a deadline task              |`deadline DESCRIPTION /by DATE` |
| `event`    | Adds an event       task          |`event DESCRIPTION /from START_DATE /to END_DATE` |
| `list`     | Displays the current tasks        |`list` |
| `mark`     | Marks a task                      | `mark INDEX` |
| `unmark`   | Unmarks a task                    | `unmark INDEX` |
| `delete`   | Deletes a task                    | `delete INDEX` |
| `find`     | Finds task with the given keyword | `find KEYWORD` |
| `bye`       | Ends the application              | `bye` |