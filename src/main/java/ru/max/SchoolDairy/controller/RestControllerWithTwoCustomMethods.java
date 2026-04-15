package ru.max.SchoolDairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.max.SchoolDairy.exception.IllegalBadArgumentException;
import ru.max.SchoolDairy.exception.ResourceNotFoundException;
import ru.max.SchoolDairy.model.Homework;
import ru.max.SchoolDairy.model.Student;
import ru.max.SchoolDairy.repository.HomeworkRepository;
import ru.max.SchoolDairy.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestControllerWithTwoCustomMethods {

    private final HomeworkRepository homeworkRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public RestControllerWithTwoCustomMethods(HomeworkRepository homeworkRepository, StudentRepository studentRepository) {
        this.homeworkRepository = homeworkRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/homework/{teacherId}")
    public List<Homework> findFutureHomeworksByTeacher(@PathVariable Long teacherId,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate){
        List<Homework> homeworks = homeworkRepository.findFutureHomeworksByTeacher(teacherId, currentDate);
        if(currentDate.isBefore(LocalDate.now())){
            throw new IllegalBadArgumentException("Дата не может быть в прошлом. Текущая дата: " + LocalDate.now());
        }
        if(teacherId <= 0){
            throw new IllegalBadArgumentException("ID учителя должен быть положительным числом начиная с 1");
        }
        if(homeworks.isEmpty()){
            throw new ResourceNotFoundException("Домашние задания не найдены для учителя с ID: " + teacherId);
        }
        return homeworks;
    }

    @GetMapping("/student")
    public List<Student> findByBirthDateBetweenOrAddressContainingIgnoreCase(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                             @RequestParam String address){
        List<Student> students = studentRepository.findByBirthDateBetweenOrAddressContainingIgnoreCase(startDate, endDate, address);

        if(students.isEmpty()){
            throw new ResourceNotFoundException("Студенты, родившиеся в период с " + startDate + " до " +  endDate + " или проживающие в " + address + " не найдены.");
        }

        return studentRepository.findByBirthDateBetweenOrAddressContainingIgnoreCase(startDate, endDate, address);
    }
}
