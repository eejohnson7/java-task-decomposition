package unit2;

/*
You are given a 2D array of integer values where each cell represents a unique integer. The size of the matrix, n x n, ranges from 1 x 1 to 
10 x 10, and each integer cell value, v, ranges from 1 to 100, inclusive.

Your task is to traverse the matrix in a unique way: Start from the top-left cell and move right until you hit the upper right corner. Then, 
move downward one cell and start moving to the left until you hit the left boundary. Upon hitting the left boundary, move down one cell and 
start moving right until you hit the right boundary. When you hit the right boundary, move down one cell and start moving left again. 
Continue this pattern until you have traversed every cell in the matrix.

Having completed this zigzag traversal, you will gather a list of traversed cell values. Your task now is to process this list and identify 
the values of the prime numbers and their indices. Therefore, implement the function zigzagTraverseAndPrimes(int[][] matrix) that returns a
 map where each key-value pair represents an index and the prime number found at that index from the traversed list.

For instance, suppose you have a 4x4 matrix:
{
    {10, 11, 4, 3},
    {6, 7, 15, 13},
    {8, 14, 1, 2},
    {5, 9, 12, 19}
}
Upon completing the zigzag traversal, you obtain the list: {10, 11, 4, 3, 13, 15, 7, 6, 8, 14, 1, 2, 19, 12, 9, 5}. From this list, we observe
 that 11, 3, 13, 7, 2, 19, and 5 are prime numbers, and they are located at the 1st, 3rd, 4th, 6th, 11th, 12th, and 15th positions (0-indexed) 
 in the list. Our function should return: {1: 11, 3: 3, 4: 13, 6: 7, 11: 2, 12: 19, 15: 5}.

Remember, a prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself. The first few prime numbers are 
2, 3, 5, 7, 11, and so on.
*/

import java.util.HashMap;
import java.util.Map;

public class ZigZagTraverseAndPrimes {
    private static boolean isPrime(int n) {
        if (n == 1) { return false; }
        if (n <= 3) { return true; }
        if (n % 2 == 0 || n % 3 == 0) { return false; }
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) { return false; }
        }
        
        return true;
    }
    
    private int[] traverse(int[][] matrix) {
        int n = matrix.length;
        int[] traversal = new int[n * n];
        
        int row = 0, col = 0, dir = 1;
        for(int i = 0; i < n * n; i++) {
            traversal[i] = matrix[row][col];
            
            if (dir == 1) {
                if (col == n - 1) {
                    dir = -1;
                    row++;
                } else {
                    col++;
                }
            } else if (dir == -1) {
                if (col == 0) {
                    dir = 1;
                    row++;
                } else {
                    col--;
                }
            }
        }
        
        return traversal;
    }

    public Map<Integer, Integer> zigzagTraverseAndPrimes(int[][] matrix) {        
        HashMap<Integer, Integer> result = new HashMap<>();
        int[] traversal = traverse(matrix);
        
        for (int i = 0; i < traversal.length; i++) {
            if (isPrime(traversal[i])) {
                result.put(i, traversal[i]);
            }
        }
        
        return result;
    }
}
