Feature: Test is plumber can pick up a new pipe.

  Scenario: Plumber stands on a pump and picks up a new pipe.

    Given game window starts
    When click on start
    And game starts
    And click on pick new up pipe
    Then check if plumber has pipe in pocket