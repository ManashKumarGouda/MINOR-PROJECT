package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class TaskController {

    
    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        
        tasks.add(new Task(1, "Task 1", "Pending"));
        tasks.add(new Task(2, "Task 2", "In Progress"));
        tasks.add(new Task(3, "Task 3", "Completed"));
        tasks.add(new Task(4, "Task 4", "Pending"));
        tasks.add(new Task(5, "Task 5", "In Progress"));
        tasks.add(new Task(6, "Task 6", "Completed"));
    }

    
    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    
    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

    @PutMapping("/task")
    public Task updateTask(@RequestBody Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                return task;
            }
        }
        return null;
    }

    
    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable int id) {

        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task t = iterator.next();

            if (t.getId() == id) {
                iterator.remove();
                return "Task deleted successfully";
            }
        }
        return "Task not found";
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        List<Task> tasksByStatus = new ArrayList<>();
        for (Task t : tasks) {
            if (status.equals(t.getStatus())) {
                tasksByStatus.add(t);
            }
        }
        return tasksByStatus;
    }

    @GetMapping("/status/{id}/{status}")
    public Task getTaskByStatus(@PathVariable int id, @PathVariable String status) {
        for (Task t : tasks) {
            if (t.getId() == id && status.equals(t.getStatus())) {
                return t;
            }
        }
        return null;
    }

}