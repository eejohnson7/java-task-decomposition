package unit2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Consider a grid of characters in the form of a 2D array, where each cell represents a distinct character selected from a-z. Your task is to 
process this grid following a specific order.

Start from the top-left cell of the grid and move in a clockwise spiral direction. Initially, go right until you hit the right boundary, 
then down until you reach the bottom boundary, then left until you encounter the left boundary, and finally, up until you hit the top boundary 
(note that the top boundary is now the first row since we already visited the first cell in the matrix). Once this cycle is complete, 
move inwards, i.e., one cell to the right, and repeat the spiral process within the remaining unvisited cells.

During this spiral traversal, you will generate a sequence of visited cell characters. Afterwards, identify the vowels (a, e, i, o, u) in the 
sequence and return their positions in a 0-indexed order.

Please implement the function spiralTraverseAndVowels(char[][] grid) to achieve this. This function takes a 2D array of characters (grid) as 
input and returns an array containing the positions of the vowels in the spirally traversed sequence.

For instance, consider the following 3x4 grid:
{{'a', 'b', 'c', 'd'},
{'e', 'f', 'g', 'h'},
{'i', 'j', 'k', 'l'}}
Upon completing the spiral traversal, we will obtain the sequence: {'a', 'b', 'c', 'd', 'h', 'l', 'k', 'j', 'i', 'e', 'f', 'g'}. From this 
sequence, we observe that 'a', 'i', and 'e' are vowels and are located at the 0th, 8th, and 9th positions in the sequence, so our function 
returns: {0, 8, 9}.

The size of the 2D array (grid) will not exceed 100x100, and each character will be a lowercase letter from 'a' to 'z'.
*/

public class SpiralTraversal {
    public int[] spiralTraverseAndVowels(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        return getVowels(traverse(grid));
    }
    
    private List<Character> traverse(char[][] grid) {
        List<Character> traversal = new ArrayList<>();
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        int row = 0, col = 0, dir = 1;
        int topBound = 0, botBound = rows - 1, leftBound = 0, rightBound = cols - 1;
        for (int i = 0; i < rows * cols; i++) {
            traversal.add(grid[row][col]);
            
            if (dir == 1) {
                if (col == rightBound) {
                    topBound++;
                    dir = -2;
                    row++;
                } else {
                    col++;
                }
            } else if (dir == -2) { 
                if (row == botBound) {
                    rightBound--;
                    dir = -1; 
                    col--;
                } else {
                    row++;
                }
            } else if (dir == -1) {
                if (col == leftBound) {
                    botBound--;
                    dir = 2;
                    row--;
                } else {
                    col--;
                }
            } else if (dir == 2) { 
                if (row == topBound) { 
                    leftBound++;
                    dir = 1; 
                    col++;
                } else {
                    row--;
                }
            }
        }
        
        return traversal;
    }
    
    private int[] getVowels(List<Character> traversal) {
        String vowels = "aeiou";
        
        List<Integer> vowelIndex = new ArrayList<>();
        for (int i = 0; i < traversal.size(); i++) {
            if (vowels.indexOf(traversal.get(i)) > -1) {
                vowelIndex.add(i);
            }
        }
        
        int[] result = new int[vowelIndex.size()];
        for (int i = 0; i < vowelIndex.size(); i++) {
            result[i] = vowelIndex.get(i);
        }
        
        return result;
    }
}
