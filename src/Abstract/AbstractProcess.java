package Abstract;

import Main.PuzzleContainer;
import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;

/**
 * Created by sahil on 5/25/17.
 */

import java.util.Iterator;

public abstract class AbstractProcess {

    protected Square[][] standard_puzzle;
    protected IPuzzle row_puzzle;
    protected IPuzzle col_puzzle;
    protected IPuzzle block_puzzle;

    public AbstractProcess(PuzzleContainer pc)
    {
        this.standard_puzzle = pc.standard_puzzle;
        this.row_puzzle = pc.row_puzzle;
        this.col_puzzle = pc.col_puzzle;
        this.block_puzzle = pc.block_puzzle;
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

        row_puzzle.get_building_block(row).add_to_contained_values(new_value);
        col_puzzle.get_building_block(col).add_to_contained_values(new_value);

        int hl_row = row / 3;
        int hl_col = col / 3;
        int index = (hl_row)*3 + hl_col;
        block_puzzle.get_building_block(index).add_to_contained_values(new_value);

        quick_candidate_update(row, col, new_value);
        update_candidate_counts();
    }

    private void update_candidate_counts()
    {
        row_puzzle.update_candidate_counts();
        col_puzzle.update_candidate_counts();
        block_puzzle.update_candidate_counts();
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
     * Does NOT assign the value passed to [row, col]
     *
     * @param row
     * @param col
     */
    private void quick_candidate_update(int row, int col, int assigned_value)
    {
        Iterator<Square> iter;

        iter = row_puzzle.get_building_block(row).iterator();
        while (iter.hasNext())
            iter.next().remove_single_candidate(assigned_value);

        iter = col_puzzle.get_building_block(col).iterator();
        while (iter.hasNext())
            iter.next().remove_single_candidate(assigned_value);

        int hl_row = row / 3;
        int hl_col = col / 3;

        int index = (hl_row)*3 + hl_col;
        iter = block_puzzle.get_building_block(index).iterator();
        while (iter.hasNext())
            iter.next().remove_single_candidate(assigned_value);
    }

    /**
     * runs the process as defined in the subclass
     * @return true if a change was made, false otherwise
     */
    public boolean execute()
    {
        boolean process_change_made = false;

        while (run_process())
            process_change_made = true;

        return process_change_made;
    }

    public abstract boolean run_process();
}
