package ru.max.SchoolDairy.service;

import ru.max.SchoolDairy.model.Grade;
import ru.max.SchoolDairy.model.Student;
import ru.max.SchoolDairy.model.Subject;
import ru.max.SchoolDairy.model.Teacher;
import ru.max.SchoolDairy.repository.GradeRepository;
import ru.max.SchoolDairy.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.max.SchoolDairy.repository.SubjectRepository;
import ru.max.SchoolDairy.repository.TeacherRepository;

import java.time.LocalDate;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;


    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    /**
     * Транзакционный метод добавления оценки ученику
     */
    @Transactional
    public void addGradeToStudent(Long studentId, Long subjectId, Long teacherId,
                                   Integer value, String comment, Integer term) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Ученик с id " + studentId + " не найден"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Предмет с id " + subjectId + " не найден"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Учитель с id " + teacherId + " не найден"));

        Grade grade = new Grade();
        grade.setValue(value);
        grade.setDate(LocalDate.now());
        grade.setComment(comment);
        grade.setTerm(term);
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setTeacher(teacher);

        student.getGrades().add(grade);
        grade.setStudent(student);

        studentRepository.save(student);
    }
}