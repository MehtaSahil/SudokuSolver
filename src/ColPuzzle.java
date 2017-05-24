/**
 * Created by sahil on 5/23/17.
 */

public class ColPuzzle {

    private final int num_rows;
    private final int num_cols;
    private Vector[] col_puzzle_data;

    public ColPuzzle(Square[][] init_data)
    {
        num_rows = init_data.length;
        num_cols = init_data[0].length;

        if (num_rows != num_cols || num_rows != 9)
            throw new IllegalArgumentException("ColPuzzle Constructor: init_data must be 9x9");

        col_puzzle_data = new Vector[num_cols];
        init_col_puzzle_data(init_data);
    }

    private void init_col_puzzle_data(Square[][] init_data)
    {
        /* create a new Vector for each col in init_data */
        for (int c = 0; c < num_cols; c++)
        {
            Square[] slice = new Square[num_cols];
            for (int r = 0; r < num_rows; r++)
            {
                slice[r] = init_data[r][c];
            }

            col_puzzle_data[c] = new Vector(slice);
        }
    }

    /**
     *
     * @param index
     * @return Vector at [index] in col_puzzle_data
     */
    public Vector get_col_vector(int index)
    {
        return col_puzzle_data[index];
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                s.append(col_puzzle_data[c].get_value(r) + ", ");
            }

            s.append("\n");
        }

        return s.toString();
    }
}
