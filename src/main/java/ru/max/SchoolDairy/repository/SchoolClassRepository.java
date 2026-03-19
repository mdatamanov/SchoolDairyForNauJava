package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.SchoolClass;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
}