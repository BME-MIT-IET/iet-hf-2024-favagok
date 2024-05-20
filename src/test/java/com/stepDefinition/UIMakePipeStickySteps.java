package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.PlumberPipeInteraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static program.main.Main.game;

import java.awt.event.ActionEvent;

public class UIMakePipeStickySteps {

    private PlumberPipeInteraction ppInteraction = new PlumberPipeInteraction();
    @When("click on make sticky")
    public void click_on_make_sticky() {
        System.out.println("test started");
        ActionEvent endButtonEvent = new ActionEvent(ppInteraction.getSticky(), ActionEvent.ACTION_PERFORMED, "sticky");
        ppInteraction.actionPerformed(endButtonEvent);
    }
    @Then("test if pipe is sticky")
    public void test_if_pipe_is_sticky() {
        System.out.println("test started");
        assertTrue(game.Pipes().get(3).GetSticky());
    }
}
