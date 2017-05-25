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

    public SoleCandidate(Square[][] standard_puzzle,
                         AbstractVectorPuzzle row_puzzle,
                         AbstractVectorPuzzle col_puzzle,
                         BlockPuzzle block_puzzle)
    {
        super(standard_puzzle, row_puzzle, col_puzzle, block_puzzle);
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
            execute_loop_trigger = standard_sole_candidate();
    }

    /**
     * Considers every spot in the puzzle for a sole candidate
     * updates the values of squares with sole candidates
     * @return true if a change was made, false otherwise
     */
    public boolean standard_sole_candidate()
    {
        boolean change_made = false;

        for (int r = 0; r < standard_puzzle.length; r++)
        {
            for (int c = 0; c < standard_puzzle[r].length; c++)
            {
                Square current = standard_puzzle[r][c];
                int current_row = current.get_row();
                int current_col = current.get_col();

                if (current.get_candidates().size() == 1)
                {
                    set_value(current_row, current_col, current.get_final_candidate());
                    change_made = true;
                }
            }
        }

        return change_made;
    }
}
