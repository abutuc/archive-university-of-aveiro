package ua.tqs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;


import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;



class HelloWorldChromeJupiterTest {
    static final Logger log = getLogger(lookup().lookupClass());
    private WebDriver driver;

    @BeforeAll
    static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    void test(){
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }
}