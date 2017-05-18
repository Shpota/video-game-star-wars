Star Wars
=========

Simple 2D video game written with Java

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
.\gradlew build
```

## How to run.
Unix:
```
./gradlew run
```
Windows:
```
.\gradlew run
```
Alternatively the game can be started from the `jar` distribution.
```
java -cp "distr/*" com.shpota.starwars.Controller
```
The game was tested only on Mac OS X. 
Other systems were not verified, but most likely the game will work 
on Linux and Windows as well.

## Gameplay

![alt text][gameplay]

[gameplay]: https://github.com/Shpota/video-game-star-wars/blob/master/samples/gameplay.png "Gameplay"

## What could be improved.
1. Thread safe. The access to the game state should be done in synchronized way since the view and the 
game logic access the game state from different threads.

2. The game model is not completely decoupled. For instance `Enemy` class 'knows' about dimensions of the 
screen. Ideally it has to be done in the same way like it is done for object moves (via strategies).

3. View implementation looks a little chaotic, it could be refactored into several classes. 

4. Game loop. The game uses simplest variant of game loop which is far from what is used in real systems. 
The model just updates its state every 20 milliseconds.

5. Test coverage.
