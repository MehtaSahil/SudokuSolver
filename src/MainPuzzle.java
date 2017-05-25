import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;
import SubPuzzles.ColPuzzle;
import SubPuzzles.RowPuzzle;

import java.util.Set;

/**
 * Created by sahil on 5/23/17.
 */

public class MainPuzzle {

    private Square[][] standard_puzzle;
    private RowPuzzle row_puzzle;
    private ColPuzzle col_puzzle;
    private BlockPuzzle block_puzzle;

    private int num_rows;
    private int num_cols;

    public MainPuzzle(Square[][] init_data)
    {
        standard_puzzle = init_data;
        row_puzzle = new RowPuzzle(init_data);
        col_puzzle = new ColPuzzle(init_data);
        block_puzzle = new BlockPuzzle(init_data);

        num_rows = init_data.length;
        num_cols = init_data[0].length;

        update_candidates();
    }

    public void solve()
    {
        /* Solve the puzzle! (lots more work to be done) */
    }

    /**
     * Goes through every PuzzlePieces.Square and updates its availabe candidates
     * based on filled values in Row/Col/PuzzlePieces.Block Puzzles
     */
    public void update_candidates()
    {
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                Square temp = standard_puzzle[r][c];
                Set<Integer> candidates = temp.get_candidates();

                Set<Integer> vals_to_remove = row_puzzle.get_vector(r).get_contained_values();
                candidates.removeAll(vals_to_remove);

                vals_to_remove = col_puzzle.get_vector(c).get_contained_values();
                candidates.removeAll(vals_to_remove);

                vals_to_remove = block_puzzle.get_block(r / 3, c / 3).get_contained_values();
                candidates.removeAll(vals_to_remove);
            }
        }
    }

    /**
     * Sets value at specified location in puzzle
     * Manages candidate updates automatically
     * Manages Vector contained_value updates automatically
     *
     * @param row row index of Square at which a value will be set
     * @param col col index of Square at which a value will be set
     */
    public void set_value(int row, int col, int new_value)
    {
        Square toSet = standard_puzzle[row][col];
        toSet.set_value(new_value);

        row_puzzle.get_vector(row).add_to_contained_values(new_value);
        col_puzzle.get_vector(col).add_to_contained_values(new_value);
        block_puzzle.get_block(row / 3, col / 3).add_to_contained_values(new_value);

        update_candidates();
    }

    public String toString()
    {
        /* could return any of the puzzle types */
        return col_puzzle.toString();
    }
}
