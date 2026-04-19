package ru.max.SchoolDairy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LoginUITest {

    @LocalServerPort
    private int port;
    private WebDriver driver;
    private String baseUrl;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    void setUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new EdgeDriver(options);
        baseUrl = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testSuccessfulLogin() throws InterruptedException {
        driver.get(baseUrl + "/login");
        Thread.sleep(1000);

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));

        usernameField.sendKeys("md");
        passwordField.sendKeys("123");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/student"));
    }

    @Test
    void testLogout() throws InterruptedException {
        driver.get(baseUrl + "/login");
        Thread.sleep(1000);

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));

        usernameField.sendKeys("md");
        passwordField.sendKeys("123");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        Thread.sleep(2000);

        assertTrue(driver.getCurrentUrl().contains("/student"));

        driver.get(baseUrl + "/logout");

        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/login"));
    }
}