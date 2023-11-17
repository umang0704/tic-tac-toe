package org.tictactoe.model;

import lombok.Getter;
import lombok.Setter;
import org.tictactoe.design.factory.BotPlayingStrategyFactory;
import org.tictactoe.design.strategies.botplayingstrategy.BotPlayingStrategy;
@Getter
@Setter
public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private final BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, String name, BotDifficultyLevel botDifficultyLevel) {
        super(name,symbol,PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Cell makeMoveAndGetCell(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}
