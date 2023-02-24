import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import tasks.*;

public class Data {
    public static void createFile() throws IOException {
        try {
            if (new File("data").mkdirs()) {
                File data = new File("data/data.txt");
                data.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void saveData(ArrayList<Task> taskManager) throws IOException {
        try {
            createFile();
        } catch (IOException e) {
            System.out.println("An error occurred. Directory probably exists");
        }

        try {
            FileWriter myWriter = new FileWriter("data/data.txt");
            for (Task task : taskManager) {
                if (task.getType().equals("T")) {
                    myWriter.write(task.getType() + "%" + task.getStatusIcon()
                                    + "%" + task.getDescription() + '\n');
                } else if (task.getType().equals("D")) {
                    Deadline obj = (Deadline) task;
                    myWriter.write(task.getType() + "%" + task.getStatusIcon()
                                    + "%" + task.getDescription() + "%" + obj.by + '\n');
                } else if (task.getType().equals("E")) {
                    Event obj = (Event) task;
                    myWriter.write(task.getType() + "%" + task.getStatusIcon() + "%"
                                    + task.getDescription() + "%" + obj.start + "%" + obj.end + '\n');
                }
            }
            myWriter.close();
            System.out.println("Successful. Tasks saved to 'data.txt' file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadData(ArrayList<Task> taskManager) throws IOException {
        try {
            File data = new File("data/data.txt");
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String[] str = sc.nextLine().split("%");
                String taskType = str[0];
                boolean isDone = str[1].equals("[X]");
                String description = str[2];
                switch (taskType) {
                case "T":
                    ToDo newToDo = new ToDo(description);
                    newToDo.setIsDone(isDone);
                    taskManager.add(newToDo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(description, str[3]);
                    newDeadline.setIsDone(isDone);
                    taskManager.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(description, str[3], str[4]);
                    newEvent.setIsDone(isDone);
                    taskManager.add(newEvent);
                    break;
                default:
                    System.out.println("Invalid task.");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }
}
