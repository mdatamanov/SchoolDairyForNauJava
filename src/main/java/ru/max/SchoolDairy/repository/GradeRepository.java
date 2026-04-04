package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Grade;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface GradeRepository extends CrudRepository<Grade, Long> {
}