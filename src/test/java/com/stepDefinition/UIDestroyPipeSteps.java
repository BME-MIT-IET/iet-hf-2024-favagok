package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.PlumberButtonPanel;
import program.main.PlumberPipeInteraction;
import program.main.State;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static program.main.Main.game;

public class UIDestroyPipeSteps {


    private PlumberPipeInteraction ppInteraction = new PlumberPipeInteraction();

    @When("click on destroy pipe")
    public void click_on_destroy_pipe() {
        System.out.println("destroying pipe");
        ActionEvent event = new ActionEvent(ppInteraction.getDestroy(), ActionEvent.ACTION_PERFORMED, "destroy");
        ppInteraction.actionPerformed(event);
    }
    @Then("test if pipe is broken")
    public void test_if_pipe_is_broken() {
        System.out.println("test started");
        assertEquals(game.Pipes().get(3).getState(), State.Broken);
    }
}
