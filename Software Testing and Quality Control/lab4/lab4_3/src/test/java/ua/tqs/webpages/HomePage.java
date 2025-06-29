package ua.tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class HomePage {
    private WebDriver driver;

    // Page URL
    private static String PAGE_URL="https://blazedemo.com/";

    // Locators

    @FindBy(tagName = "h1")
    WebElement heading;

    @FindBy(name = "fromPort")
    private WebElement fromPortDropdown;

    @FindBy(name = "toPort")
    private WebElement toPortDropdown;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightButton;

    // Constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        // Initialise Elements
        PageFactory.initElements(this.driver, this);
    }

    public void selectFromPortDropdown(String option){
        fromPortDropdown.findElement(By.xpath("//option[. = '" + option + "']")).click();

    }

    public void selectToPortDropdown(String option){
        toPortDropdown.findElement(By.xpath("//option[. = '" + option + "']")).click();
    }

    public void clickOnFindFlightsButton(){
        findFlightButton.click();
    }

    // Assertions
    public boolean isPageOpened(){
        return heading.getText().equals("Welcome to the Simple Travel Agency!");
    }

}
