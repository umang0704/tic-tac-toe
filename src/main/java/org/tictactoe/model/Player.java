package org.tictactoe.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Data
public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    private Scanner scanner;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        scanner = new Scanner(System.in);
    }

    public Cell makeMoveAndGetCell(Board board) {
        System.out.println("Please enter a row no(starting from 0):");
        int row = scanner.nextInt();
        System.out.println("Please enter a col no(starting from 0):");
        int col = scanner.nextInt();
        return new Cell(row,col);
    }
}
