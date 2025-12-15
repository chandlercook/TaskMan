package com.example.taskman;

import com.example.taskman.repository.TaskPriority;

public class Task {

    private final String description;
    private final TaskPriority taskPriority;

    public Task(String description, TaskPriority taskPriority) {
        this.description = description;
        this.taskPriority = taskPriority;
    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getPriority() {
        return taskPriority;
    }




//    @Override
//    public String toString() {
//        return switch (taskPriority) {
//            case "!!!" -> "!!!\n" + "- " + description;
//            case "!!" -> "!!\n" + "- " + description;
//            case "!" -> "!\n" + "- " + description;
//            default -> "empty list";
//        };
//    }
}
