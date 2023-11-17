package org.tictactoe.model;

import lombok.Data;

@Data
public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellStatus cellStatus;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellStatus = CellStatus.EMPTY;
    }

    public void display() {
        if(this.cellStatus.equals(CellStatus.EMPTY)){
            System.out.printf(" - |");
        }else{
            System.out.printf(" "+this.player.getSymbol()+" |");
        }
    }
}
