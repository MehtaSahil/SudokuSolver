/**
 * Created by sahil on 5/23/17.
 */

public class RowPuzzle {

    private final int num_rows;
    private final int num_cols;
    private Vector[] row_puzzle_data;

    public RowPuzzle(Square[][] init_data)
    {
        num_rows = init_data.length;
        num_cols = init_data[0].length;

        if (num_rows != num_cols || num_rows != 9)
            throw new IllegalArgumentException("RowPuzzle Constructor: init_data must be 9x9");

        row_puzzle_data = new Vector[num_rows];
        init_row_puzzle_data(init_data);
    }

    private void init_row_puzzle_data(Square[][] init_data)
    {
        /* create a new Vector for each row in init_data */
        for (int r = 0; r < num_rows; r++)
        {
            Square[] slice = new Square[num_cols];
            for (int c = 0; c < num_cols; c++)
            {
                slice[c] = init_data[r][c];
            }

            row_puzzle_data[r] = new Vector(slice);
        }
    }

    /**
     * @param index
     * @return Vector at [index] in row_puzzle_data
     */
    public Vector get_row_vector(int index)
    {
        return row_puzzle_data[index];
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                s.append(row_puzzle_data[r].get_value(c) + ", ");
            }

            s.append("\n");
        }

        return s.toString();
    }
}