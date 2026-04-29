package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shiftdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate workDate;

    private String shiftType;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}