package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Subject;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}