package ru.max.SchoolDairy.service;

import ru.max.SchoolDairy.model.User;

public interface UserService {
    void createUser(Long id, String fullName, String login, String password);

    User findUserById(Long id);

    void deleteUser(Long id);

    void updateLogin(Long id, String login);
}
