package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Grade;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {
}