package SubPuzzles;

import Abstract.AbstractVectorPuzzle;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;

/**
 * Created by sahil on 5/23/17.
 */

public class RowPuzzle extends AbstractVectorPuzzle {

    public RowPuzzle(Square[][] init_data)
    {
        super(init_data);
        init_puzzle_data(init_data);
    }

    protected void init_puzzle_data(Square[][] init_data)
    {
        /* create a new PuzzlePieces.Vector for each row in init_data */
        for (int r = 0; r < num_rows; r++)
        {
            Square[] slice = new Square[num_cols];
            for (int c = 0; c < num_cols; c++)
            {
                slice[c] = init_data[r][c];
            }

            vector_puzzle_data[r] = new Vector(slice);
        }
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                s.append(vector_puzzle_data[r].get_value(c) + ", ");
            }

            s.append("\n");
        }

        return s.toString();
    }
}