package org.tictactoe.model;

import org.tictactoe.exception.InvalidGameParamException;
import org.tictactoe.strategies.WinningStrategy;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> playerList;
    private List<Move> moveList;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;
    private Player currentPlayer;

    public Game(List<Player> playerList, List<WinningStrategy> winningStrategies, int dimensions) {
        this.playerList = playerList;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimensions);
        gameStatus = GameStatus.IN_PROGRESS;
    }

    public static class GameBuilder{
        private List<Player> playerList;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        public GameBuilder setPlayerList(List<Player> playerList){
            this.playerList = playerList;
            return  this;
        }
        public GameBuilder setWinningStrategy(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return  this;
        }

        public GameBuilder setDimension(int dimension){
            this.dimension = dimension;
            return  this;
        }

        private Game build(){
            if(!valid()) throw new InvalidGameParamException("Input Parameters are invalid");
            return new Game();
        }

        private boolean valid() {
            return false;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
