Feature: Tests a plumber changing the input of a pipe

  Scenario: A plumber moves from the starting pump to a neighboring one, and changes the pipe's input in-between

    Given game window starts
    When click on start
    And game starts
    And click on change pipe in
    And select pipe in
    And click on end turn
    Then check if pipe in has changed