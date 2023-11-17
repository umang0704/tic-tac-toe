package org.tictactoe.design.strategies.botplayingstrategy;

import org.tictactoe.model.Board;
import org.tictactoe.model.Cell;
import org.tictactoe.model.CellStatus;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        for (List<Cell> row: board.getGrid()) {
            for (Cell cell: row) {
                if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
                    return cell;
                }
            }
        }

        return null; // You should never come here
    }
}
