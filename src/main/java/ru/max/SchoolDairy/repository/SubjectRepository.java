package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}