package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;
import SubPuzzles.BlockPuzzle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sahil on 6/11/17.
 */

public class NakedTwin extends AbstractProcess {

    public NakedTwin(Square[][] standard_puzzle,
                     AbstractVectorPuzzle row_puzzle,
                     AbstractVectorPuzzle col_puzzle,
                     BlockPuzzle block_puzzle)
    {
        super(standard_puzzle, row_puzzle, col_puzzle, block_puzzle);
    }

    /**
     * Considers each block as an exposed pairing of candidates
     * If there is an exposed pairing, remove that pair from the candidate sets
     * in affected blocks (row, col, block)
     * @return
     */
    public boolean run_process()
    {
        boolean change_made = false;

        /* checking for exposed pairs on rows */
        Iterator<Vector> row_iterator = row_puzzle.iterator();
        while (row_iterator.hasNext())
        {
            System.out.println("new row");

            Vector current_row = row_iterator.next();

            Set<Set<Integer>> seen_candidate_sets = new HashSet<Set<Integer>>();

            /* Iterate through all Squares in current vector */
            Iterator<Square> square_iterator = current_row.iterator();
            while (square_iterator.hasNext())
            {
                Square current_square = square_iterator.next();
                Set<Integer> current_candidates = current_square.get_candidates();

                /* If we're not considering a PAIR, move on */
                if (current_square.is_assigned() || current_candidates.size() != 2)
                {
                    continue;
                }

                System.out.println("Found a pair!");
                System.out.println(current_candidates);

//                if (seen_candidate_sets.contains(current_candidates))
//                {
//                    /* do removal of candidates */
//                    /* remove current_candidates from seen_candidate_sets */
//                    adjust_for_pair_row(current_candidates, current_row);
//                    seen_candidate_sets.remove(current_candidates);
//                    change_made = true;
//                }
//                else
//                {
//                    /* add current_candidates to seen_candiate_sets for future comparison */
//                    seen_candidate_sets.add(current_candidates);
//                }
            }
        }

        /* checking for exposed pairs in columns */

        /* checking for exposed pairs in blocks */

        return change_made;
    }

    /**
     * Removes the candidates found in pair_candidates from all Squares in the specified
     * row EXCEPT for those that contain the found pair
     * @param pair_candidates
     * @param row
     */
    private void adjust_for_pair_row(Set<Integer> pair_candidates, Vector row)
    {
        Iterator<Square> iter = row.iterator();
        while (iter.hasNext())
        {
            Square current_square = iter.next();

            /* don't remove pair candidates from the Squares that contain the actual pair */
            if (current_square.get_candidates().equals(pair_candidates))
                continue;

            current_square.remove_candidates(pair_candidates);
        }
    }
}
