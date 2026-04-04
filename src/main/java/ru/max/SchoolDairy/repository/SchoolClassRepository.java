package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.SchoolClass;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

@RepositoryRestResource
public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
}