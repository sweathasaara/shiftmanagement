package com.example.controller;

import com.example.dto.ShiftDTO;
import com.example.entity.ShiftDetails;
import com.example.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping
    public ShiftDetails createShift(@RequestBody ShiftDTO dto) {

        return shiftService.createShift(dto);
    }

    @GetMapping
    public List<ShiftDetails> getAllShifts() {

        return shiftService.getAllShifts();
    }
}