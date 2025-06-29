package ua.tqs.belly;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    private Belly belly;
    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        belly = new Belly();
        belly.eat(cukes);
    }

    @When("I wait {int} hour")
    public void I_wait_hour(int hours){
        belly.wait(hours);
    }

    @Then("my belly should growl")
    public void my_belly_should_growl(){
        belly.growl();
    }


}
