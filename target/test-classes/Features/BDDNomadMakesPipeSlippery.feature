Feature: Nomad makes pipe slippery.

  Scenario: First the plumbers move, then nomad stands on a pump then moves to the other pump and makes the pipe between them slippery.

    Given game window starts
    When click on start
    And game starts
    And click on move button
    And select destination
    And click on end turn
    And click on move button
    And select destination
    And click on end turn
    And click on move button
    And select destination
    And click on end turn
    And click on move button
    And select destination
    And click on end turn
    And click on move button
    And select destination nomad
    And click on make slippery
    And click on end turn nomad
    Then check if the pipe is slippery