package com.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import program.main.Player;
import io.cucumber.java.en.When;
import program.main.*;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertTrue;
import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;
import static program.main.Main.game;
import static program.main.State.Working;
import static program.main.State.Broken;
import static org.junit.Assert.assertEquals;

public class UIPickUpPipeSteps {

    private PlumberButtonPanel pbPanel = new PlumberButtonPanel();
    private PlumberPickUpWindow ppuWindow = new PlumberPickUpWindow(3);
    @When("click on pick up pipe")
    public void click_on_pick_up_pipe() {
        System.out.println("test started");
        ActionEvent e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pickUpPipe");
        pbPanel.actionPerformed(e);
    }
    @When("select a pipe")
    public void select_a_pipe() {
        System.out.println("test started");
        ppuWindow.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pipe7"));
    }
    @When("check if plumber has pipe in pocket")
    public void check_if_plumber_has_pipe_in_pocket() {
        System.out.println("test started");
        assertTrue(game.Plumbers().get(0).getPipeInPocket() != null);
    }
    @Then("check if the pipe is no more connected to the previous location")
    public void check_if_the_pipe_is_no_more_connected_to_the_previous_location() {
        System.out.println("test started");
        assertTrue(!game.Plumbers().get(0).getPosition().getConnectedPipes().contains(game.Plumbers().get(0).getPipeInPocket()));
    }

}
