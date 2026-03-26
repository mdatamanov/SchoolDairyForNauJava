package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}