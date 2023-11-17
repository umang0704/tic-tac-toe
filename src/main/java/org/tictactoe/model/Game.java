package org.tictactoe.model;

import lombok.Data;
import org.tictactoe.exception.InvalidGameParamException;
import org.tictactoe.design.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.tictactoe.model.PlayerType.BOT;

@Data
public class Game {
    private Board board;
    private List<Player> playerList;
    private List<Move> moveList;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;
    private int currentPlayerIndex;

    public Game(List<Player> playerList, List<WinningStrategy> winningStrategies, int dimensions) {
        this.playerList = playerList;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimensions);
        gameStatus = GameStatus.IN_PROGRESS;
        currentPlayerIndex = 0;
        moveList = new ArrayList<>();
    }

    public void undo() {
        if (this.moveList.isEmpty()) {
            System.out.println("This is first move of the game.");
            return;
        }
        Move lastMove = this.moveList.get(moveList.size() - 1);
        for (WinningStrategy winningStrategy : winningStrategies) {
            winningStrategy.handleUndo(this.board, lastMove);
        }
        lastMove.getCell().setPlayer(null);
        lastMove.getCell().setCellStatus(CellStatus.EMPTY);
        moveList.remove(lastMove);

        currentPlayerIndex -= 1;
        currentPlayerIndex += playerList.size();
        currentPlayerIndex %= playerList.size();

    }

    public void makeMove() {
        Player currentPlayer = playerList.get(currentPlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn.");
        Cell proposedCell = currentPlayer.makeMoveAndGetCell(board);
        System.out.println("Move made at row: " + proposedCell.getRow() + " col: " + proposedCell.getCol() + ".");
        if (!validateProposedCell(proposedCell)) {
            System.out.println("Invalid Move. Please Retry!");
            return;
        }
        Cell cellInBoard = board.getGrid().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(currentPlayer, cellInBoard);
        moveList.add(move);

        if (isGameWon(currentPlayer, move)) return;
        if (isGameDraw()) return;

        currentPlayerIndex += 1;
        currentPlayerIndex %= playerList.size();
    }

    private boolean isGameDraw() {
        if (moveList.size() == board.getSize() * board.getSize()) {
            gameStatus = GameStatus.TIED;
            return true;
        }
        return false;
    }

    private boolean isGameWon(Player currentPlayer, Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
    }

    private boolean validateProposedCell(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        if (row < 0 || col < 0 || row >= board.getSize() || col >= board.getSize()) return false;
        return board.getGrid().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);
    }

    public void printResult() {
        if (gameStatus.equals(GameStatus.ENDED)) {
            System.out.println("Game has Ended. Winner is " + winner.getName());
        } else if (gameStatus.equals(GameStatus.TIED)) {
            System.out.println("Game has Tied.");
        } else {
            System.out.println("Game is In Progress.");
        }
    }


    public static class GameBuilder {
        private List<Player> playerList;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        public GameBuilder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public GameBuilder setWinningStrategy(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws InvalidGameParamException {
            if (!valid()) throw new InvalidGameParamException("Input Parameters are invalid");
            return new Game(this.playerList, this.winningStrategies, dimension);
        }

        private boolean valid() {
            int botCount = 0;
            Set<Character> uniqueSymbols = new HashSet<>();
            for (Player player : playerList) {
                if (player.getPlayerType().equals(BOT)) botCount++;
                if (uniqueSymbols.contains(player.getSymbol().getAChar())) return false;
                else uniqueSymbols.add(player.getSymbol().getAChar());
            }
            if (playerList.size() < 2 || playerList.size() != dimension - 1 || botCount >= 2) return false;
            return true;
        }
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

}
