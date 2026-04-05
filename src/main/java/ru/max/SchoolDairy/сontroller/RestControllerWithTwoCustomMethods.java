package ru.max.SchoolDairy.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.max.SchoolDairy.model.Homework;
import ru.max.SchoolDairy.model.Student;
import ru.max.SchoolDairy.repository.HomeworkRepository;
import ru.max.SchoolDairy.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestControllerWithTwoCustomMethods {

    @Autowired
    HomeworkRepository homeworkRepository;
    StudentRepository studentRepository;

    public RestControllerWithTwoCustomMethods(HomeworkRepository homeworkRepository, StudentRepository studentRepository) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/homework/{teacherId}")
    public List<Homework> findFutureHomeworksByTeacher(@PathVariable Long teacherId,
                                                       @RequestParam LocalDate currentDate){
        return homeworkRepository.findFutureHomeworksByTeacher(teacherId, currentDate);
    }

    @GetMapping("/student")
    public List<Student> findByBirthDateBetweenOrAddressContainingIgnoreCase(@RequestParam LocalDate startDate,
                                                                             @RequestParam LocalDate endDate,
                                                                             @RequestParam String address){
        return studentRepository.findByBirthDateBetweenOrAddressContainingIgnoreCase(startDate, endDate, address);
    }
}
