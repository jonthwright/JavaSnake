# JavaSnake
Snake game in Java. Written using OpenJDK 16.

Uses Swing to build the Graphical User Interface (GUI) of the game.

## Objective

The objective of the game is to control the head of the snake (green body with yellow head) to eat as many fruits as possible (red).

The game will continue as long as the snake doesn't encounters two situations: 
1. it does not collide with itself, i.e., it's own body; or 
2. it does not collide with the wall, i.e., the edge of the game screen.

The goal is to last as long as possible and get the highest score possible (the number of fruits eaten).

The snake will elongate each time a fruit is eaten. 

## How to play

The following controls that are required to play the game includes:

* Spacebar : Start game during main menu
* W-key/Up arrow key: Change snake head direction to go up (only when snake head direction is pointing left or right)
* S-key/Down arrow key: Change snake head direction to go down (only when snake head direction is pointing left or right)
* A-key/Left arrow key: Change snake head direction to go left (only when snake head direction is pointing up or down)
* D-key/Right arrow key: Change snake head direction to go right (only when snake head direction is pointing up or down)

## TODO List

Things that need to be done, but not limited to:

* Fix controls to prevent quick succession of inputs when controlling snake
* Possibly include a feature that makes the snake's movement faster after a certain amount of fruit is eaten
* Testing and checking for potential bugs

_Some points are subject to change._