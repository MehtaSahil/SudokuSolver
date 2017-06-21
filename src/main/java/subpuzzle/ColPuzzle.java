package main.java.subpuzzle;

import main.java.abstraction.AbstractVectorPuzzle;
import main.java.puzzlepiece.Square;
import main.java.puzzlepiece.Vector;

/**
 * Created by sahil on 5/23/17.
 */

public class ColPuzzle extends AbstractVectorPuzzle {

    public ColPuzzle(Square[][] init_data)
    {
        super(init_data);
    }

    /**
     * Creates column vectors
     * @param init_data input data
     */
    public void init_puzzle_data(Square[][] init_data)
    {
        /* create a new main.java.puzzlepiece.Vector for each col in init_data */
        for (int c = 0; c < num_cols; c++)
        {
            Square[] slice = new Square[num_cols];
            for (int r = 0; r < num_rows; r++)
            {
                slice[r] = init_data[r][c];
            }

            vector_puzzle_data[c] = new Vector(slice);
        }
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                s.append(vector_puzzle_data[c].get_value_at_index(r) + ", ");
            }

            s.append("\n");
        }

        return s.toString();
    }
}
