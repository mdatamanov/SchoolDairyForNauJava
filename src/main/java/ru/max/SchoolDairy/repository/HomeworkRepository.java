package ru.max.SchoolDairy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.max.SchoolDairy.model.Homework;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.List;

public interface HomeworkRepository extends CrudRepository<Homework, Long> {

    @Query("SELECT h FROM Homework h WHERE h.teacher.id = :teacherId AND h.dueDate > :currentDate")
    List<Homework> findFutureHomeworksByTeacher(@Param("teacherId") Long teacherId,
                                                @Param("currentDate") LocalDate currentDate);
}