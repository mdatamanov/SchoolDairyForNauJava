package ru.max.SchoolDairy.service;

import ru.max.SchoolDairy.dto.Role;
import ru.max.SchoolDairy.model.User;

public interface UserService {
    void createUser(User user) throws Exception;

    User findUserById(Long id);

    void deleteUser(Long id);

    void updateLogin(Long id, String login);
}
