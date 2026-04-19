package ru.max.SchoolDairy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.max.SchoolDairy.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
