package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import com.example.entity.Task;
import java.util.List;
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
    @OneToMany(mappedBy = "shiftDetails")
    private List<Task> tasks;

    private LocalDate workDate;

    private String shiftType;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}