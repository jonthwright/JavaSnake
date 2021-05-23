# JavaSnake
Snake game in Java. Written using OpenJDK 16.

Uses Swing to build the Graphical User Interface (GUI) of the game.

## Objective

The objective of the game is to control the head of the snake (green body with yellow head) to eat as many fruits as possible (red).

The game will continue as long as the snake doesn't encounters two situations: 
1. it does not collide with itself, i.e., it's own body; or 
2. it does not collide with the wall, i.e., the edge of the game screen.

The goal is to last as long as possible and get the highest score possible (the number of fruits eaten).

The snake will elongate each time a fruit is eaten and will move faster after a certain amount of fruit is eaten.

## How to play

The following controls that are required to play the game includes:

* Spacebar or Arrow keys or WASD keys: Start a new game (only when on the main menu)
* W-key/Up arrow key: Change snake head direction to go up (only when snake head direction is pointing left or right)
* S-key/Down arrow key: Change snake head direction to go down (only when snake head direction is pointing left or right)
* A-key/Left arrow key: Change snake head direction to go left (only when snake head direction is pointing up or down)
* D-key/Right arrow key: Change snake head direction to go right (only when snake head direction is pointing up or down)
* Spacebar or P-key: Pause or unpause the game (only when playing a game)
* M-key: Return to main menu (only when on game over screen)
* R-key: Restart a new game (only when on game over screen)

## TODO List

Things that need to be done, but not limited to:

* ~~Add various game logic and mechanics~~
* ~~Add Graphical User Interface of the game~~
* ~~Fix controls to prevent quick succession of inputs when controlling snake~~
* ~~Possibly include a feature that makes the snake's movement faster after a certain amount of fruit is eaten~~
* ~~Possibly add a restart game functionality~~
* ~~Add pause functionality~~
* Major code clean-up: look for ways how to make the source code to be a lot cleaner than it's current state, specifically the Game.java and GameGraphics.java files
* Testing and checking for potential bugs
