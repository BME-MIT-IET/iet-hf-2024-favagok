Feature: Player moves from pump to pump.

  Scenario: Test if player's position changed.

    Given plumber is on starting point
    When it is his turn he moves
    Then he lands on the other pump