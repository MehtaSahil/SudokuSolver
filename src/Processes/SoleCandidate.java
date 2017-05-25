package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import PuzzlePieces.Block;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;
import SubPuzzles.BlockPuzzle;
import SubPuzzles.ColPuzzle;
import SubPuzzles.RowPuzzle;

/**
 * Created by sahil on 5/25/17.
 */

import java.util.Iterator;

public class SoleCandidate extends AbstractProcess {

    public SoleCandidate(Square[][] standard_board,
                         AbstractVectorPuzzle row_puzzle,
                         AbstractVectorPuzzle col_puzzle,
                         BlockPuzzle block_puzzle)
    {
        super(standard_board, row_puzzle, col_puzzle, block_puzzle);
    }

    /**
     * Will run sole candidate until it cannot achieve any more progress
     * At this point either the puzzle is solved or another method should be attempted
     * after another method is attempted, sole candidate can be rune once again
     */
    public void execute()
    {
        boolean execute_loop_trigger = true;
        while (execute_loop_trigger)
        {
            boolean row_change = vector_sole_candidate(row_puzzle);
            boolean col_change = vector_sole_candidate(col_puzzle);
            boolean block_change = block_sole_candidate(block_puzzle);

            execute_loop_trigger = row_change || col_change || block_change;
        }
    }

    /**
     * @param puzzle AbstractVectorPuzzle to check for sole candidates
     * @return true if a change was made, false otherwise
     */
    public boolean vector_sole_candidate(AbstractVectorPuzzle puzzle)
    {
        Iterator<Vector> vector_iter;
        Iterator<Square> square_iter;
        boolean change_made = false;

        /**
         * Iterate through all Vectors in puzzle
         * check all Squares in each Vector for sole candidates
         * update values if there was a sole candidate
         */
        vector_iter = puzzle.iterator();
        while (vector_iter.hasNext())
        {
            square_iter = vector_iter.next().iterator();
            while (square_iter.hasNext())
            {
                Square current = square_iter.next();
                int current_row = current.get_row();
                int current_col = current.get_col();

                if (current.get_candidates().size() == 1)
                {
                    set_value(current_row, current_col, current.get_final_candidate());
                }
            }
        }

        return change_made;
    }

    public boolean block_sole_candidate(BlockPuzzle puzzle)
    {
        Iterator<Block> block_iter;
        Iterator<Square> square_iter;
        boolean change_made = false;

        /**
         * Iterate through all Vectors in puzzle
         * check all Squares in each Vector for sole candidates
         * update values if there was a sole candidate
         */
        block_iter = puzzle.iterator();
        while (block_iter.hasNext())
        {
            square_iter = block_iter.next().iterator();
            while (square_iter.hasNext())
            {
                Square current = square_iter.next();
                int current_row = current.get_row();
                int current_col = current.get_col();

                if (current.get_candidates().size() == 1)
                {
                    set_value(current_row, current_col, current.get_final_candidate());
                }
            }
        }

        return change_made;
    }
}
