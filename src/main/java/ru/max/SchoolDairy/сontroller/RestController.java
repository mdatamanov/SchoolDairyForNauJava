package ru.max.SchoolDairy.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.max.SchoolDairy.model.Homework;
import ru.max.SchoolDairy.model.Student;
import ru.max.SchoolDairy.repository.HomeworkRepository;
import ru.max.SchoolDairy.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    HomeworkRepository homeworkRepository;
    StudentRepository studentRepository;

    public RestController(HomeworkRepository homeworkRepository, StudentRepository studentRepository) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/homework/{teacherId}")
    public List<Homework> findFutureHomeworksByTeacher(@PathVariable Long teacherId,
                                                       @RequestBody LocalDate currentDate){
        return homeworkRepository.findFutureHomeworksByTeacher(teacherId, currentDate);
    }

    @GetMapping("/student/")
    public List<Student> findByBirthDateBetweenOrAddressContainingIgnoreCase(@RequestBody LocalDate startDate,
                                                                             @RequestBody LocalDate endDate,
                                                                             @RequestBody String address){
        return studentRepository.findByBirthDateBetweenOrAddressContainingIgnoreCase(startDate, endDate, address);
    }
}
