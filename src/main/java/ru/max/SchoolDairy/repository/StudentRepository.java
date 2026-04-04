package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Grade;
import ru.max.SchoolDairy.model.Student;
import org.springframework.data.repository.CrudRepository;
import ru.max.SchoolDairy.repository.custom.StudentRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface StudentRepository extends CrudRepository<Student, Long>, StudentRepositoryCustom {
    List<Student> findByBirthDateBetweenOrAddressContainingIgnoreCase(
            LocalDate startDate, LocalDate endDate, String address);

}