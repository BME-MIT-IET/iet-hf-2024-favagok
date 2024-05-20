Feature: plumber picks up a pump

  Scenario: test if he really has a pump in his pocket

    Given game window starts
    When click on start
    And game starts
    And click on pick up pump
    Then test if he really has a pump