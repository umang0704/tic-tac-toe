package org.tictactoe.design.factory;

import org.tictactoe.design.strategies.botplayingstrategy.BotPlayingStrategy;
import org.tictactoe.design.strategies.botplayingstrategy.EasyBotPlayingStrategy;
import org.tictactoe.model.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        return new EasyBotPlayingStrategy();
        //        return switch (difficultyLevel) {
//            case EASY -> new EasyBotPlayingStrategy();
//            case MEDIUM -> new MediumBotPlayingStrategy();
//            case HARD -> new HardBotPlayingStrategy();
//        };
    }
}
