/**
 * Created by sahil on 5/23/17.
 */

public class ColPuzzle {

    private Vector[] col_puzzle_data;

    public ColPuzzle(Square[][] init_data)
    {
        init_col_puzzle_data(init_data);
    }

    private void init_col_puzzle_data(Square[][] init_data)
    {
        /* create a new Vector for each col in init_data */
        for (int c = 0; c < init_data[0].length; c++)
        {
            Square[] slice = new Square[9];
            for (int r = 0; r < init_data[c].length; r++)
            {
                slice[c] = init_data[r][c];
            }

            col_puzzle_data[c] = new Vector(slice);
        }
    }

    public String toString()
    {
        return "";
    }
}
