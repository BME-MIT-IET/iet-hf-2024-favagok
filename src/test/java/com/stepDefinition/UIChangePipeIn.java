package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.PlumberButtonPanel;
import program.main.PlumberPipeInWindow;
import program.main.State;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static program.main.Main.game;

public class UIChangePipeIn {
    private PlumberButtonPanel pbPanel = new PlumberButtonPanel();
    private PlumberPipeInWindow ppWindow = new PlumberPipeInWindow(3);
    @When("click on change pipe in")
    public void click_on_change_pipe_in() {
        System.out.println("test started");
        ActionEvent event = new ActionEvent(pbPanel.getChangePipeIn(), ActionEvent.ACTION_PERFORMED, "change pipe in");
        pbPanel.actionPerformed(event);
    }
    @When("select pipe in")
    public void select_pipe_in() {
        System.out.println("test started");
        ppWindow.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pipe8"));
    }

    @Then("check if pipe in has changed")
    public void check_if_the_pipe_in_has_changed() {
        System.out.println("test started");
        assertEquals(game.Fields().get(0).getPipeIn(), game.Pipes().get(7));
    }



}
