package com.example.taskman;

import com.example.taskman.enums.TaskPriority;
import com.example.taskman.repository.TaskRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO
// fix delete function
// incorporate SQLite later on


@ShellComponent
public class TaskCommands {

    private final TaskRepository taskRepository;

    public TaskCommands(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @ShellMethod(key = "hello", value = "Computer gives its salutations to you | sayHello [name]")
    String sayHello(@ShellOption(defaultValue = "ok fine, human") String user){
        return "Hello " + user;
    }

    @ShellMethod(key = "newTask", value = "Add new task | newTask [description]")
    void addTask() {
        taskRepository.addTask();
    }

    @ShellMethod(key = "listTasks",value = "List all tasks")
    public String listTasks() {

        Map<TaskPriority, List<Task>> tasksByPriority =
                taskRepository.getAll().stream()
                        .collect(Collectors.groupingBy(
                                Task::getPriority,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        if (taskRepository.getAll().isEmpty()) { return "The list is empty."; }
        else {
            return taskRepository.formatTasksByPriority(tasksByPriority);
        }
    }

    @ShellMethod(key = "deleteTask", value = "Remove a task from your list | deleteTask [nameOfTask]")
    public void deleteTask(@ShellOption String taskToDelete) {
        taskRepository.deleteTask(taskToDelete);
    }

}
