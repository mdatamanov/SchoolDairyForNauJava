package ru.max.SchoolDairy.model;


import java.time.LocalDate;
import java.util.List;

public class Student extends User{
    // + все поля класса User
    private LocalDate birthDate;
    private String address;


    private SchoolClass studentClass;
    private List<Grade> grades;
    private List<Homework> homeworks;

    public Student(){

    }

    public Student(Long id, String fullName, String login, String password, LocalDate birthDate, String address, SchoolClass studentClass, List<Grade> grades, List<Homework> homeworks) {
        super(id, fullName, login, password);
        this.birthDate = birthDate;
        this.address = address;
        this.studentClass = studentClass;
        this.grades = grades;
        this.homeworks = homeworks;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SchoolClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(SchoolClass studentClass) {
        this.studentClass = studentClass;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    
}
