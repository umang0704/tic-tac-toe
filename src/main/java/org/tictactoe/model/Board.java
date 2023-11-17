package org.tictactoe.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Board {
    private int size;
    private List<List<Cell>> grid;

    public Board(int size) {
        this.size = size;
        this.grid = new ArrayList<>();
        for(int i = 0 ; i < size ; i++){
            grid.add(new ArrayList<>());
            for(int j = 0 ; j < size ; j++){
                grid.get(i).add(new Cell(i,j));
            }
        }
    }

    public void printBoard() {
        for(List<Cell> row : grid){
            System.out.printf("|");
            for(Cell cell : row ) cell.display();
            System.out.println();
        }
    }
}
