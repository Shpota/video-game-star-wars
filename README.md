Star Wars
=========

Simple 2D video game written with Java. The game is implemented using only
standart lib.

This was the technical task which was a part of interview process for one german company. 
Unfortunettly I din't get an offer from the company, but not because of the code quality but 
because I implemented something they didn't ask. 

I hope that this code will be useful for someone else, that is why it stays here.

## Environment Setup.
The application requires JDK 1.8 or above.

## How to build.
Perform the next command form the root folder of the project.

Unix:
```
./gradlew build
```
Windows:
```
.\gradlew.bat build
```

## How to run.
Unix:
```
./gradlew run
```
Windows:
```
.\gradlew.bat run
```
Alternatively the game can be started from the `jar` distribution.
```
java -cp "distr/*" com.shpota.starwars.Controller
```
The game was tested only on Mac OS X. 
Other systems were not verified, but most likely the game will work 
on Linux and Windows as well.

## What needs to be improved.
1. Thread safe. The access to the game state should be done in synchronized way since the view and the 
game logic access the game state from different threads.

2. The game model is not completely decoupled. For instance `Enemy` class 'knows' about dimensions of the 
screen. Ideally it has to be done in the same way like it is done for object moves (via strategies).

3. View implementation looks a little chaotic, it could be refactored into several classes. 

4. Game loop. The game uses simplest variant of game loop which is far from what is used in real systems. 
The model just updates its state every 20 milliseconds.

5. Test coverage.

6. Chnage appernce of enemies after loading the game - the screen should be empty after startup.

## Gameplay

![alt text][gameplay]

[gameplay]: https://github.com/Shpota/video-game-star-wars/blob/master/samples/gameplay.png "Gameplay"


Icons of enemy and player were taken from [opengameart.org](https://opengameart.org/). All rights reserved by their respective owners.
