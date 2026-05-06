package com.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private Long shiftId;

    private String title;
    private String description;
    private String status;
    private String priority;
}