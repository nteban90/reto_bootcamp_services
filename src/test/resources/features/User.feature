Feature: Create, update and delete user

  @PostUser
  Scenario: Create user successfully.
    When I consume the endpoint and I send the user information email, username, password, firstname, lastname "
    Then I can validate the response service


  @PutUser
  Scenario: Update user successfully.
    When I consume the endpoint and I send the user information for update email,username,password,firstname,lastname"
    Then I can validate the response server


  @DeleteUser
  Scenario: Delete user successfully.
    When I consume the endpoint  and I send the user information for delete user
    Then I can validate the response for the phone