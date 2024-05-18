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

public class UIPickUpPumpSteps {
    private PlumberButtonPanel pbPanel = new PlumberButtonPanel();
    @When("click on pick up pump")
    public void click_on_pick_up_pump() {
        System.out.println("test started");
        ActionEvent e = new ActionEvent(pbPanel.getpickUpPump(), ActionEvent.ACTION_PERFORMED, "pickUpPump");
        pbPanel.actionPerformed(e);
    }
    @Then("test if he really has a pump")
    public void test_if_he_really_has_a_pump() {
        System.out.println("test started");
        assertTrue(Controller.getCurrentPlumber().getPumpInPocket() != null);

    }
}
