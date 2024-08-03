/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author vikas/
 */
public class task {

    private final String title;
    private final String status;
    private final String dueDate;
    private final String description;
    private final int id;
    
    public task(String task, String currentStatus, String due, String desc) {
        this.title = task;
        this.dueDate = due;
        this.status = currentStatus;
        this.description = desc;
        this.id = 0;
    }
    
    public String getTitle() {
        return this.title;
    }

    public String getStatus() {
        return status;
    }

    public String getDueDate() {
        return dueDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int setId() {
        JsonElement userdata = new JsonParser().parse(json);
        try {
            FileReader reader = new FileReader("/home/vikas/Documents/Vikas/ToDO/User_Details.json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            reader.close();
            jsonArray.add(jsonElement);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void editTask(int id,String value) {

    }

    public void 
    @Override
    public String toString() {
        return "task{" + "title=" + title + ", status=" + status + ", dueDate=" + dueDate + ", description=" + description + '}';
    }   
}
// Project - dipslay project
//             add task
//             filter
//             sort

// Task - edit task
//         markasdone
//         delete
