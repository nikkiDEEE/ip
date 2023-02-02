import java.util.Scanner;
import java.util.ArrayList;

public class ThomasShelby {
    static final int MAX_TASKS = 100;

    static Task[] taskManager = new Task[MAX_TASKS];
    static int taskCount = 0;

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". "
                    + taskManager[i]);
        }
    }

    private static void addToDo(String[] cmdSplit) {
        ToDo newToDo = new ToDo(cmdSplit[1]);
        //newToDo.setDescription(cmdSplit[1]);
        taskManager[taskCount] = newToDo;
        taskCount++;
        System.out.println("Don't sleep on it.");
        System.out.println("added: " + newToDo);
    }

    private static void addDeadline(String[] cmdSplit) {
        String[] taskAndDeadline = cmdSplit[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        //newDeadline.setDescription(cmdSplit[1]);
        taskManager[taskCount] = newDeadline;
        taskCount++;
        System.out.println("Time is money, chop chop!");
        System.out.println("added: " + newDeadline);
    }

    private static void addEvent(String[] cmdSplit) {
        String[] taskAndDuration = cmdSplit[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        //newEvent.setDescription(cmdSplit[1]);
        taskManager[taskCount] = newEvent;
        taskCount++;
        System.out.println("I'll see you there.");
        System.out.println("added: " + newEvent);
    }

    private static void markTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager[whichTask].setIsDone(true);
        System.out.println("That was long due, well done.");
        System.out.println(taskManager[whichTask]);
    }

    private static void unmarkTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager[whichTask].setIsDone(false);
        System.out.println("You've gotten lazy.");
        System.out.println(taskManager[whichTask].getStatusIcon()
                + " " + taskManager[whichTask].getDescription());
    }

    public static void main(String[] args) {
        System.out.print("Good day, I'm Thomas Shelby.\nTo what do I owe the pleasure?\n");
        Scanner in = new Scanner(System.in);
        while (true) {
            String cmd = in.nextLine();
            String[] cmdSplit = cmd.split(" ", 2); // array of words
            int whichTask = 0; // variable holding mark/unmark task num
            switch (cmdSplit[0]) {
            case "bye":
                System.out.println("Cheers.");
                return;
            case "list":
                listTasks();
                break;
            case "todo":
                addToDo(cmdSplit);
                break;
            case "deadline":
                addDeadline(cmdSplit);
                break;
            case "event":
                addEvent(cmdSplit);
                break;
            case "mark":
                markTask(cmdSplit);
                break;
            case "unmark":
                unmarkTask(cmdSplit);
                break;
            default:
                System.out.println(cmd); // echo cmd
                break;
            }
        }
    }
}