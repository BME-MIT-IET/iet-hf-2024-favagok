package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.PlumberButtonPanel;
import program.main.PlumberPipeInWindow;
import program.main.PlumberPipeOutWindow;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;
import static program.main.Main.game;

public class UIChangePipeOut {

    private PlumberButtonPanel pbPanel = new PlumberButtonPanel();
    private PlumberPipeOutWindow ppWindow = new PlumberPipeOutWindow(3);
    @When("click on change pipe out")
    public void click_on_change_pipe_out() {
        System.out.println("test started");
        ActionEvent event = new ActionEvent(pbPanel.getChangePipeOut(), ActionEvent.ACTION_PERFORMED, "change pipe out");
        pbPanel.actionPerformed(event);
    }
    @When("select pipe out")
    public void select_pipe_out() {
        System.out.println("test started");
        ppWindow.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pipe1"));
    }

    @Then("check if pipe out has changed")
    public void check_if_the_pipe_out_has_changed() {
        System.out.println("test started");
        assertEquals(game.Fields().get(3).getPipeOut(), game.Pipes().get(0));
    }

}
