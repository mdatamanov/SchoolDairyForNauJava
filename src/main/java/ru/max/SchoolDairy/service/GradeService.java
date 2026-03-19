package ru.max.SchoolDairy.service;

import ru.max.SchoolDairy.model.Grade;
import ru.max.SchoolDairy.model.Student;
import ru.max.SchoolDairy.repository.GradeRepository;
import ru.max.SchoolDairy.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * Транзакционный метод добавления оценки ученику
     * Если ученик не найден - всё откатывается
     */
    @Transactional
    public Grade addGradeToStudent(Long studentId, Integer value, String comment, Integer term) {


        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Ученик с id " + studentId + " не найден"));


        Grade grade = new Grade();
        grade.setValue(value);
        grade.setDate(LocalDate.now());
        grade.setComment(comment);
        grade.setTerm(term);
        grade.setStudent(student);


        Grade savedGrade = gradeRepository.save(grade);

        return savedGrade;
    }
}