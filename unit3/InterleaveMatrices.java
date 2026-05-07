package unit3;

import java.util.Arrays;

/*
Your task is to write a function, interleaveMatrices, that takes two matrices (2D arrays) and a start and end range for rows and columns 
for each matrix as inputs. Instead of concatenating submatrices together, this task requires interleaving the columns from the submatrices 
within the final matrix.

If A and B are your two matrices, and the respective submatrices selected from them based on the given ranges are sub_A and sub_B, then the 
task is to form a new matrix C by interleaving columns from sub_A and sub_B. Starting with the first column of sub_A, alternately include a 
column from sub_A and a column from sub_B until all columns from both submatrices are included.

All matrices are filled with integers. The size of each matrix, A and B, ranges between 1×1 and 10×10, inclusive, and each element in the 
matrix is from the range of −100 to 100, inclusive. The start and end ranges for rows and columns for each matrix are provided as an array 
{start_row, end_row, start_column, end_column}, and these are 0-based indices.

For example, if A is:
{{1, 2, 3, 4},
{5, 6, 7, 8},
{9, 10, 11, 12}}

and B is:
{{11, 12, 13},
{14, 15, 16},
{17, 18, 19}}

If we select 2x2 submatrices from each (comprising the 2nd to the 3rd rows and the 2nd to the 3rd columns from A, and the 1st to the 
2nd rows and the 1st to the 2nd columns from B), their interleaved combination would look like this:
{{6, 11, 7, 12},
{10, 14, 11, 15}}
Note that in the output, columns from sub_A and sub_B are interwoven.

It is guaranteed that the given submatrices have pairwise equal dimensions.
*/

public class InterleaveMatrices {
    public int[][] interleaveMatrices(int[][] matrixA, int[][] matrixB, int[][] submatrixCoords) {
        //logMatrix(matrixA, "matrixA");
        //logMatrix(matrixB, "matrixB");
        //logMatrix(submatrixCoords, "submatrixCoords");
        
        int[][] submatrixA = getSubmatrix(matrixA, submatrixCoords[0]);
        //logMatrix(submatrixA, "submatrixA");
        
        int[][] submatrixB = getSubmatrix(matrixB, submatrixCoords[1]);
        //logMatrix(submatrixB, "submatrixB");
        
        int[][] interleave = interleaveMatrices(submatrixA, submatrixB);
        //logMatrix(interleave, "interleave");
        
        return interleave;
    }
    
    private int[][] interleaveMatrices(int[][] submatrixA, int[][] submatrixB) {
        int rows = submatrixA.length;
        int colsA = submatrixA[0].length;
        int colsB = submatrixB[0].length;
        
        int[][] interleave = new int[rows][colsA + colsB];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colsA; j++) {          
                interleave[i][2 * j] = submatrixA[i][j]; // interleave[0][0] = submatrixA[0][0], interleave[0][2]
            }
            
            for (int j = 0; j < colsB; j ++) {
                interleave[i][2 * j + 1] = submatrixB[i][j];
            }
        }
        
        return interleave;
    }
    
    private int[][] getSubmatrix(int[][] matrix, int[] submatrixCoords) {
        int startRow = submatrixCoords[0];
        int endRow = submatrixCoords[1];
        int rows = endRow - startRow + 1;
        
        int startCol = submatrixCoords[2];
        int endCol = submatrixCoords[3];
        int cols = endCol - startCol + 1;
        
        int[][] submatrix = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                submatrix[i][j] = matrix[startRow + i][startCol + j];
            }
        }
        
        return submatrix;
    }
    
    /*
    private void logMatrix(int[][] matrix, String title) {
        System.out.println(title + ": ");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    */
}
