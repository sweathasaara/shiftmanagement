package com.example.service;

import com.example.dto.ShiftDTO;
import com.example.entity.ShiftDetails;
import com.example.entity.User;
import com.example.repository.ShiftDetailsRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {

    @Autowired
    private ShiftDetailsRepository shiftRepository;

    @Autowired
    private UserRepository userRepository;

    public ShiftDetails createShift(ShiftDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElse(null);

        if (user == null) {
            return null;
        }

        ShiftDetails shift = new ShiftDetails();

        shift.setUser(user);
        shift.setWorkDate(java.time.LocalDate.parse(dto.getWorkDate()));
        shift.setShiftType(dto.getShiftType());
        shift.setStartTime(java.time.LocalTime.parse(dto.getStartTime()));
        shift.setEndTime(java.time.LocalTime.parse(dto.getEndTime()));
        shift.setStatus(dto.getStatus());

        return shiftRepository.save(shift);
    }
}