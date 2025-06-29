package ua.tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ReservePage {
    private WebDriver driver;

    // Page URL

    @FindBy(tagName = "h3")
    WebElement heading;

    @FindBy(css = "tr .btn")
    List<WebElement> flightButtons;


    // Constructor
    public ReservePage(WebDriver driver){
        this.driver = driver;
        // Initialise Elements
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnFlightButton(int num){
        flightButtons.get(num).click();

    }

    // Assertions
    public boolean isCorrectPageOpened(String from, String to){
        return heading.getText().contains("Flights from " + from + " to " + to + ":");
    }


}
