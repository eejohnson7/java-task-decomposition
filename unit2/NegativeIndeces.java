package unit2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a matrix of n x m integers, where n represents the number of rows and m represents the number of columns. Both n and m range from 1 to 100, inclusive.

The matrix cells may contain either a positive, a negative integer, or zero, with values ranging from -100 to 100, inclusive.

In this task, you are required to traverse the matrix diagonally from the top-left cell to the bottom-right cell in a zigzag pattern. Start from the top-left cell, move one cell to the right (if it exists), then move one step diagonally down-left. After reaching a left (bottom) boundary, move one step down (right) and start moving diagonally up-right. Continue this pattern until you reach the last cell of the matrix. Your task is to return a list of tuples, each tuple containing the index pair (in 0-based indexing format) of cells with negative integers encountered during your traversal.

For example, consider a 3 x 4 matrix:

{{1, -2, 3, -4},
{5, -6, 7, 8},
{-9, 10, -11, 12}}
The traversal in a zigzag pattern will result in: {1, -2, 5, -9, -6, 3, -4, 7, 10, -11, 8, 12}.

The negative integers in this sequence and their corresponding positions in the matrix are: {-2, -9, -6, -4, -11}, with indices: {{0, 1}, {2, 0}, {1, 1}, {0, 3}, {2, 2}}.

Your function, solution(matrix), should then return these indices as a list of arrays: {{0, 1}, {2, 0}, {1, 1}, {0, 3}, {2, 2}}.
 */

public class NegativeIndeces {
    public List<int[]> solution(int[][] matrix) {
        // {{1, -2, 3, -4},
        // {5, -6, 7, 8},
        // {-9, 10, -11, 12}}
        // {1, -2, 5, -9, -6, 3, -4, 7, 10, -11, 8, 12}
        
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int diags = rows + cols;
        
        List<int[]> negs = new ArrayList<>();
        
        for (int d = 0; d < diags; d++) {
            // for any diag -> d = row + col
            // 0 <= row < rows
            // 0 <= col < cols
            // col = d - row
            // 0 <= d - row < cols
            // row <= d && row > d - cols
            
            if (d % 2 == 0) { // even diags -> traverse up right
                int minRow = Math.max(0, d - (cols - 1)); // largest value that keeps the col valid
                int maxRow = Math.min(d, rows - 1); // smallest value that keeps col valid
                
                while(minRow <= maxRow) {
                    int col = d - minRow;
                    
                    if (matrix[minRow][col] < 0) {
                        negs.add(new int[]{minRow, col});
                    }
                    
                    minRow++;
                }
                
            } else { // odd diags -> traverse down left
                int minRow = Math.max(0, d - (cols - 1)); // largest value that keeps the col valid
                int maxRow = Math.min(d, rows - 1); // smallest value that keeps col valid
                
                while(maxRow >= minRow) {
                    int col = d - maxRow;
                    
                    if (matrix[maxRow][col] < 0) {
                        negs.add(new int[]{maxRow, col});
                    }
                    
                    maxRow--;
                } 
            }
        }

        return negs;
    }
}
