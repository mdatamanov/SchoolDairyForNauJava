package ru.max.SchoolDairy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.SchoolDairy.Service.UserService;
import ru.max.SchoolDairy.model.User;

import java.util.Scanner;

@Component
public class ConsoleInterface {

    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public ConsoleInterface(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Создать пользователя");
            System.out.println("2. Найти пользователя");
            System.out.println("3. Обновить логин");
            System.out.println("4. Удалить пользователя");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createUser();
                    break;
                case "2":
                    findUser();
                    break;
                case "3":
                    updateLogin();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "0":
                    System.out.println("Программа завершена");
                    return;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private void createUser() {
        System.out.println("\n--- Создание пользователя ---");

        System.out.print("ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("ФИО: ");
        String fullName = scanner.nextLine();

        System.out.print("Логин: ");
        String login = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        userService.createUser(id, fullName, login, password);
        System.out.println("Пользователь создан!");
    }

    private void findUser() {
        System.out.println("\n--- Поиск пользователя ---");

        System.out.print("ID пользователя: ");
        Long id = Long.parseLong(scanner.nextLine());

        User user = userService.findUserById(id);

        if (user != null) {
            System.out.println("Найден пользователь:");
            System.out.println("ID: " + user.getId());
            System.out.println("ФИО: " + user.getFullName());
            System.out.println("Логин: " + user.getLogin());
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void updateLogin() {
        System.out.println("\n--- Обновление логина ---");

        System.out.print("ID пользователя: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Новый логин: ");
        String newLogin = scanner.nextLine();

        userService.updateLogin(id, newLogin);
        System.out.println("Логин обновлен!");
    }

    private void deleteUser() {
        System.out.println("\n--- Удаление пользователя ---");

        System.out.print("ID пользователя: ");
        Long id = Long.parseLong(scanner.nextLine());

        userService.deleteUser(id);
        System.out.println("Пользователь удален!");
    }
}
