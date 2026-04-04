package ru.max.SchoolDairy.repository.custom;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Homework;
import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface HomeworkRepositoryCustom {

    /**
    поиск активных домашних заданий учителя
     */
    List<Homework> findActiveHomeworksByTeacher(Long teacherId, LocalDate currentDate);

    List<Homework> findHomeworksBySubjectAndClass(
            Long subjectId, Long classId, LocalDate fromDate, LocalDate toDate);
}