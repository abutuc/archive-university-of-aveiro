package ua.tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ConfirmationPage {
    private WebDriver driver;

    // Page URL

    @FindBy(tagName = "h1")
    WebElement heading;

    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    // Assertions
    public boolean isPageOpened(){
        return this.heading.getText().equals("Thank you for your purchase today!");
    }

    public boolean isTitle(String title){
        return this.driver.getTitle().equals(title);
    }



}
