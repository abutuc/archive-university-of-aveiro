package ua.tqs;

import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseFlightSteps {
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = WebDriverManager.firefoxdriver().create();
    }
    @When("I navigate to {string}")
    public void I_navigate_to(String url){
        driver.get(url);
    }

    @And("select {string} as the departure city")
    public void select_departure_city(String city){
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.name("fromPort"))
                .findElement(By.xpath("//option[. = '"+ city +"']")).click();

    }

    @And("select {string} as the destination city")
    public void select_destination_city(String city){
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.name("toPort"))
                .findElement(By.xpath("//option[. = '"+ city +"']")).click();
    }

    @And("click 'Find Flights'")
    public void click_find_flights_button(){
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("click the number {int} Choose This Flight button")
    public void click_choose_this_flight_button(int n){
        driver.findElement(By.cssSelector("tr:nth-child("+ (n-1) +") .btn")).click();
    }


    @And("fill the name input with {string}")
    public void fill_name_input(String name) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(name);
    }


    @And("fill the address input with {string}")
    public void fill_address_input(String address) {
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(address);
    }

    @And("fill the city input with {string}")
    public void fill_city_input(String city) {
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
    }

    @And("fill the zip code input with {string}")
    public void fill_zipcode_input(String zipcode) {
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys(zipcode);
    }

    @And("choose {string} as my card type")
    public void choose_card_type(String type) {
        driver.findElement(By.id("cardType")).click();
        driver.findElement(By.name("cardType")).findElement(By.xpath("//option[. = '"+type+"']")).click();
    }

    @And("fill the credit card number input with {int}")
    public void fill_credit_card_number_input(int number) {
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(Integer.toString(number));
    }

    @And("fill the month input with {int}")
    public void fill_month_input(int month) {
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys(Integer.toString(month));
    }

    @And("fill the year input with {int}")
    public void fill_year_input(int year) {
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys(Integer.toString(year));
    }

    @And("fill the name on card input with {string}")
    public void fill_name_on_card_input(String name) {
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(name);
    }

    @And("click 'Purchase Flight'")
    public void click_purchase_flight_button(){
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should see the header \"Thank you for your purchase today!\"")
    public void see_header(){
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    }
}
