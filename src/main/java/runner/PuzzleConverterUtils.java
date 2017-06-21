package main.java.runner;

import main.java.puzzlepiece.Square;

/**
 * Created by sahil on 5/23/17.
 */

public class PuzzleConverterUtils {

    /**
     * Converts int[][] input data to a Square[][]
     *
     * @param init_data int[][] input data
     * @return Square[][] matrix used in analysis
     */
    public static Square[][] convert2dIntegerMatrixToSquares(int[][] init_data) {
        int num_rows = init_data.length;
        int num_cols = init_data[0].length;

        if (num_rows != num_cols || num_rows != 9)
            throw new IllegalArgumentException("main.java.runner.PuzzleConverterUtils Constructor: init_data must be 9x9");

        /**
         * create matrix of Squares with identical data
         * */
        Square[][] to_return = new Square[num_rows][num_cols];
        for (int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                to_return[r][c] = new Square(r, c, init_data[r][c]);
            }
        }

        return to_return;
    }

    public static int[][] convert2dSquareMatrixToIntegers(Square[][] square_data)
    {
        int num_rows = square_data.length;
        int num_cols = square_data[0].length;

        /* create matrix of integers with identical data */
        int[][] to_return = new int[num_rows][num_cols];
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                to_return[r][c] = square_data[r][c].get_value();
            }
        }

        return to_return;
    }
}
