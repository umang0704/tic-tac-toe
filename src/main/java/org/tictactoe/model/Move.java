package org.tictactoe.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Move {
    private Player player;
    private Cell cell;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }
}
