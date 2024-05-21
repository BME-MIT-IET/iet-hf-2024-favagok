package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.*;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertTrue;
import static program.main.Main.game;
import static org.junit.Assert.assertEquals;

public class BDDPlumberMovesSteps {
    private PlumberButtonPanel pbPanel = new PlumberButtonPanel();
    private PlumberMoveWindow pmWindow = new PlumberMoveWindow(3);

    private PlumberPipeInteraction ppInteraction = new PlumberPipeInteraction();
    @When("click on move button")
    public void click_on_move_button() {
        System.out.println("test started");
        ActionEvent e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "move");
        pbPanel.actionPerformed(e);
    }
    @When("select destination")
    public void select_destination() {
        System.out.println("test started");
        pmWindow.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"pump2"));
    }
    @When("click on end turn")
    public void click_on_end_turn() {
        System.out.println("test started");
        ActionEvent endButtonEvent = new ActionEvent(ppInteraction.getEnd(), ActionEvent.ACTION_PERFORMED, "end");
        ppInteraction.actionPerformed(endButtonEvent);
    }
    @Then("check new position")
    public void check_new_position() {
        System.out.println("test started");
        assertEquals(game.Plumbers().get(0).getPosition(), game.Fields().get(3));
    }
}
