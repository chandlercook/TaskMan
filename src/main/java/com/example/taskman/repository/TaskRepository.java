package com.example.taskman.repository;

import com.example.taskman.Task;
import jakarta.annotation.Priority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void addTask(){

        String taskName;

        System.out.println("Please type the name of the task: ");
        taskName = sc.nextLine();

        System.out.println("Now priority !!! - !! - ! : ");
        String priorityMark = sc.nextLine();

        try {
            TaskPriority taskPriority = TaskPriority.fromInput(priorityMark);
            tasks.add(new Task(taskName, taskPriority));
            System.out.println("Added");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Please use !!!, !!, or !");
        }
    }

    public List<Task> getAll() {
        return List.copyOf(tasks); // safe read-only copy
    }

    public String formatTasksByPriority(Map<TaskPriority, List<Task>> tasksByPriority){
        StringBuilder sb = new StringBuilder();

        tasksByPriority.forEach((priority, tasks) -> {
            sb.append(priority.getDisplay()).append("\n");

            tasks.forEach(task ->
                    sb.append("- ").append(task.getDescription()).append("\n")
                    );

            sb.append("\n");
        });

        return sb.toString();
    }

}
