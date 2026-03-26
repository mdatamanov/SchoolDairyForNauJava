package ru.max.SchoolDairy;

import ru.max.SchoolDairy.model.*;
import ru.max.SchoolDairy.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.max.SchoolDairy.service.GradeService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SchoolDiaryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SchoolClassRepository classRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private SchoolClass testClass;
    private Teacher testTeacher;
    private Subject testSubject;

    @BeforeEach
    public void setUp() {
        testTeacher = new Teacher();
        testTeacher.setFullName("Тестов Учитель");
        testTeacher.setLogin("teacher_" + System.currentTimeMillis() + "_" + Math.random());
        testTeacher.setPassword("123");
        testTeacher = teacherRepository.save(testTeacher);

        testClass = new SchoolClass();
        testClass.setClassName("5А");
        testClass.setAcademicYear("2024-2025");
        testClass.setHomeroomTeacher(testTeacher);
        testClass = classRepository.save(testClass);

        testSubject = new Subject();
        testSubject.setName("Математика");
        testSubject.setTeacher(testTeacher);
        testSubject = subjectRepository.save(testSubject);
    }

    private Student createTestStudent() {
        Student student = new Student();
        student.setFullName("Тестов Ученик");
        student.setLogin("test_login_" + System.currentTimeMillis() + "_" + Math.random());
        student.setPassword("123");
        student.setBirthDate(LocalDate.of(2010, 1, 1));
        student.setAddress("Москва");
        student.setStudentClass(testClass);
        return studentRepository.save(student);
    }

    @Test
    void testQueryLookupStrategy() {
        Student student = createTestStudent();

        List<Student> students = studentRepository
                .findByBirthDateBetweenOrAddressContainingIgnoreCase(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(2015, 12, 31),
                        "Москва");

        assertNotNull(students);
        assertTrue(students.stream().anyMatch(s -> s.getId().equals(student.getId())));
    }

    @Test
    void testCriteriaApiStudent() {
        Student student = createTestStudent();

        List<Student> students = studentRepository
                .findStudentsByBirthDateBetweenOrAddress(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(2015, 12, 31),
                        "Москва");

        assertNotNull(students);
        assertTrue(students.stream().anyMatch(s -> s.getId().equals(student.getId())));
    }

    @Test
    void testCriteriaApiHomework() {
        Student student = createTestStudent();

        Homework homework = new Homework();
        homework.setTeacher(testTeacher);
        homework.setSubject(testSubject);
        homework.setStudent(student);
        homework.setDescription("Домашнее задание");
        homework.setSchoolClass(testClass);
        homework.setDueDate(LocalDate.now().plusDays(1));

        Homework savedHomework = homeworkRepository.save(homework);

        List<Homework> homeworks = homeworkRepository
                .findActiveHomeworksByTeacher(testTeacher.getId(), LocalDate.now());

        assertNotNull(homeworks);
        assertTrue(homeworks.stream().anyMatch(h -> h.getId().equals(savedHomework.getId())));
    }

    @Test
    void testTransactionRollbackStudentNotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            gradeService.addGradeToStudent(
                    999L,
                    testSubject.getId(),
                    testTeacher.getId(),
                    5,
                    "Отлично",
                    1
            );
        });

        assertTrue(exception.getMessage().contains("не найден"));
    }

    @Test
    void testTransactionRollbackOnError() {
        Student student = createTestStudent();

        long countBefore = gradeRepository.count();

        assertThrows(Exception.class, () -> {
            gradeService.addGradeToStudent(
                    student.getId(),
                    testSubject.getId(),
                    testTeacher.getId(),
                    null,
                    "Ошибка",
                    1
            );
        });

        long countAfter = gradeRepository.count();
        assertEquals(countBefore, countAfter, "Транзакция должна откатиться");
    }
}