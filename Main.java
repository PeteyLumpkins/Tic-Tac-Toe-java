package tictactoe;
import java.util.Scanner;
import java.util.Arrays;


class Board {
   String input;
   String[][] grid;
   String board;
   String error;

   public Board(String input) {
      this.input = input;
      this.grid = createGrid();
      this.board = "";
      this.error = null;
   }
   public void setGrid(int x, int y, String player) {
      this.grid[x - 1][y - 1] = player;
   }
   public void setGrid(String swap) {
      if (swap.equals("Swap")){
         String[] temp = this.grid[0];
         this.grid[0] = this.grid[2];
         this.grid[2] = temp;
      } else if (swap.equals("Transpose")){
         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               if (j <= i) {
                  String temp = this.grid[i][j];
                  this.grid[i][j] = this.grid[j][i];
                  this.grid[j][i] = temp;
               }
            }
         }
      }
   }
   public void setError(String error) { this.error = error; }
   public void updateBoardPos(int x, int y, String player) {
      this.setGrid(x, y, player);
      this.setBoard();
   }
   public boolean checkValidPos(String x, String y) {
      String nums = "0123456789";
      if (!nums.contains(x) || !nums.contains(y)) {
         setError("You should enter numbers!");
         return false;
      }
      else if (Integer.parseInt(y) > this.grid.length || Integer.parseInt(x) > this.grid[0].length) {
         setError("Coordinates should be from 1 to 3!");
         return false;
      }
      else if (!this.grid[Integer.parseInt(x) - 1][Integer.parseInt(y) - 1].equals("_")) {
         setError("This cell is occupied! Choose another one!");
         return false;
      } else { return true; }
   }
   public String[][] createGrid() {
      String[] arry = input.split("");
      int count = 0;
      String[][] grid = new String[3][3];
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {

            grid[i][j] = arry[count];
            count++;
         }
      }
      return grid;
   }
   public void setBoard() {
      String board = "";
      board += "---------\n";
      for (int i = 0; i < 3; i++) {
         board += "| ";
         for (int j = 0; j < 3; j++) {
            board += this.grid[i][j] + " ";
         }
         board += "|\n";
      }
      board += "---------";
      this.board = board;
   }
}

class Game {
   Board ticTacToe;
   String[][] grid;

   public Game(Board ticTacToe) {
      this.ticTacToe = ticTacToe;
      this.grid = ticTacToe.grid;
   }
   public String checkGameState() {
      if ((checkWin("O") && checkWin("X")) || checkSums()) {
         return "Impossible";
      }
      else if (checkWin("O")) {
         return "O wins";
      }
      else if (checkWin("X")) {
         return "X wins";
      }
      else if (!checkWin("X") && !checkWin("O") && !checkEmpty()) {
         return "Draw";
      }
      else {
         return "Game not finished"; }
   }

   public boolean checkWin(String player) {
      return checkDiagnal(player).equals(player) || checkCols(player).equals(player) || checkRows(player).equals(player);
   }

   public String checkDiagnal(String player) {
      if (grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2]) && grid[1][1].equals(player)) {
         return grid[1][1];
      } else if (grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0]) && grid[1][1].equals(player)) {
         return grid[1][1];
      }
      return "";
   }

   public String checkRows(String player) {
      for (int i = 0; i < 3; i++) {
         if (grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2]) && grid[i][1].equals(player)) {
            return grid[i][0];
         }
      }
      return "";
   }

   public String checkCols(String player) {
      for (int i = 0; i < 3; i++) {
         if (grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i]) && grid[1][i].equals(player)) {
            return grid[0][i];
         }
      }
      return "";
   }

   public boolean checkEmpty() {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            if (grid[i][j].equals("_")) {
               return true;
            }
         }
      }
      return false;
   }

   public boolean checkSums() {
      int X = 0;
      int O = 0;
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            if (grid[i][j].equals("X")) {
               X += 1;
            } else if (grid[i][j].equals("O")) {
               O += 1;
            }
         }
      }
      return X - O > 1 || O - X > 1;
   }
}
public class Main {
   public String findPlayer(int turn) {
      if (turn % 2 == 0) {
         return "X";
      }
      else {return "O";}
   }
   public static void main(String[] args) {
      boolean run = true;
      Board board = new Board("_________");
      Game newGame = new Game(board);
      int turn = 0;
//      board.setBoard();
//      System.out.println(board.board);
//      while (run) {
//         String pos1 = scanner.next();
//         String pos2 = scanner.next();
//         board.setGrid("Swap");
//         board.setGrid("Transpose");
//         if (!board.checkValidPos(pos1, pos2)) {
//            System.out.println(board.error);
//            board.setGrid("Transpose");
//            board.setGrid("Swap");
//            continue;
//         }
//         board.updateBoardPos(Integer.parseInt(pos1), Integer.parseInt(pos2), "X");
//         board.setGrid("Transpose");
//         board.setGrid("Swap");
//         board.setBoard();
//         run = false;
//      }
//
//      System.out.println(board.board);
//      System.out.println(newGame.checkGameState());

      while (newGame.checkGameState().equals("Game not finished")) {
         Scanner scanner = new Scanner(System.in);
         String player = "";
         if (turn % 2 == 0) {
            player = "X";
         } else {player = "O";}
         board.setBoard();
         System.out.println(board.board);
         String pos1 = scanner.next();
         String pos2 = scanner.next();
         board.setGrid("Swap");
         board.setGrid("Transpose");
         if (!board.checkValidPos(pos1, pos2)) {
            System.out.println(board.error);
            board.setGrid("Transpose");
            board.setGrid("Swap");
            continue;
         }
         int x = Integer.parseInt(pos1);
         int y = Integer.parseInt(pos2);
         board.updateBoardPos(x, y, player);
         board.setGrid("Transpose");
         board.setGrid("Swap");
         board.setBoard();
         turn += 1;
      }
      System.out.println(board.board);
      System.out.println(newGame.checkGameState());
   }
}