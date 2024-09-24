package com.apps.wave.news.service;

import java.util.Map;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final TaskService taskService;

    public AdminService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void approveNews(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task != null) {
            taskService.complete(taskId); // Completes the approval task
            System.out.println("News approved!");
        } else {
            System.out.println("Task not found!");
        }
    }

    public void rejectNews(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task != null) {
            taskService.complete(taskId, Map.of("approved", false)); // Marks as rejected
            System.out.println("News rejected!");
        } else {
            System.out.println("Task not found!");
        }
    }
}
