package ru.max.SchoolDairy.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Homework {
    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private String attachments;


    private Subject subject;
    private SchoolClass schoolClass;
    private Teacher teacher;
    private Student student;

    public Homework() {}

    public Homework(Long id, String description, LocalDate dueDate, LocalDateTime createdAt, String attachments, Subject subject, SchoolClass schoolClass, Teacher teacher, Student student) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.attachments = attachments;
        this.subject = subject;
        this.schoolClass = schoolClass;
        this.teacher = teacher;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}