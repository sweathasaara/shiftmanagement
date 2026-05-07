package com.example.service;

import com.example.entity.ShiftDetails;
import com.example.repository.ShiftDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.util.List;
@Component
public class ShiftScheduler {
    @Autowired
    private ShiftDetailsRepository shiftRepository;
    @Scheduled(fixedRate = 60000)
    public void updateShiftAutomatically() {
        List<ShiftDetails> shifts = shiftRepository.findAll();
        LocalTime currentTime = LocalTime.now();
        for (ShiftDetails shift : shifts) {
            if (shift.getEndTime().isBefore(currentTime)
                    && shift.getStatus().equalsIgnoreCase("Active")) {
                shift.setStatus("Completed");
                if (shift.getShiftType().equalsIgnoreCase("Morning")) {
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