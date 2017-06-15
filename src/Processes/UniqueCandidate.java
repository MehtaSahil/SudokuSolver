package Processes;

import Abstract.AbstractProcess;
import Abstract.IBuildingBlock;
import Abstract.IPuzzle;
import Main.PuzzleContainer;
import PuzzlePieces.Square;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sahil on 5/25/17.
 */
public class UniqueCandidate extends AbstractProcess {

    public UniqueCandidate(PuzzleContainer pc) { super(pc); }
    /**
     * executes unique candidate on rows, cols, and blocks
     * @return true if a change was made, false otherwise
     */
    public boolean run_process()
    {
        boolean row_change = unique_candidate(row_puzzle);
        boolean col_change = unique_candidate(col_puzzle);
        boolean block_change = unique_candidate(block_puzzle);

        return row_change || col_change || block_change;
    }

    /**
     * performs unique candidate and makes changes on vector-based puzzles
     * @param puzzle
     * @return true if a change was made, false otherwise
     */
    private boolean unique_candidate(IPuzzle puzzle)
    {
        boolean change_made = false;
        Iterator<IBuildingBlock> building_block_iter = puzzle.iterator();

        /* for all vectors in the puzzle */
        while (building_block_iter.hasNext())
        {
            IBuildingBlock current_building_block = building_block_iter.next();
            Iterator<Square> square_iterator = current_building_block.iterator();

            /* for all squares in the vector */
            while (square_iterator.hasNext())
            {
                Square current_square = square_iterator.next();
                int current_row = current_square.get_row();
                int current_col = current_square.get_col();

                Set<Integer> candidates = current_square.get_candidates();

                /* temp_candidates exists to avoid concurrent modification exception on candidates */
                Set<Integer> temp_candidates = new HashSet<Integer>(candidates);

                /* test all candidates for uniqueness in vector */
                for (Integer candidate : temp_candidates)
                {
                    /* if the candidate is the only one of its kind */
                    if (current_building_block.get_candidate_counts().get(candidate) == 1)
                    {
                        set_value(current_row, current_col, candidate);
                        change_made = true;
                    }
                }
            }
        }

        return change_made;
    }
}
