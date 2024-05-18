Feature: Player moves from pump to pump.

  Scenario: Test if player's position changed.

    Given game window starts
    When click on start
    And game starts
    And click on move button
    And select destination
    And click on end turn
    Then check new position