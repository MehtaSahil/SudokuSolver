import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import Processes.SoleCandidate;
import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;
import SubPuzzles.ColPuzzle;
import SubPuzzles.RowPuzzle;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by sahil on 5/23/17.
 */

public class MainPuzzle {

    /**
     * Different representations of the input data
     * These types all point to the same underlying set of Square objects,
     *      so changing data in one will change data in the others
     *      In other words, they do NOT require extra maintenance
     */
    private Square[][] standard_puzzle;
    private AbstractVectorPuzzle row_puzzle;
    private AbstractVectorPuzzle col_puzzle;
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

        init_candidates();
        init_candidate_counts();
    }

    public void solve()
    {
        /**
         * Plan to have a list of AbstractProcesses through which we can just call .execute()
         * This should lead to a really clean solving loop when more Processes come online
         */

        AbstractProcess sole_candidate = new SoleCandidate(standard_puzzle, row_puzzle, col_puzzle, block_puzzle);
        sole_candidate.execute();
    }

    /**
     * Goes through every PuzzlePieces.Square and updates its availabe candidates
     * based on filled values in Row/Col/PuzzlePieces.Block Puzzles
     */
    private void init_candidates()
    {
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                Square curr = standard_puzzle[r][c];

                Set<Integer> vals_to_remove = row_puzzle.get_vector(r).get_contained_values();
                curr.remove_candidates(vals_to_remove);

                vals_to_remove = col_puzzle.get_vector(c).get_contained_values();
                curr.remove_candidates(vals_to_remove);

                vals_to_remove = block_puzzle.get_block(r / 3, c / 3).get_contained_values();
                curr.remove_candidates(vals_to_remove);
            }
        }
    }

    private void init_candidate_counts()
    {
        row_puzzle.update_candidate_counts();
        col_puzzle.update_candidate_counts();
        block_puzzle.update_candidate_counts();
    }

    public String toString()
    {
        /* could use any of the puzzle types */
        return block_puzzle.toString();
    }
}
