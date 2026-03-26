package ru.max.SchoolDairy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.SchoolDairy.model.User;

import java.util.List;

@Component
public class UserRepository implements CrudRepository<User, Long> {

    @Override
    public void create(User entity) {

    }

    @Override
    public User read(Long aLong) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
