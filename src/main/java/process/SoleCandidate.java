package main.java.process;

import main.java.abstraction.AbstractProcess;
import main.java.runner.PuzzleContainer;
import main.java.puzzlepiece.Square;

/**
 * Created by sahil on 5/25/17.
 */

public class SoleCandidate extends AbstractProcess {

    public SoleCandidate(PuzzleContainer pc) { super(pc); }

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

                if (current.has_one_candidate())
                {
                    set_value(current_row, current_col, current.get_final_candidate());
                    change_made = true;
                }
            }
        }

        return change_made;
    }
}
