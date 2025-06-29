package ua.tqs.calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.*;

public class CalculatorSteps {
    private Calculator calculator;

    @Given("a calculator I just turned on")
    public void turn_on_calculator(){
        this.calculator = new Calculator();
    }


    @When("I add {int} and {int}")
    public void I_add_x_and_y(int x, int y){
        calculator.push(x);
        calculator.push(y);
        calculator.push("+");
    }

    @Then("the result is {int}")
    public void the_result_is(double x){
        assertThat(calculator.value()).isEqualTo(x);
    }

    @When("I subtract {int} to {int}")
    public void I_subtract_x_to_y(int x, int y){
        calculator.push(x);
        calculator.push(y);
        calculator.push("-");
    }

}
