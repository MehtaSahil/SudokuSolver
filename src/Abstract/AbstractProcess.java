package Abstract;

import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;
import SubPuzzles.ColPuzzle;
import SubPuzzles.RowPuzzle;

/**
 * Created by sahil on 5/25/17.
 */

import java.util.Iterator;

public abstract class AbstractProcess {

    private Square[][] standard_puzzle;
    private RowPuzzle row_puzzle;
    private ColPuzzle col_puzzle;
    private BlockPuzzle block_puzzle;

    public AbstractProcess(Square[][] standard_puzzle,
                           RowPuzzle row_puzzle,
                           ColPuzzle col_puzzle,
                           BlockPuzzle block_puzzle)
    {
        this.standard_puzzle = standard_puzzle;
        this.row_puzzle = row_puzzle;
        this.col_puzzle = col_puzzle;
        this.block_puzzle = block_puzzle;
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

        quick_candidate_update(row, col, new_value);
    }

    /**
     * Only updates the row, column, and block that would be affected by
     * a value assignment at [row, col]. This means that not every spot
     * on the puzzle needs to be touched because some of them are guaranteed
     * to not have been affected.
     *
     * e.g. updating [4, 7] affects candidates on row 4, col 7, and block [1, 2]
     *      but nowhere else (so dont touch anything you don't have to)
     *
     * @param row
     * @param col
     */
    private void quick_candidate_update(int row, int col, int assigned_value)
    {
        Iterator<Square> iter;

        iter = row_puzzle.get_vector(row).iterator();
        while (iter.hasNext())
            iter.next().remove_single_candidate(assigned_value);

        iter = col_puzzle.get_vector(col).iterator();
        while (iter.hasNext())
            iter.next().remove_single_candidate(assigned_value);

        iter = block_puzzle.get_block(row / 3, col / 3).iterator();
        while (iter.hasNext())
            iter.next().remove_single_candidate(assigned_value);
    }

    public abstract void execute();
}
