Feature: Adding the user to list

Scenario Outline:  Add User
Given User is on reqres URL
When User enters the "<name>" , and "<job>"
And Users hits the users API
Then Users are added to list


Examples: 
|name|job|
|Sathya|Sr.Engg|
|Narayana|Engg|

Scenario: Update User
Given User is on reqres URL
When User enters name & job
|Harsha|ML|
|Subbu|Data Engg|
And User hits the API
Then User data is updated
