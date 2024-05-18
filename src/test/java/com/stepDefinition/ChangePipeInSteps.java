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
public class ChangePipeInSteps {
    @Given("initialize the game")
    public void initialize_the_game() {
        System.out.println("test started");
        Player.setGame(game);
    }
    @When("every player moves")
    public void every_player_moves() {
        System.out.println("test started");
        game.Plumbers().get(0).move(Main.game.Fields().get(4));
    }
    @When("change the pipe in of the pump the plumber stands on")
    public void change_the_pipe_in_of_the_pump_the_plumber_stands_on() {
        System.out.println("test started");
        game.Plumbers().get(0).getPosition().changePipeIn(Main.game.Pipes().get(6));
    }
    @Then("check if pipe in changed")
    public void check_if_pipe_in_changed() {
        System.out.println("test started");
        assertEquals(Main.game.Pipes().get(6), game.Plumbers().get(0).getPosition().getPipeIn());
    }
}
