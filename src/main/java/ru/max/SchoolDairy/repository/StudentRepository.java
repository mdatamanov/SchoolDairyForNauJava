package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Student;
import org.springframework.data.repository.CrudRepository;
import ru.max.SchoolDairy.repository.custom.StudentRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long>, StudentRepositoryCustom {
    List<Student> findByBirthDateBetweenOrAddressContainingIgnoreCase(
            LocalDate startDate, LocalDate endDate, String address);
}