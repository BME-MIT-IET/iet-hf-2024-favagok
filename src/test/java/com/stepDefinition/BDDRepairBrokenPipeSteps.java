package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.PlumberPipeInteraction;
import program.main.State;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static program.main.Main.game;

public class BDDRepairBrokenPipeSteps {
    private PlumberPipeInteraction ppInteraction = new PlumberPipeInteraction();
    @When("click on repair pipe")
    public void click_on_repair_pipe() {
        System.out.println("test started");
        ActionEvent endButtonEvent = new ActionEvent(ppInteraction.getRepair(), ActionEvent.ACTION_PERFORMED, "repair");
        ppInteraction.actionPerformed(endButtonEvent);
    }
    @Then("test if pipe is fixed")
    public void test_if_pipe_is_fixed() {
        System.out.println("test started");
        assertEquals(game.Pipes().get(3).getState(), State.Working);
    }
}
