/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author vikas
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
//    when the first instance of program is run, a file for containing all the project id's and task id's is created...
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        // Write code here...
        System.out.println("Welcome to todo");
        System.out.println("what do you want to do");
        System.out.println("Create task: 1 \nOpen task: 2 \nList all tasks: 3 \nJump to task using Id: 4 \nFilter task: 5 \nExit: 6 ");
        int input = Integer.valueOf(scan.nextLine());
        switch (input) {
            
            case 1:
//                System.out.println("Enter project name");
//                Project newdirectory = new Project("/home/vikas/Documents/Vikas/ToDO/projects/" + scan.nextLine() + ".csv");
//                Project.newProject(newdirectory.getpath());
                Gson newTask  = new GsonBuilder().setPrettyPrinting().create();
                System.out.println("Enter task name: ");
                String tempTask = scan.nextLine();
                System.out.println("Enter due date: (dd/mm/yyyy)");
                String tempDate = scan.nextLine();
                System.out.println("Enter status");
                String tempStatus = scan.nextLine();
                System.out.println("Enter description: ");
                String tempDes = scan.nextLine();
                task newtask = new task(tempTask, tempStatus, tempDate, tempDes);
                FileWriter myWriter = new FileWriter("/home/vikas/Documents/Vikas/ToDO/project.json");
                newTask.toJson(newtask, myWriter);
                break;

            case 2:
//                System.out.println("Enter project name");
//                Project openDirectory;
//            openDirectory = new Project("/home/vikas/Documents/Vikas/ToDO/projects/" + scan.nextLine() + ".csv");
//                break;

            case 3:
//                System.out.println("Enter project name");
//                String read_project = scan.nextLine();
//                if(validDirectory(read_project)) {
//                    listDirectory(read_project);
//                }
                
            case 4:
//                System.out.println("Enter the task id...");
//                System.out.println("In process of implementation...");
                break;
            
            case 5:
//                System.out.println("filter tasks due: \ntoady: 1\ntomorrow: 2\nthis week: 3\nthis month: 4\nDue upto: 5");
//                int choice = scan.nextInt();
//                switch(choice) {
//                    case 1:
//                        break;
//                    
//                    case 2:
//                        break;
//                        
//                    case 3:
//                        break;
//                        
//                    case 4:
//                        break;
//                        
//                    case 5:
//                        break;
//                        
//                    default:
//                        System.out.println("Enter valid input");
//                }
                break;
                
            case 6:
//                exit using goto
                break;
         
            default:
                System.out.println("Enter valid number");
                break;
        }
    }
    
    public static void printTask(String input) {
        String[] task = input.split(",");
        for (int i = 0; i < task.length; i++) {
            task[i] = task[i].trim();
        }
        if (task[0].length() > 0) {
            System.out.println("Task-name: " + task[0]);
            System.out.println("Task-due_date: " + task[1]);
            System.out.println("Task-status: " + task[2]);
            System.out.println("");
        }
    }
    public static boolean validDirectory(String input) {
        File directory = new File(input);
        return directory.exists() && directory.isDirectory();
    }
    
    public static void listDirectory(String input) {
        File directory = new File(input);
        File[] filesList = directory.listFiles();

            if (filesList != null) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        System.out.println("File: " + file.getName());
                    } else if (file.isDirectory()) {
                        System.out.println("Directory: " + file.getName());
                    }
                }
            } else {
                System.out.println("The specified directory is empty or an error occurred.");
            }
    }
    
    public static void createDirectory(String input) {
        try {
                    File file = new File("/home/vikas/Documents/Vikas/ToDO/projects/" + input + ".csv");

                    if (file.createNewFile()) {
                        System.out.println("File created: " + file.getName());
                        // FileWriter myWriter = new FileWriter("/home/vikas/Documents/Vikas/ToDO/" + project);
                    } else {
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occured");
                }
    }
}
