package com.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import program.main.Player;
import io.cucumber.java.en.When;
import program.main.*;

import java.awt.event.ActionEvent;

import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;
import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;
import static org.junit.Assert.assertEquals;

public class UIStartSteps {
    private startMenu startMenu;
    @Given("game window starts")
    public void game_window_starts() {
        System.out.println("test started");
        Player.setGame(game);
        startMenu = new startMenu();
    }
    @When("click on start")
    public void click_on_start() {
        System.out.println("test started");
        startMenu.getStartButton().getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Start"));
    }

    @Then("game starts")
    public void game_starts() {
        System.out.println("test started");
        Assert.assertTrue(startMenu.getstart());
    }
}
