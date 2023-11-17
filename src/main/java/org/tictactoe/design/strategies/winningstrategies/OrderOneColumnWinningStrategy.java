package org.tictactoe.design.strategies.winningstrategies;

import org.tictactoe.model.Board;
import org.tictactoe.model.Move;
import org.tictactoe.model.Player;
import org.tictactoe.model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneColumnWinningStrategy implements WinningStrategy {
    private final List<HashMap<Symbol,Integer>> columnMap;

    public OrderOneColumnWinningStrategy(int size,List<Player> players) {
        this.columnMap = new ArrayList<>();
        for(int i = 0 ; i < size ; i++){
            columnMap.add(new HashMap<>());
            for(Player player : players){
                columnMap.get(i).put(player.getSymbol(),0);
            }
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int cellCol = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        HashMap<Symbol, Integer> moveCol = columnMap.get(cellCol);
        moveCol.put(symbol,moveCol.get(symbol)+1);

        return columnMap.get(cellCol).get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int cellCol = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        HashMap<Symbol, Integer> moveCol = columnMap.get(cellCol);
        moveCol.put(symbol,moveCol.get(symbol)-1);
   }
}
