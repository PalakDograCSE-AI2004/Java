package Sudoku_Game;


import java.util.Scanner;


public class SudokuProject {
    //define the grid size
    //here we have a 9x9 grid
    private static final int GRIDSIZE = 9;


    //now we will create three helper methods to check whether a number already exits in
    // row,column or small grid(3x3)

    //firstly we will check for row
    //number is the number which we want to place and row is the row in which we want to place the number
    private static boolean Is_number_present_in_row(int[][] board, int number, int row){
        for(int i= 0; i< GRIDSIZE; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;

    }

    //now we will check for column
    private static boolean Is_number_present_in_column(int[][] board, int number, int column){
        for(int i= 0; i< GRIDSIZE; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;

    }

    //now we will check for small subgrid
     private static boolean Is_number_present_in_subgrid(int[][] board, int number,int row, int column){
        int localBoxRow=row-row%3;
        int localBoxColumn=column-column%3; 

        for(int i=localBoxRow;i< localBoxRow + 3;i++){
            for(int j=localBoxColumn;j<localBoxColumn +3;j++){
                if( board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    //now we will create a method that will check the above three and will tell whether it is a valid placement or not
    private static boolean isValidPlacement( int[][] board, int number,int row, int column){
        return !Is_number_present_in_row(board, number, row)&&
        !Is_number_present_in_column(board, number, column)&&
        !Is_number_present_in_subgrid(board, number, row, column);
    } 

    //now we will create the method that will solve and backtrack
    //now the game will start as the computer will traverse on all the smaall boxes one by one on one row and then next row
    //check whether it is a valid placement
    //if yes then place the number and goes on next blank box
    //now if somewhere a box is encountered where none of the digits from 1 to 9 is valid then it will backtrack to previous number
    //it will go to previous and change the number there if possible and if not then it will go to the previous of that particular box
    

    private static boolean Solve_the_sudoku(int[][] board){
        for ( int row=0; row< GRIDSIZE;row ++){
            for (int column = 0; column < GRIDSIZE; column ++){
                if(board[row][column] ==0){
                    for(int numberToPlace =1; numberToPlace <= GRIDSIZE; numberToPlace ++){
                        if(isValidPlacement(board, numberToPlace, row, column)){
                            board[row][column] = numberToPlace;

                            //here recursion is used
                            if(Solve_the_sudoku(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    private static void printBoard(int[][] board) {
    for (int row = 0; row < GRIDSIZE; row++) {
      if (row % 3 == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int column = 0; column < GRIDSIZE; column++) {
        if (column % 3 == 0 && column != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
  }








    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("*********************************************************************************");
        System.out.println("                     Welcome to my project                                       ");
        System.out.println("*********************************************************************************");

        System.out.println("enter name");
        String name;
        name=sc.nextLine();
        System.out.println("Hello "+ name );
        System.out.println("Enter the sudoku which you want to solve");
        System.out.println("Enter 0 where you want blank ");

        
        //it is a 2D array
        //here zero represents the blank spaces and the numbers are fixed
        int[][] board = new int[9][9];
        

        // Iterate over each row
        for (int i = 0; i < 9; i++) {
            // Iterate over each column
            for (int j = 0; j < 9; j++) {
                System.out.print("Enter value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                board[i][j] = sc.nextInt();
            }
        }

        // Print the array to verify the input
        System.out.println("Sudoku you want to solve:");
        System.out.println("  ");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }


        
        if (Solve_the_sudoku(board)) {
        System.out.println("Solved successfully!");
         printBoard(board);
        }
        else {
        System.out.println("Unsolvable board ");
        }
        
        
        
    }   
}
