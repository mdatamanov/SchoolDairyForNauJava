package ru.max.SchoolDairy.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.max.SchoolDairy.config.Config;
import ru.max.SchoolDairy.dto.Role;
import ru.max.SchoolDairy.model.User;
import ru.max.SchoolDairy.repository.user.UserRepository;

import java.util.Optional;

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
    public void createUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("User with this username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateLogin(Long id, String login) {
        userRepository.findById(id).ifPresent(user -> {
            user.setUsername(login);
            userRepository.save(user);
        });
    }

}