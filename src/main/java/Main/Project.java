/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author vikas
 */
public class Project {

    private int project_id;
    private final String path;
//    what does making final do


    public Project(String file_path) {
        this.path = file_path;
    }
    
    public String getpath() {
        return this.path;
    }
    
    public static void newProject(String path) {
        try {
            File file;
            file = new File(path);

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occured");
        }
    }
    
    public void openproject(String input) {
        
    }
//                    
}
