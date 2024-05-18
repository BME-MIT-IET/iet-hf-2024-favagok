Feature: Tests a plumber changing the output of a pipe

  Scenario: A plumber moves from the starting pump to a neighboring one, and changes the pipe's output in-between

    Given game window starts
    When click on start
    And game starts
    And click on move button
    And select destination

    And click on end turn
    Then check if pipe out has changed