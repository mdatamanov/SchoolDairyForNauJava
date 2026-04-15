package ru.max.SchoolDairy.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.max.SchoolDairy.config.Config;
import ru.max.SchoolDairy.dto.Role;
import ru.max.SchoolDairy.model.User;
import ru.max.SchoolDairy.repository.User.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private Config appConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;


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
        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void updateLogin(Long id, String login) {

    }

    public void addUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getName()).isPresent()) {
            throw new Exception("User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

}