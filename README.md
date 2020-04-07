Made by Aldrick Johan (aj2nud) and Wei Wang (ww2ta)

Project Summary

    This project is a game of TicTacToe that is played on a 5x5 grid where 
    each player needs to get 5 in a row to win. It uses a GUI to display the game.

Packages and classes

GAMES (package)

    GameInterface - An interface outlining what methods
    need to be included in an implementation of a GameInterface.
    This includes createPlayer, getCurrentPlayer, getWinner, getCurrentSymbol,
    makeTerm, isGameOver, clearBoard, and startGame.

    ComputerGameInterface - A class that implements GameInterface and
    defines the parts of the game when 1 player mode is slected (playing against a 
    computer). This class includes the following methods: createPlayer,
    clearBoard, getCurrentPlayer, isMoveLegal, makeTerm, getMoveNumber, getCurrentSymbol,
    isGameOver, gameStarted, checkHorizontal, checkDiagonal, checkVertical, changeTeam, and startGame.
    
    HumanGameInterface - A class that implements GameInterface and 
    defines the parts of the game when 2 player mode is slected (playing against a 
    another person). This class includes the following methods: createPlayer,
    clearBoard, getCurrentPlayer, isMoveLegal, makeTerm, getMoveNumber, getCurrentSymbol,
    isGameOver, gameStarted, checkHorizontal, checkDiagonal, checkVertical, changeTeam, and startGame.
    
    Player - A class that defines the Player object. Consists of the following fields:
        String name
        String symbol
    
GUI (package)
    
    GuiController - Controls the GUI and starts the game in the menu.
    Includes the begin and start methods.
    
    Menu - Sets up the menu and manages all operations related to the menu.
    Includes the following methods: Menu(constructor) which sets up the
    scene, addModes, getters, reset, and modeSelectionHandler.
    
    GameBoardPane - This sets up and manages the GUI for the game.
    It is made of a grid of 25 buttons for the gameboard as well as a quit
    button that goes back to the meny and a restart button that resets
    the game. It has the following methods: setUpPane, setLabel, setWidgetPosition,
    getRandomButton, reset, TableButtonHandler, QuitButtonHandler, and RestartButtonHandler.

beginGame

    Begins the game by calling GuiController.start()
    
How to run

    This program can be run by loading up this project into an IDE
    and then running the beginGame class.
