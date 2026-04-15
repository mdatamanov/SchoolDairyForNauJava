package ru.max.SchoolDairy.repository.custom;

import ru.max.SchoolDairy.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentsByBirthDateBetweenOrAddress(
            LocalDate startDate, LocalDate endDate, String address) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null && endDate != null) {
            Predicate birthDatePredicate = cb.between(root.get("birthDate"), startDate, endDate);
            predicates.add(birthDatePredicate);
        }

        if (address != null && !address.isEmpty()) {
            Predicate addressPredicate = cb.like(
                    cb.lower(root.get("address")),
                    "%" + address.toLowerCase() + "%"
            );
            predicates.add(addressPredicate);
        }

        Predicate finalPredicate = cb.or(predicates.toArray(new Predicate[0]));


        query.where(finalPredicate);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Student> findStudentsByClassAndAge(
            Long classId, Integer minAge, Integer maxAge) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (classId != null) {
            Predicate classPredicate = cb.equal(root.get("studentClass").get("id"), classId);
            predicates.add(classPredicate);
        }

        if (minAge != null && maxAge != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate maxBirthDate = currentDate.minusYears(minAge);
            LocalDate minBirthDate = currentDate.minusYears(maxAge);

            Predicate agePredicate = cb.between(root.get("birthDate"), minBirthDate, maxBirthDate);
            predicates.add(agePredicate);
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}