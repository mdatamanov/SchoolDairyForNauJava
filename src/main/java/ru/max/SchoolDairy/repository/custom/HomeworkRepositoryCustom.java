package ru.max.SchoolDairy.repository.custom;

import org.springframework.stereotype.Repository;
import ru.max.SchoolDairy.model.Homework;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomeworkRepositoryCustom {

    /**
    поиск активных домашних заданий учителя
     */
    List<Homework> findActiveHomeworksByTeacher(Long teacherId, LocalDate currentDate);

    List<Homework> findHomeworksBySubjectAndClass(
            Long subjectId, Long classId, LocalDate fromDate, LocalDate toDate);
}