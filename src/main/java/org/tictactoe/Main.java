package org.tictactoe;

import org.tictactoe.controller.GameController;
import org.tictactoe.exception.InvalidGameParamException;
import org.tictactoe.model.*;
import org.tictactoe.strategies.OrderDiagonalColumnWinningStrategy;
import org.tictactoe.strategies.OrderOneColumnWinningStrategy;
import org.tictactoe.strategies.OrderRowColumnWinningStrategy;
import org.tictactoe.strategies.WinningStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidGameParamException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int size = 3;
        List<Player> players = Arrays.asList(
                new Player("A", new Symbol('0'), PlayerType.HUMAN),
                new Player("B", new Symbol('x'), PlayerType.HUMAN)
        );

        List<WinningStrategy> winningStrategies = Arrays.asList(
                new OrderDiagonalColumnWinningStrategy(players),
                new OrderOneColumnWinningStrategy(size, players),
                new OrderRowColumnWinningStrategy(size, players)
        );
        Game game;
        try {
            game = gameController.createGame(players, size, winningStrategies);
        } catch (InvalidGameParamException invalidGameParamException) {
            System.out.println("Invalid Game Params Found!");
            return;
        }

        System.out.println("Game is starting...");

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Current Board Status.");
            gameController.printBoard(game);

            System.out.println("Do you want to do undo. (y/n)");
            String undoResponse = scanner.next();

            if ("Y".equalsIgnoreCase(undoResponse)) {
                gameController.undo(game);
            } else {
                gameController.makeMove(game);
            }
        }

        gameController.printResult(game);
        gameController.printBoard(game);

    }
}