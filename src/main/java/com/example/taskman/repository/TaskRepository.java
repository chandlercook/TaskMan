package com.example.taskman.repository;

import com.example.taskman.Task;
import com.example.taskman.enums.TaskPriority;
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

    private String normalize(String s) {
        return s.trim().replaceAll("\\s+", " ").toLowerCase();
    }


    public void deleteTask(String taskToDelete) {

        Iterator<Task> iterator = tasks.iterator(); // iterator's start position sits behind index 0 of the list
        boolean found = false;

        String target = normalize(taskToDelete);

        while (iterator.hasNext()) { // additionally checks if the list is empty ^^
            Task task = iterator.next();
            String desc = normalize(task.getDescription());

            System.out.println("DEBUG: [" + task.getDescription() + "]");

            if (desc.equals(target)) {
                iterator.remove();
                found = true;
                System.out.println(taskToDelete + " has been removed.");
                break;
            }
        }
        if (!found){
            System.out.println("Task not in list.");
        }
    }

}
