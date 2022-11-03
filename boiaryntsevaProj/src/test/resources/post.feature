@PostTest @FullRegression
Feature: Post Feature

  Background:
    Given User opens 'Home' page


  @R011
  @BeforeDeletingAllPostsForDefaultUser
  @AfterDeletingAllPostsForDefaultUser

  Scenario: R011 Check number of posts
    Given create 2 new posts via API for 'default' user and 'default' password
    When User click on 'MyProfile' button on 'Home' page
    Then User is redirected to 'Profile' page
    And User sees 2 posts in Posts list on 'Profile' page



