package Sudoku_Game;

public class Sudoku_solver {
    //define the grid size
    //here we have a 9x9 grid
    private static final int GRID_SIZE = 9;


    //now we will create three helper methods to check whether a number already exits in
    // row,column or small grid(3x3)

    //firstly we will check for row
    //number is the number which we want to place and row is the row in which we want to place the number
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for(int i= 0; i< GRID_SIZE; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;

    }

    //now we will check for column
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for(int i= 0; i< GRID_SIZE; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;

    }

    //now we will check for small grid
     private static boolean isNumberInBox(int[][] board, int number,int row, int column){
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
        return !isNumberInRow(board, number, row)&&
        !isNumberInColumn(board, number, column)&&
        !isNumberInBox(board, number, row, column);
    } 

    //now we will create the method that will solve and backtrack
    //now the game will start as the computer will traverse on all the smaall boxes one by one on one row and then next row
    //check whether it is a valid placement
    //if yes then place the number and goes on next blank box
    //now if somewhere a box is encountered where none of the digits from 1 to 9 is valid then it will backtrack to previous number
    //it will go to previous and change the number there if possible and if not then it will go to the previous of that particular box
    

    private static boolean solveBoard(int[][] board){
        for ( int row=0; row< GRID_SIZE;row ++){
            for (int column = 0; column < GRID_SIZE; column ++){
                if(board[row][column] ==0){
                    for(int numberToTry =1; numberToTry <= GRID_SIZE; numberToTry ++){
                        if(isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = numberToTry;

                            //here recursion is used
                            if(solveBoard(board)){
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
    for (int row = 0; row < GRID_SIZE; row++) {
      if (row % 3 == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int column = 0; column < GRID_SIZE; column++) {
        if (column % 3 == 0 && column != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
  }








    public static void main(String[] args) {
       
        //here zero represents the blank spaces and the numbers are fixed
        //it is basically a 2D array
        int[][] board={
            {7,0,2,0,5,0,6,0,0},
            {0,0,0,0,0,3,0,0,0},
            {1,0,0,0,0,9,5,0,0},
            {8,0,0,0,0,0,0,9,0},
            {0,4,3,0,0,0,7,5,0},
            {0,9,0,0,0,0,0,0,8},
            {0,0,9,7,0,0,0,0,5},
            {0,0,0,2,0,0,0,0,0},
            {0,0,7,0,4,0,2,0,3}
        }; 


        printBoard(board);
        
        if (solveBoard(board)) {
        System.out.println("Solved successfully!");
        }
        else {
        System.out.println("Unsolvable board :(");
        }
        
        printBoard(board);
        
    }



    
    
}
