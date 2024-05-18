package com.stepDefinition;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.*;
import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;
import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;
import static org.junit.Assert.assertEquals;

public class MoveSteps {
    @Given("plumber is on starting point")
    public void plumber_is_on_starting_point() {
        System.out.println("test started");
            Player.setGame(game);

    }

    @When("it is his turn he moves")
    public void it_is_his_turn_he_moves() {
        System.out.println("test started");
        // Write code here that turns the phrase above into concrete actions
        game.Plumbers().get(0).move(Main.game.Fields().get(4));
    }

    @Then("he lands on the other pump")
    public void he_lands_on_the_other_pump() {
        System.out.println("test started");
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Main.game.Fields().get(4), game.Plumbers().get(0).getPosition());
    }

}
