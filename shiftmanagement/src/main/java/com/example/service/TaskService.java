package com.example.service;

import com.example.dto.TaskDTO;
import com.example.entity.ShiftDetails;
import com.example.entity.Task;
import com.example.repository.ShiftDetailsRepository;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ShiftDetailsRepository shiftDetailsRepository;
    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getShiftDetails().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority()
        );
    }
    public TaskDTO createTask(TaskDTO dto) {
        ShiftDetails shift = shiftDetailsRepository
                .findById(dto.getShiftId())
                .orElse(null);

        if (shift == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setShiftDetails(shift);
        return convertToDTO(taskRepository.save(task));
    }
    public List<TaskDTO> getTasksByShiftId(Long shiftId) {
        return taskRepository.findByShiftDetailsId(shiftId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public TaskDTO updateTaskStatus(Long id, String status) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            return convertToDTO(taskRepository.save(task));
        }
        return null;
    }
}