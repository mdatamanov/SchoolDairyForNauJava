package ru.max.SchoolDairy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.max.SchoolDairy.model.Report;
import ru.max.SchoolDairy.service.ReportService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> createReport() {
        Long reportId = reportService.createReport();

        reportService.generateReportAsync(reportId);

        Map<String, Long> response = new HashMap<>();
        response.put("reportId", reportId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReport(@PathVariable Long id) {
        Report report = reportService.getReport(id);

        if (report == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", report.getId());
        response.put("status", report.getStatus().toString());

        switch (report.getStatus()) {
            case COMPLETED:
                response.put("content", report.getContent());
                response.put("message", "Отчёт успешно сформирован");
                break;
            case CREATED:
                response.put("message", "Отчёт ещё формируется, попробуйте позже");
                break;
            case ERROR:
                response.put("message", report.getContent()); 
                break;
        }

        return ResponseEntity.ok(response);
    }
}
