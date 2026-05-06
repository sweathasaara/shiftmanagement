package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDTO {

    private Long id;

    private Long userId;

    private String workDate;

    private String shiftType;

    private String startTime;

    private String endTime;

    private String status;
}