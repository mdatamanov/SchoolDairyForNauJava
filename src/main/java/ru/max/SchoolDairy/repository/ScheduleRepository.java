package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@RepositoryRestResource
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}