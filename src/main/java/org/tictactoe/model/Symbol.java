package org.tictactoe.model;

import lombok.Data;

@Data
public class Symbol {
    private Character aChar;

    public Symbol(Character aChar) {
        this.aChar = aChar;
    }

    @Override
    public String toString() {
        return String.valueOf(aChar);
    }
}
