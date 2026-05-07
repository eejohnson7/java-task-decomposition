package unit3;

/*
Warren, an innovator in mathematical problems, challenges you to solve a complex task involving matrix manipulation. He provides you with a 2D array, M, with dimensions m x n, 
where m and n range from 1 to 500, inclusive. Each element in the matrix ranges from -100 to 100, inclusive.

Warren further provides you with the coordinates describing two sub-matrices within M, denoted as S1 and S2. He asks you to write a Java function, say submatrixSwap(), which takes 
as inputs the matrix M and the coordinates specifying the sub-matrices S1 and S2. This function is required to swap the positions of S1 and S2 within M.

The swapping of sub-matrices is subject to the following constraints:

The sub-matrices do not overlap.
S1 and S2 must have identical dimensions, i.e., the number of rows and columns in S1 must equal the number of rows and columns in S2.
The coordinates of each submatrix are given by 4 coordinates - {row_l, row_r, col_l, col_r}, which correspond to a valid submatrix with rows in [row_l, row_r) and columns in 
[col_l, col_r).
Example

Let's consider an example to clarify the task:

Suppose 'M' is:

Java
int[][] M = {{1, 2, 3, 4, 5},
             {6, 7, 8, 9, 10},
             {11, 12, 13, 14, 15},
             {16, 17, 18, 19, 20},
             {21, 22, 23, 24, 25}};
With sub-matrix S1 defined by the coordinates 0, 2, 2, 4 (indicating that it spans from rows 0 to 1 and columns 2 to 3), and S2 given the coordinates 2, 4, 0, 2.

Our function submatrixSwap(matrix, new int[]{0, 2, 2, 4}, new int[]{2, 4, 0, 2}) should obtain the following swapped matrix:

Java
M = {{1, 2, 11, 12, 5},
     {6, 7, 16, 17, 10},
     {3, 4, 13, 14, 15},
     {8, 9, 18, 19, 20},
     {21, 22, 23, 24, 25}};
Explanation:

In this scenario, the sub-matrix S1 spans rows 0 to 1 and columns 2 to 3 (0-indexed) and includes the elements 3, 4, 8, 9. The sub-matrix S2 spans rows 2 to 3 and columns 0 to 1 
(0-indexed) and includes the elements 11, 12, 16, 17.

The function submatrixSwap() swaps the positions of S1 and S2 within the matrix M. As a result, the columns 2 and 3 in rows 0 and 1 have been replaced by S2, and the columns 0 and 1 
in rows 2 and 3 have been replaced by S1.
*/

public class SubmatrixSwap {
  public void submatrixSwap(int[][] matrix, int[] coord_S1, int[] coord_S2) {    
    int[][] S1 = getSubmatrix(matrix, coord_S1);    
    int[][] S2 = getSubmatrix(matrix, coord_S2);
    
    swapCoords(matrix, coord_S1, S2);
    swapCoords(matrix, coord_S2, S1);
  }
  
  private int[][] swapCoords(int[][] matrix, int[] coords, int[][] submatrix) {
    int startRow = coords[0];
    int endRow = coords[1] - 1;
    int startCol = coords[2];
    int endCol = coords[3] - 1;
    
    int subRow = 0;
    for (int row = startRow; row <= endRow; row++) {
      int subCol = 0;
      
      for (int col = startCol; col <= endCol; col++) {
        matrix[row][col] = submatrix[subRow][subCol];
        subCol++;
      }
      
      subRow++;
    }
    
    return matrix;
  }
  
  private int[][] getSubmatrix(int[][] matrix, int[] coords) {    
    int startRow = coords[0];
    int endRow = coords[1] - 1;
    int rows = endRow - startRow + 1;
        
    int startCol = coords[2];
    int endCol = coords[3] - 1;
    int cols = endCol - startCol + 1;
        
    int[][] submatrix = new int[rows][cols];
        
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          submatrix[i][j] = matrix[startRow + i][startCol + j];
        }
    }
        
    return submatrix;
  }
}
