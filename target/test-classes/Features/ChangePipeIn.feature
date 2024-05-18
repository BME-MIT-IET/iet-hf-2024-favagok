Feature: Change the input pipe of a pump.

  Scenario: A plumber moves to a pump and in the second turn changes the input of that pump.

    Given initialize the game
    When every player moves
    And change the pipe in of the pump the plumber stands on
    Then check if pipe in changed