package ru.max.SchoolDairy;

import ru.max.SchoolDairy.model.*;
import ru.max.SchoolDairy.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.max.SchoolDairy.service.GradeService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SchoolDiaryTests {

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

    private static SchoolClass testClass;
    private static Teacher testTeacher;
    private static Subject testSubject;

    @BeforeAll
    public static void setup(@Autowired SchoolClassRepository classRepo,
                             @Autowired TeacherRepository teacherRepo,
                             @Autowired SubjectRepository subjectRepo) {
        // Создаем учителя
        testTeacher = new Teacher();
        testTeacher.setFullName("Тестов Учитель");
        testTeacher.setLogin("teacher_" + System.currentTimeMillis());
        testTeacher.setPassword("123");
        testTeacher = teacherRepo.save(testTeacher);

        // Создаем класс
        testClass = new SchoolClass();
        testClass.setClassName("5А");
        testClass.setAcademicYear("2024-2025");
        testClass.setHomeroomTeacher(testTeacher);
        testClass = classRepo.save(testClass);

        // Создаем предмет
        testSubject = new Subject();
        testSubject.setName("Математика");
        testSubject.setTeacher(testTeacher);
        testSubject = subjectRepo.save(testSubject);
    }

    // ===== ТЕСТЫ ПУНКТА 5 (Query Lookup Strategy) =====

    @Test
    @Order(1)
    public void testQueryLookupStrategy() {
        List<Student> students = studentRepository
                .findByBirthDateBetweenOrAddressContainingIgnoreCase(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(2015, 12, 31),
                        "Москва");
        assertNotNull(students);
    }

    // ===== ТЕСТЫ ПУНКТА 6 (Criteria API) =====

    @Test
    @Order(2)
    public void testCriteriaApiStudent() {
        List<Student> students = studentRepository
                .findStudentsByBirthDateBetweenOrAddress(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(2015, 12, 31),
                        "Москва");
        assertNotNull(students);
    }

    @Test
    @Order(3)
    public void testCriteriaApiHomework() {
        List<Homework> homeworks = homeworkRepository
                .findActiveHomeworksByTeacher(testTeacher.getId(), LocalDate.now());
        assertNotNull(homeworks);
    }

    // ===== ТЕСТЫ ПУНКТА 7 (Транзакционный метод) =====

    @Test
    @Order(4)
    public void testTransactionSuccess() {
        // Создаем ученика
        Student student = new Student();
        student.setFullName("Тестов Ученик");
        student.setLogin("test_login_" + System.currentTimeMillis());
        student.setPassword("123");
        student.setBirthDate(LocalDate.of(2010, 1, 1));
        student.setAddress("Москва");
        student.setStudentClass(testClass);
        student = studentRepository.save(student);

        // Добавляем оценку с предметом и учителем
        Grade grade = gradeService.addGradeToStudent(
                student.getId(),           // studentId
                testSubject.getId(),       // subjectId
                testTeacher.getId(),       // teacherId
                5,                         // value
                "Отлично",                 // comment
                1                          // term
        );

        // Проверяем
        assertNotNull(grade.getId());
        assertEquals(5, grade.getValue());
        assertNotNull(grade.getSubject());
        assertNotNull(grade.getTeacher());
    }

    @Test
    @Order(5)
    public void testTransactionRollbackStudentNotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            gradeService.addGradeToStudent(
                    999L,                    // studentId
                    testSubject.getId(),     // subjectId
                    testTeacher.getId(),     // teacherId
                    5,                       // value
                    "Отлично",               // comment
                    1                        // term
            );
        });

        assertTrue(exception.getMessage().contains("не найден"));
    }

    @Test
    @Order(6)
    public void testTransactionRollbackOnError() {
        // Создаем ученика с классом
        Student student = new Student();
        student.setFullName("Тестов Ученик 2");
        student.setLogin("test_login2_" + System.currentTimeMillis());
        student.setPassword("123");
        student.setBirthDate(LocalDate.of(2010, 1, 1));
        student.setAddress("Москва");
        student.setStudentClass(testClass);
        student = studentRepository.save(student);

        long countBefore = gradeRepository.count();

        // Пытаемся добавить оценку с null значением (value = null)
        try {
            gradeService.addGradeToStudent(
                    student.getId(),           // studentId
                    testSubject.getId(),       // subjectId
                    testTeacher.getId(),       // teacherId
                    null,                      // value - это null вызовет ошибку
                    "Ошибка",                   // comment
                    1                           // term
            );
        } catch (Exception e) {
            // Игнорируем
        }

        long countAfter = gradeRepository.count();
        assertEquals(countBefore, countAfter, "Транзакция должна откатиться");
    }
}