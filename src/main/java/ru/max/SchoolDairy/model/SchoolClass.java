package ru.max.SchoolDairy.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToOne
    @JoinColumn(name = "homeroom_teacher_id", unique = true)
    private Teacher homeroomTeacher;

    @OneToMany(mappedBy = "studentClass")
    private List<Student> students;

    @OneToMany(mappedBy = "schoolClass")
    private List<Homework> homeworks;

    public SchoolClass() {}

    public SchoolClass(String className, String academicYear, String roomNumber) {
        this.className = className;
        this.academicYear = academicYear;
        this.roomNumber = roomNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Teacher getHomeroomTeacher() { return homeroomTeacher; }
    public void setHomeroomTeacher(Teacher homeroomTeacher) { this.homeroomTeacher = homeroomTeacher; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public List<Homework> getHomeworks() { return homeworks; }
    public void setHomeworks(List<Homework> homeworks) { this.homeworks = homeworks; }
}