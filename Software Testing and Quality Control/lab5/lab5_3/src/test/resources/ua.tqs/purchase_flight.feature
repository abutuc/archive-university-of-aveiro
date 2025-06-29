Feature: Flight purchase
  To allow a client to search for flights from a given city to a destination city and buy the flight of choice.

  Scenario: Buy successfully a flight
    When I navigate to 'https://blazedemo.com'
    And select 'Boston' as the departure city
    And select 'London' as the destination city
    And click 'Find Flights'
    And click the number 2 Choose This Flight button
    And fill the name input with 'Tommy Shelby'
    And fill the address input with 'Peaky Blinder street'
    And fill the city input with 'Birmigham'
    And fill the zip code input with '1111-999'
    And choose 'Visa' as my card type
    And fill the credit card number input with 123456789
    And fill the month input with 11
    And fill the year input with 2017
    And fill the name on card input with 'Thomas Shelby'
    And click 'Purchase Flight'
    Then I should see the header "Thank you for your purchase today!"

