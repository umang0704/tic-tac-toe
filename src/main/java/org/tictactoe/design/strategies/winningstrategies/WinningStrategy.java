package org.tictactoe.design.strategies.winningstrategies;

import org.tictactoe.model.Board;
import org.tictactoe.model.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);
}
