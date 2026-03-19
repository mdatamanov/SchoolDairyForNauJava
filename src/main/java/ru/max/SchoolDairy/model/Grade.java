package ru.max.SchoolDairy.model;

import java.time.LocalDate;

public class Grade {
    private Long id;
    private Integer value;
    private LocalDate date;
    private String comment;
    private Integer term;

    private Student student;
    private Subject subject;
    private Teacher teacher;

    public Grade() {}

    public Grade(Integer value, LocalDate date, Integer term) {
        this.value = value;
        this.date = date;
        this.term = term;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Integer getTerm() { return term; }
    public void setTerm(Integer term) { this.term = term; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}