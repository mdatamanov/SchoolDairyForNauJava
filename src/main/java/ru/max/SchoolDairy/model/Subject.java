package ru.max.SchoolDairy.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "hours_per_week")
    private Integer hoursPerWeek;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

    @OneToMany(mappedBy = "subject")
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