package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;

/**
 * Created by sahil on 5/25/17.
 */

public class SoleCandidate extends AbstractProcess {

    public SoleCandidate(Square[][] standard_puzzle,
                         AbstractVectorPuzzle row_puzzle,
                         AbstractVectorPuzzle col_puzzle,
                         BlockPuzzle block_puzzle)
    {
        super(standard_puzzle, row_puzzle, col_puzzle, block_puzzle);
    }

    /**
     * Considers every spot in the puzzle for a sole candidate
     * updates the values of squares with sole candidates
     * @return true if a change was made, false otherwise
     */
    public boolean run_process()
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
