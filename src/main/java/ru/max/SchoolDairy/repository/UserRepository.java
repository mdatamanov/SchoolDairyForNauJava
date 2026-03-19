package ru.max.SchoolDairy.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.SchoolDairy.model.User;

import java.util.List;

@Component
public class UserRepository implements CrudRepository<User, Long> {

    private final List<User> userContainer;

    @Autowired
    public UserRepository(List<User> userContainer) {
        this.userContainer = userContainer;
    }

    @Override
    public void create(User entity) {
        userContainer.add(entity);
    }

    @Override
    public User read(Long id) {
        return userContainer.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(User entity) {
        for (int i = 0; i < userContainer.size(); i++) {
            if (userContainer.get(i).getId().equals(entity.getId())) {
                userContainer.set(i, entity);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        userContainer.removeIf(user -> user.getId().equals(id));
    }
}
