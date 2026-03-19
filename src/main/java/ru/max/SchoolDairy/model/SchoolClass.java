package ru.max.SchoolDairy.model;

import java.util.List;

public class SchoolClass {
    private Long id;
    private String name;
    private String academicYear;
    private String roomNumber;
    private Teacher homeroomTeacher;
    private List<Student> students;
    private List<Homework> homeworks;

    public SchoolClass() {}

    public SchoolClass(String name, String academicYear, String roomNumber) {
        this.name = name;
        this.academicYear = academicYear;
        this.roomNumber = roomNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
