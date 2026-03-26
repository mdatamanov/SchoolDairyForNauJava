package ru.max.SchoolDairy.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    private String qualification;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "cabinet_number")
    private String cabinetNumber;

    @OneToOne
    @JoinColumn(name = "homeroom_class_id", unique = true)
    private SchoolClass homeroomClass;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    public Teacher() {}

    public Teacher(String fullName, String login, String password,
                   String qualification, Integer experienceYears, String cabinetNumber) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.cabinetNumber = cabinetNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }

    public String getCabinetNumber() { return cabinetNumber; }
    public void setCabinetNumber(String cabinetNumber) { this.cabinetNumber = cabinetNumber; }

    public SchoolClass getHomeroomClass() { return homeroomClass; }
    public void setHomeroomClass(SchoolClass homeroomClass) { this.homeroomClass = homeroomClass; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }
}