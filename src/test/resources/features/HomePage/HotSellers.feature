Feature: Search functionality

  @test
  Scenario: Add single product directly from Hot Sellers section
    Given I am on the LUMA homepage
    When I add Gray coloured Hero Hoodie in M size to cart
    Then I should see You added Hero Hoodie to your shopping cart. notification
    And I should see shopping cart counter to be 1
    And I should see Hero Hoodie in my shopping cart
    And I should see Proceed to checkout button
