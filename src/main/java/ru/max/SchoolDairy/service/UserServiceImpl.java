package ru.max.SchoolDairy.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.max.SchoolDairy.config.Config;
import ru.max.SchoolDairy.dto.Role;
import ru.max.SchoolDairy.model.User;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private Config appConfig;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, Config config) {
        this.userRepository = userRepository;
        this.appConfig = config;
    }

    @PostConstruct
    public void init() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ПРИЛОЖЕНИЕ ЗАПУЩЕНО");
        System.out.println("Название: " + appConfig.getAppName());
        System.out.println("Версия: " + appConfig.getAppVersion());
        System.out.println("=".repeat(50) + "\n");
    }


    @Override
    public void createUser(Long id, String name, String login, String password,  Role role) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        userRepository.create(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.read(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void updateLogin(Long id, String login) {
        User user = findUserById(id);
        user.setLogin(login);
        userRepository.update(user);
    }
}