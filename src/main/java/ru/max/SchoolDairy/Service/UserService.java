package ru.max.SchoolDairy.Service;

import ru.max.SchoolDairy.model.User;

import java.util.List;

public interface UserService {
    void createUser(Long id, String fullName, String login, String password);
    User findUserById(Long id);
    void deleteUser(Long id);
    void updateLogin(Long id, String login);
}
