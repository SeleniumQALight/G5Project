  @POstTest @FullRegression
  Feature: Post Feature

    Background:
      Given User opens 'Home' page

    @R011
      @BeforeDeletingAllPostsForDefaultUser
      @AfterDeletingAllPostsForDefaultUse
    Scenario: R011 Check number of posts
      Given Crreate 2 new posts via API for 'default' user and 'default' password
      When User click on 'MyProfile' button on 'Login' page
      Then User is redirect to 'Profile' page
      And User sees 2 posts in Posts on 'Profile' page