package org.tictactoe.controller;

import org.tictactoe.exception.InvalidGameParamException;
import org.tictactoe.model.Game;
import org.tictactoe.model.GameStatus;
import org.tictactoe.model.Move;
import org.tictactoe.model.Player;
import org.tictactoe.strategies.WinningStrategy;

import java.util.Collections;
import java.util.List;

public class GameController {
    public Game createGame(
            List<Player> players,
            int boardSize,
            List<WinningStrategy> winningStrategies
    ) throws InvalidGameParamException {
        return Game.getBuilder()
                .setDimension(3)
                .setPlayerList(players)
                .setWinningStrategy(winningStrategies).build();
    }
    
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void printBoard(Game game) {
        game.getBoard().printBoard();
    }

    public void undo(Game game) {
        game.undo();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void printResult(Game game) {
        game.printResult();
    }
}
