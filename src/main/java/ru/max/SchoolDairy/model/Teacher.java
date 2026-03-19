package ru.max.SchoolDairy.model;

import java.util.List;

public class Teacher extends User{
    // + все поля класса User
    private String qualification;
    private Integer experienceYears;
    private String cabinetNumber;


    private List<Subject> subjects;
    private SchoolClass homeroomClass;


    public Teacher(){

    }

    public Teacher(Long id, String fullName, String login, String password, String qualification, Integer experienceYears, String cabinetNumber, List<Subject> subjects, SchoolClass homeroomClass) {
        super(id, fullName, login, password);
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.cabinetNumber = cabinetNumber;
        this.subjects = subjects;
        this.homeroomClass = homeroomClass;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(String cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public SchoolClass getHomeroomClass() {
        return homeroomClass;
    }

    public void setHomeroomClass(SchoolClass homeroomClass) {
        this.homeroomClass = homeroomClass;
    }
}
