package ru.max.SchoolDairy.repository.custom;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Student;
import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface StudentRepositoryCustom {

    List<Student> findStudentsByBirthDateBetweenOrAddress(
            LocalDate startDate, LocalDate endDate, String address);

    List<Student> findStudentsByClassAndAge(
            Long classId, Integer minAge, Integer maxAge);
}