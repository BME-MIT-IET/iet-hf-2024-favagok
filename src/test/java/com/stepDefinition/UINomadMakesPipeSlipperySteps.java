package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.NomadMoveWindow;
import program.main.NomadPipeInteraction;
import program.main.PlumberMoveWindow;
import program.main.PlumberPipeInteraction;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertTrue;
import static program.main.Main.game;

public class UINomadMakesPipeSlipperySteps {
    private NomadPipeInteraction npInteraction = new NomadPipeInteraction();
    private NomadMoveWindow nmWindow = new NomadMoveWindow(3);

    @When("select destination nomad")
    public void select_destination_nomad() {
        System.out.println("test started");
        nmWindow.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"pump4"));
    }
    @When("click on make slippery")
    public void click_on_make_slippery() {
        System.out.println("test started");
        ActionEvent endButtonEvent = new ActionEvent(npInteraction.getSlippery(), ActionEvent.ACTION_PERFORMED, "slippery");
        npInteraction.actionPerformed(endButtonEvent);
    }
    @When("click on end turn nomad")
    public void click_on_end_turn_nomad() {
        System.out.println("test started");
        ActionEvent endButtonEvent = new ActionEvent(npInteraction.getEnd(), ActionEvent.ACTION_PERFORMED, "end");
        npInteraction.actionPerformed(endButtonEvent);
    }
    @Then("check if the pipe is slippery")
    public void check_if_the_pipe_is_slippery() {
        System.out.println("test started");
        assertTrue(game.Pipes().get(2).GetSlippery());
    }
}
