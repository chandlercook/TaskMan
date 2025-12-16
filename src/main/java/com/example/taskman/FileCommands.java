package com.example.taskman;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@ShellComponent
public class FileCommands {

    private final TaskCommands taskCommands;

    public FileCommands(TaskCommands taskCommands) {
        this.taskCommands = taskCommands;
    }

    @ShellMethod(key = "makeFile", value = "Makes a file on your computer of your current tasks |  makeTaskFile [exampleFileName]")
    public void makeFile(@ShellOption String fileName) {

        // Create file
        try {
            File Obj = new File(fileName + ".txt");

            if (Obj.createNewFile()) {
                System.out.println("File Created" + Obj.getName() + "\n");
            } else {
                System.out.println("Updating file...");
            }
        } catch (IOException e) {
            System.out.println("An error occurred upon creating a file");
            e.printStackTrace();
        }

        // Write to file
        try {
            FileWriter Writer = new FileWriter(fileName + ".txt");

            Writer.write(taskCommands.listTasks());
            Writer.close();

            System.out.println("Successfully written");
        } catch (IOException e) {
            System.out.println("An error occurred upon writing to the file");
            e.printStackTrace();
        }
    }
}


