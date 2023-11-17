package org.tictactoe.design.strategies.botplayingstrategy;

import org.tictactoe.model.Board;
import org.tictactoe.model.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
