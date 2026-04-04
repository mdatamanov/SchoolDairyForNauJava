package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Teacher;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}