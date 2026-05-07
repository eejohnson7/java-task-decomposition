package unit3;

import java.util.ArrayList;
import java.util.List;

/*
You are tasked with creating a Java method named matrixBoundaryConcatenation(). This method should accept two 2D matrices, matrixA and
 matrixB, and the number of boundary layers, n, to extract from both matrices.

In this context, a boundary layer refers to the elements that form the outer contour of a matrix. For instance, the first layer of the 
following 4x4 matrix includes the elements 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, and 5:

Plain text
1  2  3  4
5  6  7  8
9  10 11 12
13 14 15 16
Your method should extract the first n boundary layers from both matrixA and matrixB. It should then concatenate these extracted layers 
into a new list, ensuring that the layers from matrixA precede those from matrixB in the resultant list.

The matrices matrixA and matrixB will be square matrices, with each side's length ranging from 1 to 10. The number of layers n will be 
less than or equal to the side length of the square matrices.

The method signature should be:

Java
Copy to clipboard
public List<Integer> matrixBoundaryConcatenation(int[][] matrixA, int[][] matrixB, int n);
The elements in the input matrices can be any integer between -100 and 100.

Example

Consider the following input to our method:

Java
int[][] matrixA = { {1, 2, 3, 4}, 
                    {5, 6, 7, 8}, 
                    {9, 10, 11, 12},
                    {13, 14, 15, 16} };

int[][] matrixB = { {17, 18, 19, 20}, 
                    {21, 22, 23, 24}, 
                    {25, 26, 27, 28}, 
                    {29, 30, 31, 32} };

int n = 2;
Our method matrixBoundaryConcatenation(matrixA, matrixB, n) should return:

Java
Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10, 17, 18, 19, 20, 24, 28, 32, 31, 30, 29, 25, 21, 22, 23, 27, 26)
Explanation:

In matrixA, the first boundary layer is composed of the elements 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, and 5, taken in a clockwise 
direction from the top-left corner. Our second layer then includes the elements 6, 7, 11, and 10.

For matrixB, the corresponding boundary layers include the elements 17, 18, 19, 20, 24, 28, 32, 31, 30, 29, 25, 21 for the first layer 
and 22, 23, 27, 26 for the second one.

The method outputs a list where the extracted layers from matrixA are followed by those from matrixB.
*/

public class MatrixBounmdaryConcatenation {
    public List<Integer> matrixBoundaryConcatenation(int[][] matrixA, int[][] matrixB, int n) {        
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            result.addAll(extractLayer(matrixA, i));
        }
        
        for (int i = 0; i < n; i++) {
            result.addAll(extractLayer(matrixB, i));
        }

        return result;
    }
    
    private List<Integer> extractLayer(int[][] matrix, int level) {        
        List<Integer> layer = new ArrayList<>();
        
        int n = matrix.length - (level * 2);
        int layerLength = n == 1 ? n : 4 * n - 4;
        
        int row = level, col = level;
        String dir = "right";
        
        for (int i = 0; i < layerLength; i++) {
            layer.add(matrix[row][col]);
            
            if ("right".equals(dir)) {
                if (col == matrix.length - 1 - level) {
                    dir = "down";
                    row++;
                } else {
                    col++;
                }
            } else if ("down".equals(dir)) {
                if (row == matrix.length - 1 - level) {
                    dir = "left";
                    col--;
                } else {
                    row++;
                }
            } else if ("left".equals(dir)) {
                if (col == level) {
                    dir = "up";
                    row--;
                } else {
                    col--;
                }
            } else if ("up".equals(dir)) {
                row--;
            }
        }
        
        return layer;
    }
}
