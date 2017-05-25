import PuzzlePieces.Square;

/**
 * Created by sahil on 5/23/17.
 */

public class DataConverter {

    /**
     * Converts int[][] input data to a Square[][]
     * @param init_data int[][] input data
     * @return Square[][] matrix used in analysis
     */
    public static Square[][] convertMatrixToSquares(int[][] init_data)
    {
        int num_rows = init_data.length;
        int num_cols = init_data[0].length;

        if (num_rows != num_cols || num_rows != 9)
            throw new IllegalArgumentException("DataConverter Constructor: init_data must be 9x9");

        /**
         * create matrix of Squares with identical data
         * */
        Square[][] toReturn = new Square[num_rows][num_cols];
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                toReturn[r][c] = new Square(r, c, init_data[r][c]);
            }
        }

        return toReturn;
    }
}
