@users
Feature: Adding the users to the list.
Background: 
Given user is on reqres url
@add
Scenario Outline: Add user
When user enters the "<name>" and "<job>"
And user hit the users API
Then users are added to list
 
Examples:
|name|job|
|praveen|chef|
|Sathya|Analyst|
@update
Scenario: Updating user details
When enters name and job
|Subbu|Doctor|
|Harsha|Engg|
And user hits the API
Then user data is updated

