Feature: Test if a player can make a pipe sticky.

  Scenario: Plumber stands on a pump then moves to the other pump and makes the pipe between them sticky.

    Given game window starts
    When click on start
    And game starts
    And click on move button
    And select destination
    And click on make sticky
    And click on end turn
    Then test if pipe is sticky