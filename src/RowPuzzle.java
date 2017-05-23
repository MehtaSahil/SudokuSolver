/**
 * Created by sahil on 5/23/17.
 */

public class RowPuzzle {

    private Vector[] row_puzzle_data;

    public RowPuzzle(Square[][] init_data)
    {
        init_row_puzzle_data(init_data);
    }

    private void init_row_puzzle_data(Square[][] init_data)
    {
        /* create a new Vector for each row in init_data */
        for (int r = 0; r < init_data.length; r++)
        {
            Square[] slice = new Square[9];
            for (int c = 0; c < init_data[r].length; c++)
            {
                slice[c] = init_data[r][c];
            }

            row_puzzle_data[r] = new Vector(slice);
        }
    }

    public String toString()
    {
        return "";
    }
}