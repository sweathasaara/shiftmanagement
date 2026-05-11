package com.example.service;

import com.example.entity.ShiftDetails;
import com.example.entity.Task;
import com.example.repository.ShiftDetailsRepository;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class ShiftScheduler {

    @Autowired
    private ShiftDetailsRepository shiftRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000)
    public void updateShiftAutomatically() {

        List<ShiftDetails> shifts = shiftRepository.findAll();
        DayOfWeek today = DayOfWeek.SATURDAY;
        if (today == DayOfWeek.SATURDAY
                || today == DayOfWeek.SUNDAY) {

            for (ShiftDetails shift : shifts) {
                shift.setStatus("Paused");
                shiftRepository.save(shift);
                List<Task> tasks =
                        taskRepository.findByShiftDetailsId(
                                shift.getId());
                for (Task task : tasks) {
                    if (!task.getStatus()
                            .equalsIgnoreCase("COMPLETED")) {
                        task.setStatus("PAUSED");
                        taskRepository.save(task);
                    }
                }
            }
            System.out.println("Weekend detected - shifts paused");
            return;
        }
        LocalTime currentTime = LocalTime.now();

        for (ShiftDetails shift : shifts) {
            if (shift.getStatus()
                    .equalsIgnoreCase("Paused")) {
                shift.setStatus("Active");
                shiftRepository.save(shift);
                List<Task> tasks =
                        taskRepository.findByShiftDetailsId(
                                shift.getId());
                for (Task task : tasks) {

                    if (task.getStatus()
                            .equalsIgnoreCase("PAUSED")) {

                        task.setStatus("IN_PROGRESS");

                        taskRepository.save(task);
                    }
                }
            }
            if (shift.getEndTime().isBefore(currentTime)
                    && shift.getStatus()
                    .equalsIgnoreCase("Active")) {
                shift.setStatus("Completed");
                if (shift.getShiftType()
                        .equalsIgnoreCase("Morning")) {
                    shift.setShiftType("Night");
                    shift.setStartTime(LocalTime.of(17, 0));
                    shift.setEndTime(LocalTime.of(1, 0));
                } else {
                    shift.setShiftType("Morning");
                    shift.setStartTime(LocalTime.of(9, 0));
                    shift.setEndTime(LocalTime.of(17, 0));
                }
                shift.setStatus("Active");
                shiftRepository.save(shift);
                System.out.println("Shift updated automatically");
            }
        }
    }
}