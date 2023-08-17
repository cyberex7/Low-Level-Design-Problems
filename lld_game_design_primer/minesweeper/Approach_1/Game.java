package lld_game_design_primer.minesweeper.Approach_1;

import java.util.Random;
import java.util.Scanner;


public class Game {

    private Board board;

    private int boardRows;
    private int boardColumns;
    private int numberOfBombsInTheGame;
   

    public Game(int boardRows, int boardColumns, int numberOfBombs) {
        this.boardRows = boardRows;
        this.boardColumns =  boardColumns;
        this.numberOfBombsInTheGame = numberOfBombs;
    }

    private void initialize() {
        if (board == null) {
            board = new Board(boardRows, boardColumns, numberOfBombsInTheGame);
            board.printBoard(); // display board
        }
    }

    public void start() {
        if (board == null) {
            initialize();
        }
        playGame();
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        Board.GameState gameState = Board.GameState.INPROGRESS;

        while (gameState == Board.GameState.INPROGRESS) {
            String input = scanner.nextLine(); // input the coordinate of the cell you want to click in the form of row-col
            if (input.equals("exit")) { // exit is the keyword to exit the game
                scanner.close();
                return;
            }

            int row = Integer.parseInt(input.split("-")[0]);
            int col = Integer.parseInt(input.split("-")[1]);

            gameState = board.exposeCell(row, col);

            // check if the player won or lost by making the above move
            if (gameState == Board.GameState.LOST) {
                System.out.println("FAIL");
            } else if (gameState == Board.GameState.WON) {
                System.out.println("WIN");
            }

            board.printBoard(); // display board after the player has made the move
        }
        scanner.close();
    }
     
}
