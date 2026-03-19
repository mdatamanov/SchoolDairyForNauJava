package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByBirthDateBetweenAndAddressContainingIgnoreCase(
            LocalDate startDate, LocalDate endDate, String address);
}