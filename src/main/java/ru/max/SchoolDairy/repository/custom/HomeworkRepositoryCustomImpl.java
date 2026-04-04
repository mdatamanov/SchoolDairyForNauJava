package ru.max.SchoolDairy.repository.custom;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Homework;
import ru.max.SchoolDairy.model.Subject;
import ru.max.SchoolDairy.model.Teacher;
import ru.max.SchoolDairy.model.SchoolClass;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RepositoryRestResource
@Transactional(readOnly = true)
public class HomeworkRepositoryCustomImpl implements HomeworkRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Homework> findActiveHomeworksByTeacher(Long teacherId, LocalDate currentDate) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homework> query = cb.createQuery(Homework.class);
        Root<Homework> root = query.from(Homework.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<Homework, Teacher> teacherJoin = root.join("teacher");


        if (teacherId != null) {
            Predicate teacherPredicate = cb.equal(teacherJoin.get("id"), teacherId);
            predicates.add(teacherPredicate);
        }

        if (currentDate != null) {
            Predicate datePredicate = cb.greaterThan(root.get("dueDate"), currentDate);
            predicates.add(datePredicate);
        }

        query.orderBy(cb.asc(root.get("dueDate")));

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Homework> findHomeworksBySubjectAndClass(
            Long subjectId, Long classId, LocalDate fromDate, LocalDate toDate) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homework> query = cb.createQuery(Homework.class);
        Root<Homework> root = query.from(Homework.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<Homework, Subject> subjectJoin = root.join("subject");

        Join<Homework, SchoolClass> classJoin = root.join("schoolClass");

        if (subjectId != null) {
            Predicate subjectPredicate = cb.equal(subjectJoin.get("id"), subjectId);
            predicates.add(subjectPredicate);
        }

        if (classId != null) {
            Predicate classPredicate = cb.equal(classJoin.get("id"), classId);
            predicates.add(classPredicate);
        }

        if (fromDate != null && toDate != null) {
            Predicate datePredicate = cb.between(root.get("dueDate"), fromDate, toDate);
            predicates.add(datePredicate);
        }

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.asc(root.get("dueDate")));

        return entityManager.createQuery(query).getResultList();
    }
}