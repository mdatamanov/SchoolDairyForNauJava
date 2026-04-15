package ru.max.SchoolDairy.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.max.SchoolDairy.model.Admin;
import org.springframework.data.repository.CrudRepository;

@RepositoryRestResource
public interface AdminRepository extends CrudRepository<Admin, Long> {
}