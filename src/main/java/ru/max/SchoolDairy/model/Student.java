package ru.max.SchoolDairy.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String address;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private SchoolClass studentClass;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @OneToMany(mappedBy = "student")
    private List<Homework> homeworks;

    public Student() {}

    public Student(String fullName, String login, String password,
                   LocalDate birthDate, String address) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public SchoolClass getStudentClass() { return studentClass; }
    public void setStudentClass(SchoolClass studentClass) { this.studentClass = studentClass; }

    public List<Grade> getGrades() { return grades; }
    public void setGrades(List<Grade> grades) { this.grades = grades; }

    public List<Homework> getHomeworks() { return homeworks; }
    public void setHomeworks(List<Homework> homeworks) { this.homeworks = homeworks; }
}

