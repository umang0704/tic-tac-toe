package org.tictactoe.design.strategies.winningstrategies;

import org.tictactoe.model.Board;
import org.tictactoe.model.Move;
import org.tictactoe.model.Player;
import org.tictactoe.model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderRowColumnWinningStrategy implements WinningStrategy {
    private final List<HashMap<Symbol,Integer>> rowMap;

    public OrderRowColumnWinningStrategy(int size, List<Player> players) {
        this.rowMap = new ArrayList<>();
        for(int i = 0 ; i < size ; i++){
            rowMap.add(new HashMap<>());
            for(Player player : players){
                rowMap.get(i).put(player.getSymbol(),0);
            }
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int cellRow = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        HashMap<Symbol, Integer> moveRow = rowMap.get(cellRow);
        moveRow.put(symbol,moveRow.get(symbol)+1);
        return rowMap.get(cellRow).get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int cellRow = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        HashMap<Symbol, Integer> moveRow = rowMap.get(cellRow);
        moveRow.put(symbol,moveRow.get(symbol)-1);
    }
}
