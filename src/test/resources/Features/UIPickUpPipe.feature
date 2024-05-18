Feature: Test is plumber can pick up an existing pipe.

  Scenario: Plumber stands on a pump and picks up an incoming pipe.

    Given game window starts
    When click on start
    And game starts
    And click on pick up pipe
    And select a pipe
    And check if plumber has pipe in pocket
    Then check if the pipe is no more connected to the previous location