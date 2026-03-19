package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Teacher;
import org.springframework.data.repository.CrudRepository;


public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}