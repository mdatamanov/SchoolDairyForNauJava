package ru.max.SchoolDairy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.max.SchoolDairy.dto.ReportStatus;
import ru.max.SchoolDairy.model.Report;
import ru.max.SchoolDairy.model.User;
import ru.max.SchoolDairy.repository.ReportRepository;
import ru.max.SchoolDairy.repository.user.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    public Report getReport(Long id){
        return reportRepository.findById(id).orElse(null);
    }

    public Long createReport() {
        Report report = new Report(ReportStatus.CREATED);
        reportRepository.save(report);
        return report.getId();
    }

    public CompletableFuture<Void> generateReportAsync(Long reportId) {
        return CompletableFuture.runAsync(() -> {
            long totalStartTime = System.currentTimeMillis();

            try {
                CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    long count = userRepository.count();
                    long elapsed = System.currentTimeMillis() - start;
                    System.out.println("Подсчёт пользователей занял: " + elapsed + " ms");
                    return count;
                });

                CompletableFuture<List<User>> usersListFuture = CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    List<User> users = userRepository.findAll();
                    long elapsed = System.currentTimeMillis() - start;
                    System.out.println("Получение списка пользователей заняло: " + elapsed + " ms");
                    return users;
                });

                Long userCount = userCountFuture.join();
                List<User> usersList = usersListFuture.join();

                String htmlContent = generateHtmlReport(userCount, usersList);

                Report report = reportRepository.findById(reportId).orElse(null);
                if (report != null) {
                    report.setContent(htmlContent);
                    report.setStatus(ReportStatus.COMPLETED);
                    reportRepository.save(report);
                }

                long totalElapsed = System.currentTimeMillis() - totalStartTime;
                System.out.println("Общее время формирования отчёта: " + totalElapsed + " ms");

            } catch (Exception e) {
                e.printStackTrace();
                Report report = reportRepository.findById(reportId).orElse(null);
                if (report != null) {
                    report.setStatus(ReportStatus.ERROR);
                    report.setContent("Ошибка формирования отчёта: " + e.getMessage());
                    reportRepository.save(report);
                }
            }
        });
    }

    private String generateHtmlReport(long userCount, List<User> usersList) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Статистика приложения</title>\n");
        html.append("    <style>\n");
        html.append("        table { border-collapse: collapse; width: 100%; }\n");
        html.append("        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
        html.append("        th { background-color: #4CAF50; color: white; }\n");
        html.append("        tr:nth-child(even) { background-color: #f2f2f2; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Статистика приложения</h1>\n");
        html.append("    <h2>Количество зарегистрированных пользователей: ").append(userCount).append("</h2>\n");
        html.append("    <h2>Список пользователей:</h2>\n");
        html.append("    <table>\n");
        html.append("        <tr>\n");
        html.append("            <th>ID</th>\n");
        html.append("            <th>Имя пользователя</th>\n");
        html.append("            <th>Роль</th>\n");
        html.append("        </tr>\n");

        for (User user : usersList) {
            html.append("        <tr>\n");
            html.append("            <td>").append(user.getId()).append("</td>\n");
            html.append("            <td>").append(user.getUsername()).append("</td>\n");
            html.append("            <td>").append(user.getRole()).append("</td>\n");
            html.append("        </tr>\n");
        }

        html.append("    </table>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }
}
