package ru.max.SchoolDairy.repository.custom;

import ru.max.SchoolDairy.model.Homework;
import java.time.LocalDate;
import java.util.List;

public interface HomeworkRepositoryCustom {

    // поиск активных домашних заданий учителя
    List<Homework> findActiveHomeworksByTeacher(Long teacherId, LocalDate currentDate);

    // поиск домашних заданий по предмету и классу с фильтром по дате
    List<Homework> findHomeworksBySubjectAndClass(
            Long subjectId, Long classId, LocalDate fromDate, LocalDate toDate);
}