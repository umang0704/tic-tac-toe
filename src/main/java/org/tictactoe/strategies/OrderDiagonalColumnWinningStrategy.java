package org.tictactoe.strategies;

import org.tictactoe.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDiagonalColumnWinningStrategy implements WinningStrategy {
    private final Map<Symbol, Integer> leftDiagonalSymbolCount;
    private final Map<Symbol, Integer> rightDiagonalSymbolCount;

    public OrderDiagonalColumnWinningStrategy(List<Player> players) {
        this.leftDiagonalSymbolCount = new HashMap<>();
        this.rightDiagonalSymbolCount = new HashMap<>();

        for (Player player : players) {
            leftDiagonalSymbolCount.put(player.getSymbol(), 0);
            rightDiagonalSymbolCount.put(player.getSymbol(), 0);
        }

    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if (row == col) {
            leftDiagonalSymbolCount.put(symbol, leftDiagonalSymbolCount.get(symbol) + 1);
        }
        if (row + col == board.getSize() - 1) {
            rightDiagonalSymbolCount.put(symbol, rightDiagonalSymbolCount.get(symbol) + 1);
        }
        if (leftDiagonalSymbolCount.get(symbol).equals(board.getSize()) || rightDiagonalSymbolCount.get(symbol).equals(board.getSize()))
            return true;
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if (row == col) {
            leftDiagonalSymbolCount.put(symbol, leftDiagonalSymbolCount.get(symbol) - 1);
        }
        if (row + col == board.getSize() - 1) {
            rightDiagonalSymbolCount.put(symbol, rightDiagonalSymbolCount.get(symbol) - 1);
        }
    }
}
