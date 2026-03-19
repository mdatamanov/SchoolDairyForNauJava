package ru.max.SchoolDairy.model;

import java.util.List;

public class Subject {
    private Long id;
    private String name;
    private String description;
    private Integer hoursPerWeek;
    private Teacher teacher;
    private List<Grade> grades;
    private List<Homework> homeworks;

    public Subject() {}

    public Subject(String name, String description, Integer hoursPerWeek) {
        this.name = name;
        this.description = description;
        this.hoursPerWeek = hoursPerWeek;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getHoursPerWeek() { return hoursPerWeek; }
    public void setHoursPerWeek(Integer hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public List<Grade> getGrades() { return grades; }
    public void setGrades(List<Grade> grades) { this.grades = grades; }

    public List<Homework> getHomeworks() { return homeworks; }
    public void setHomeworks(List<Homework> homeworks) { this.homeworks = homeworks; }
}
