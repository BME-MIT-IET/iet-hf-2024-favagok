Feature: Tests a plumber repairing a pipe.

  Scenario: A plumber moves from the starting pump to a neighboring one, and destroys the pipe in-between, then an other plumber moves and repairs that pipe.

    Given game window starts
    When click on start
    And game starts
    And click on move button
    And select destination
    And click on destroy pipe
    And click on end turn
    And test if pipe is broken
    And click on move button
    And select destination
    And click on repair pipe
    And click on end turn
    Then test if pipe is fixed
