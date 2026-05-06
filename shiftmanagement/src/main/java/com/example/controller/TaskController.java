package com.example.controller;

import com.example.dto.TaskDTO;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO dto) {
        return taskService.createTask(dto);
    }
    @GetMapping("/shift/{shiftId}")
    public List<TaskDTO> getTasksByShiftId(@PathVariable Long shiftId) {
        return taskService.getTasksByShiftId(shiftId);
    }
    @PutMapping("/{id}")
    public TaskDTO updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return taskService.updateTaskStatus(id, status);
    }
}