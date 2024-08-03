/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vikas  
 */
public class ToDO {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("...............Welcome to TODO..................");
        Scanner scan = new Scanner(System.in);
        File file = new File("/home/vikas/Documents/Vikas/ToDO/Vivek1.json");
        int choice = 0;
        while(choice != 6) {
            System.out.println("what do you want to do");
            System.out.println("Create task: 1 \nDisplay tasks: 2 \nMark as Done: 3\nDelete Task: 4\nEdit Task: 5\nExit: 6");
            choice = scan.nextInt();
            switch(choice) {
                case 1:
                    if(!(file.exists())) {
                        createfirsttask(file);
                        System.out.println("need to add more... Y/N");
                        String value = scan.nextLine();
                        if(value.equalsIgnoreCase("Y")) {
                            createtask(file);
                        }
                    } else {
                        createtask(file);
                    }
                break;
                case 2:
                    displayfile(file);
                    break;
                case 3:
                    int index = scan.nextInt();
                    markAsDone(file, index);
                    break;
                case 4:
                    int index1 = scan.nextInt();
                    deleteTask(file, index1);
                    break;
                case 5:
                    int index2 = scan.nextInt();
                    editTask(file,index2);
                    break;
            }
        }
    }
    
    public static void createfirsttask(File file_name) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        Gson newTask  = new GsonBuilder().setPrettyPrinting().create();
            task newtask = tasktemplate();
            try (FileWriter myWriter = new FileWriter(file_name)) {
                newTask.toJson(newtask, myWriter);
                myWriter.flush();
                myWriter.close();
            } catch (JsonIOException | IOException e) {
            }
        }
    
    public static JsonArray readfromJson(File file_name) {
        JsonArray jsonArray = null;
        try {
            FileReader reader = new FileReader(file_name);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            reader.close();
            if (jsonElement.isJsonArray()) {
                jsonArray = jsonElement.getAsJsonArray();
            } else {
                jsonArray = new JsonArray();
                jsonArray.add(jsonElement);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
    
    public static void displayfile(File file_name) {
        JsonArray json = readfromJson(file_name);
        for (JsonElement tasks : json) {
            System.out.println(tasks);
            System.out.println("\n");
        }
    }
    
    public static void createtask(File file_name) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        Gson newTask  = new GsonBuilder().setPrettyPrinting().create();
        JsonArray json = readfromJson(file_name);
        try {
            task newtask = tasktemplate();
            JsonElement newElement = newTask.toJsonTree(newtask);
            json.add(newElement);
            System.out.println("Wanna add more...? Y/N :");
            while(true) {                        
                String input = scan.nextLine();
                if(input.equalsIgnoreCase("N")) {
                break;
            } else if(input.equalsIgnoreCase("Y")) {
                newtask = tasktemplate();
                newElement = newTask.toJsonTree(newtask);
                json.add(newElement);
            } else {
                    System.out.println("Enter valid response...");
            }
            }
            FileWriter myWriter = new FileWriter(file_name);
            newTask.toJson(json, myWriter);
            myWriter.flush();
            myWriter.close();
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }
                
    public static task tasktemplate() throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter task name: ");
        String tempTask = scan.nextLine();
        System.out.println("Enter due date: (dd/mm/yyyy)");
        String tempDate = scan.nextLine();
        System.out.println("Enter status");
        String tempStatus = scan.nextLine();
        System.out.println("Enter description: ");
        String tempDes = scan.nextLine();
        return new task(tempTask, tempStatus, tempDate, tempDes);
    }
    
    public static void markAsDone(File file_name, int index) throws IOException {
        Gson newTask  = new GsonBuilder().setPrettyPrinting().create();
        File tasks_done = new File("/home/vikas/Documents/Vikas/ToDO/projects/done_tasks.json");
        JsonArray json = readfromJson(file_name);
        JsonArray tasksToKeep = new JsonArray();
        JsonArray tasksDone = new JsonArray();

        // Iterate through the tasks
        for (int i = 0; i < json.size(); i++) {
            JsonElement task = json.get(i);

            // Check if the current index matches the task to move to done
            if (i == index-1) {
                tasksDone.add(task); // Add task to done list
            } else {
                tasksToKeep.add(task); // Add task to tasks to keep list
            }
        }
        FileWriter myWriter = new FileWriter(file_name);
        FileWriter doneWriter = new FileWriter(tasks_done);
        newTask.toJson(tasksToKeep, myWriter);
        myWriter.flush();
        myWriter.close();
        newTask.toJson(tasksDone, doneWriter);
        doneWriter.flush();
        doneWriter.close();
    }
    
    public static void deleteTask(File file_name, int index) throws IOException {
        Gson newTask  = new GsonBuilder().setPrettyPrinting().create();
        JsonArray json = readfromJson(file_name);
        JsonArray tasksToKeep = new JsonArray();
        for (int i = 0; i < json.size(); i++) {
            JsonElement task = json.get(i);
            if (!(i == index-1 )) {
                tasksToKeep.add(task);
            }
        }
        FileWriter myWriter = new FileWriter(file_name);
        newTask.toJson(tasksToKeep, myWriter);
        myWriter.flush();
        myWriter.close();
    }
    
    public static void editTask(File file_name, int index) throws IOException {
        Gson newTask  = new GsonBuilder().setPrettyPrinting().create();
        JsonArray json = readfromJson(file_name);
        JsonArray afterEdit = new JsonArray();
        JsonElement toEdit = json.get(index-1);
        task edit = newTask.fromJson(toEdit, task.class);
        System.out.println(edit);
        System.out.println("Enter data to edit \n Empty results in no change...");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter task name: ");
        String tempTask = scan.nextLine();
        System.out.println("Enter due date: (dd/mm/yyyy)");
        String tempDate = scan.nextLine();
        System.out.println("Enter status");
        String tempStatus = scan.nextLine();
        System.out.println("Enter description: ");
        String tempDes = scan.nextLine();
        task Editedtask = new task((tempTask.equals("")) ? edit.getTitle() : tempTask,(tempDate.equals("")) ? edit.getDueDate(): tempDate,(tempStatus.equals("")) ? edit.getStatus(): tempStatus,(tempDes.equals("")) ? edit.getDescription(): tempDes);
        JsonElement newElement = newTask.toJsonTree(Editedtask);
        deleteTask(file_name,index);
        json = readfromJson(file_name);
        json.add(newElement);
        FileWriter myWriter = new FileWriter(file_name);
        newTask.toJson(json, myWriter);
        myWriter.flush();
        myWriter.close();
    }
    
    public static void filterTask(File file_name, String parameter, String key) {
        
    }
    
    public static void sortTask() {
        
    }
}