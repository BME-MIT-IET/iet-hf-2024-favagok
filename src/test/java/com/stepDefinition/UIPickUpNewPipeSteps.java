package com.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import program.main.PlumberButtonPanel;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertTrue;
import static program.main.Main.game;

public class UIPickUpNewPipeSteps {
    private PlumberButtonPanel pbPanel = new PlumberButtonPanel();
    @When("click on pick new up pipe")
    public void click_on_pick_new_up_pipe() {
        System.out.println("test started");
        ActionEvent e = new ActionEvent(pbPanel.getpickupnewpipe(), ActionEvent.ACTION_PERFORMED, "pickupnewpipe");
        pbPanel.actionPerformed(e);
    }
}
