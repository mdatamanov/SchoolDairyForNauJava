package ru.max.SchoolDairy.repository;

import ru.max.SchoolDairy.model.Admin;
import org.springframework.data.repository.CrudRepository;


public interface AdminRepository extends CrudRepository<Admin, Long> {
}