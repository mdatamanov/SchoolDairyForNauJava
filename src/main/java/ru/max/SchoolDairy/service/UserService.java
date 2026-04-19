package ru.max.SchoolDairy.service;

import ru.max.SchoolDairy.model.User;

import java.util.Optional;

public interface UserService {
    void createUser(User user) throws Exception;

    Optional<User> findUserById(Long id);

    void deleteUser(Long id);

    void updateLogin(Long id, String login);
}
