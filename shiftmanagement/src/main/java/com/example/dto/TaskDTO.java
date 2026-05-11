package com.example.dto;

public class TaskDTO {

    private Long id;
    private Long shiftId;
    private String title;
    private String description;
    private String status;
    private String priority;

    public TaskDTO() {
    }

    public TaskDTO(Long id,
                   Long shiftId,
                   String title,
                   String description,
                   String status,
                   String priority) {

        this.id = id;
        this.shiftId = shiftId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}