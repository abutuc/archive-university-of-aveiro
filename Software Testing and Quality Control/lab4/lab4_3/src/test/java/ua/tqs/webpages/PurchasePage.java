package ua.tqs.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class PurchasePage {

    private WebDriver driver;

    // Page URL

    @FindBy(tagName = "h2")
    WebElement heading;

    @FindBy(css = "p:nth-child(2)")
    WebElement airline;

    @FindBy(css = "p:nth-child(3)")
    WebElement flight_number;

    @FindBy(css = "p:nth-child(4)")
    WebElement price;


    @FindBy(id = "inputName")
    WebElement buyer_name;

    @FindBy(id = "address")
    WebElement buyer_address;

    @FindBy(id = "city")
    WebElement buyer_city;

    @FindBy(id = "state")
    WebElement buyer_state;

    @FindBy(id = "zipCode")
    WebElement buyer_zipCode;

    @FindBy(id = "cardType")
    WebElement buyer_cardType;

    @FindBy(id = "creditCardNumber")
    WebElement buyer_creditCardNumber;

    @FindBy(id = "creditCardMonth")
    WebElement buyer_creditCardMonth;

    @FindBy(id = "creditCardYear")
    WebElement buyer_creditCardYear;

    @FindBy(id = "nameOnCard")
    WebElement buyer_nameOnCard;

    @FindBy(css = ".btn-primary")
    WebElement purchaseFlightButton;


    public PurchasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void setBuyer_name(String name){
        buyer_name.clear();
        buyer_name.sendKeys(name);
    }

    public void setBuyer_address(String address){
        buyer_address.clear();
        buyer_address.sendKeys(address);
    }

    public void setBuyer_city(String city){
        buyer_city.clear();
        buyer_city.sendKeys(city);
    }

    public void setBuyer_state(String state){
        buyer_state.clear();
        buyer_state.sendKeys(state);
    }

    public void setBuyer_zipCode(String zipCode){
        buyer_zipCode.clear();
        buyer_zipCode.sendKeys(zipCode);
    }

    public void setBuyer_cardType(String option){
        buyer_cardType.findElement(By.xpath("//option[. = '" + option + "']")).click();
    }

    public void setBuyer_creditCardNumber(String creditCardNumber){
        buyer_creditCardNumber.clear();
        buyer_creditCardNumber.sendKeys(creditCardNumber);
    }

    public void setBuyer_creditCardMonth(String month){
        buyer_creditCardMonth.clear();
        buyer_creditCardMonth.sendKeys(month);
    }

    public void setBuyer_creditCardYear(String year){
        buyer_creditCardYear.clear();
        buyer_creditCardYear.sendKeys(year);
    }

    public void setBuyer_nameOnCard(String name){
        buyer_nameOnCard.clear();
        buyer_nameOnCard.sendKeys(name);
    }

    public void clickOnPurchaseFlightButton(){
        purchaseFlightButton.click();
    }

    // Assertions
    public boolean isCorrectPageOpened(String heading){
        return this.heading.getText().equals(heading);
    }

    public boolean isAirlineCorrect(String airline){
        return this.airline.getText().equals(airline);
    }

    public boolean isFlightNumberCorrect(String flight_number){
        return this.flight_number.getText().equals(flight_number);
    }

    public boolean isPriceCorrect(String price){
        return this.price.getText().equals(price);
    }

}
