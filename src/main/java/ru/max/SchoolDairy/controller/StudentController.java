package ru.max.SchoolDairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.max.SchoolDairy.model.Student;
import ru.max.SchoolDairy.repository.StudentRepository;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    public String getAllStudents(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students",students);
        return "student";
    }
}
