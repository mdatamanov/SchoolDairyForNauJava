package ru.max.SchoolDairy.repository.custom;

import ru.max.SchoolDairy.model.Student;
import java.time.LocalDate;
import java.util.List;

public interface StudentRepositoryCustom {

    List<Student> findStudentsByBirthDateBetweenOrAddress(
            LocalDate startDate, LocalDate endDate, String address);

    List<Student> findStudentsByClassAndAge(
            Long classId, Integer minAge, Integer maxAge);
}