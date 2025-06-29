package ua.tqs.tests;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import ua.tqs.webpages.ConfirmationPage;
import ua.tqs.webpages.HomePage;
import ua.tqs.webpages.PurchasePage;
import ua.tqs.webpages.ReservePage;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumJupiter.class)
class BlazeDemoBostonLondonTest {

    @Test
    public void blazeDemoBostonLondonChrome(ChromeDriver driver){
        String from = "Boston";
        String to = "London";

        HomePage home = new HomePage(driver);

        assertTrue(home.isPageOpened());

        home.selectFromPortDropdown(from);
        home.selectToPortDropdown(to);

        home.clickOnFindFlightsButton();
        ReservePage reservePage = new ReservePage(driver);

        assertTrue(reservePage.isCorrectPageOpened(from, to));
        reservePage.clickOnFlightButton(0);

        PurchasePage purchasePage = new PurchasePage(driver);
        assertTrue(purchasePage.isCorrectPageOpened("Your flight from TLV to SFO has been reserved."));
        assertTrue(purchasePage.isAirlineCorrect("Airline: United"));
        assertTrue(purchasePage.isFlightNumberCorrect("Flight Number: UA954"));
        assertTrue(purchasePage.isPriceCorrect("Price: 400"));

        purchasePage.setBuyer_name("Tommy Shelby");
        purchasePage.setBuyer_address("Peaky Blinder Street");
        purchasePage.setBuyer_city("Birmingham");
        purchasePage.setBuyer_zipCode("1111-999");
        purchasePage.setBuyer_cardType("Visa");
        purchasePage.setBuyer_creditCardNumber("123456789");
        purchasePage.setBuyer_creditCardMonth("11");
        purchasePage.setBuyer_creditCardYear("2017");
        purchasePage.setBuyer_nameOnCard("Thomas Shelby");

        purchasePage.clickOnPurchaseFlightButton();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        assertTrue(confirmationPage.isPageOpened());
        assertTrue(confirmationPage.isTitle("BlazeDemo Confirmation"));

    }
    // must have Docker running for it to work
    @Test
    public void blazeDemoBostonLondonFireFox(@DockerBrowser(type = BrowserType.FIREFOX) WebDriver driver){
        String from = "Boston";
        String to = "London";

        HomePage home = new HomePage(driver);

        assertTrue(home.isPageOpened());

        home.selectFromPortDropdown(from);
        home.selectToPortDropdown(to);

        home.clickOnFindFlightsButton();
        ReservePage reservePage = new ReservePage(driver);

        assertTrue(reservePage.isCorrectPageOpened(from, to));
        reservePage.clickOnFlightButton(0);

        PurchasePage purchasePage = new PurchasePage(driver);
        assertTrue(purchasePage.isCorrectPageOpened("Your flight from TLV to SFO has been reserved."));
        assertTrue(purchasePage.isAirlineCorrect("Airline: United"));
        assertTrue(purchasePage.isFlightNumberCorrect("Flight Number: UA954"));
        assertTrue(purchasePage.isPriceCorrect("Price: 400"));

        purchasePage.setBuyer_name("Tommy Shelby");
        purchasePage.setBuyer_address("Peaky Blinder Street");
        purchasePage.setBuyer_city("Birmingham");
        purchasePage.setBuyer_zipCode("1111-999");
        purchasePage.setBuyer_cardType("Visa");
        purchasePage.setBuyer_creditCardNumber("123456789");
        purchasePage.setBuyer_creditCardMonth("11");
        purchasePage.setBuyer_creditCardYear("2017");
        purchasePage.setBuyer_nameOnCard("Thomas Shelby");

        purchasePage.clickOnPurchaseFlightButton();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        assertTrue(confirmationPage.isPageOpened());
        assertTrue(confirmationPage.isTitle("BlazeDemo Confirmation"));

    }
}