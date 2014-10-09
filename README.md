Tic_Tac_Toe
===========

Tic_Tac_Toe 3 x 3 online game


Author: Yifan Gu

Design Document


Java files walkthrough:


User.java is an user object used to describe an user with username, activation information and so on.


Game.java is a game object used to describe a game with host user, guest user, game board and a series of variables to indicate the status of the game.


Move.java a move object used to describe a move made by one of users in the game. It has two method: sendMove() and getMove() . sendMove() is used when an user tries to make a move by setting the gameboard. getMove() is used to get update for the gameboard from server.


Invite.java has one method, invite() and it is used to remove both host user and guest user from available list and put them into a game and put the game into gamelist when a user invites another user to a game.


Accept.java has one method, accept() which set corresponding variables to put the guest user to go into a game when the guest user accepts the invitation.


Initialization.java is used to initialize all the variables we need which are availablelist, gamelist. availablelist stores all user objects which are active and ready to be in a game.


LoginController.java has one method login(), which is used to collect and validate the login information about the user.


GoBack.java has one method back() which handles the case where the user wants to go back to main screen when the game is over.


JSP files walkthrough


Login.jsp is used to collect user information when the user logs in.


MainScreen.jsp is used to display all available users for the user to invite and the invitation form other users.


Game.jsp is used to display the gameboard and let the user play.


AvailableUsers.jsp is used to get information about all available users and display them to the current user.


Invitations.jsp is used to get information about all invitations made by other users and display them to the current user.


Notes:


One user can only be invited by one user at a time.


The user would be treated as offline (or inactive) after 2 minute without responses. Responses mean if the user close the tab or browser but NOT means that the user did not make any click.


The invitation would be rejected if the invited user did not response within 10 seconds. This is the only option the user has to reject an invitation. Because it makes more sense and more polite.


After the game over, the user has an option to go back to mainscreen and only after the user goes back to main screen, the user will be treated as an available user (others can see him in available list).
