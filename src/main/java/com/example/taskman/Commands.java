package com.example.taskman;

import com.example.taskman.repository.TaskPriority;
import com.example.taskman.repository.TaskRepository;
import jakarta.annotation.Priority;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ShellComponent
public class Commands {

    private final TaskRepository taskRepository;

    public Commands(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @ShellMethod(key = "hello", value = "Computer gives its salutations to you.")
    String sayHello(@ShellOption(defaultValue = "ok fine, human") String user){
        return "Hello " + user;
    }

    @ShellMethod(key = "newTask", value = "Add new task")
    void addTask() {
        taskRepository.addTask();
    }

    @ShellMethod(value = "List all tasks")
    public String listTasks() {

        Map<TaskPriority, List<Task>> tasksByPriority =
                taskRepository.getAll().stream()
                        .collect(Collectors.groupingBy(
                                Task::getPriority,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        return taskRepository.formatTasksByPriority(tasksByPriority);
    }

}
