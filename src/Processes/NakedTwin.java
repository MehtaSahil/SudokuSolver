package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import Main.PuzzleContainer;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;

import java.util.*;

/**
 * Created by sahil on 6/11/17.
 */

public class NakedTwin extends AbstractProcess {

    public NakedTwin(PuzzleContainer pc) { super(pc); }

    /**
     * Considers each block as an exposed pairing of candidates
     * If there is an exposed pairing, remove that pair from the candidate sets
     * in affected blocks (row, col, block)
     * @return
     */
    public boolean run_process()
    {
        boolean change_made = false;

//        /* checking for exposed pairs on rows */
//        Iterator<Vector> row_iterator = col_puzzle.iterator();
//        while (row_iterator.hasNext())
//        {
//            System.out.println("new row");
//            Vector current_row = row_iterator.next();
//
//            Map<Set<Integer>, Integer> set_counts = new HashMap<Set<Integer>, Integer>();
//
//            /* Iterate through all Squares in current vector */
//            Iterator<Square> square_iterator = current_row.iterator();
//            while (square_iterator.hasNext())
//            {
//                Square current_square = square_iterator.next();
//                Set<Integer> current_candidates = current_square.get_candidates();
//
//                /* If we're not considering a PAIR, move on */
//                if (current_square.is_assigned() || current_candidates.size() != 2)
//                {
//                    continue;
//                }
//
//                if (!set_counts.containsKey(current_candidates))
//                {
//                    set_counts.put(current_candidates, 1);
//                }
//                else
//                {
//                    set_counts.put(current_candidates, set_counts.get(current_candidates) + 1);
//                }
//            }
//
//            System.out.println(set_counts);
//        }

        System.out.println(get_set_counts(row_puzzle));
        System.out.println(get_set_counts(col_puzzle));

        /* checking for exposed pairs in columns */

        /* checking for exposed pairs in blocks */

        return change_made;
    }

    public List<Map<Set<Integer>, Integer>> get_set_counts(AbstractVectorPuzzle puzzle)
    {
        List<Map<Set<Integer>, Integer>> toReturn = new ArrayList<Map<Set<Integer>, Integer>>();

        /* checking for exposed pairs on rows */
        Iterator<Vector> row_iterator = puzzle.iterator();
        while (row_iterator.hasNext())
        {
            Vector current_row = row_iterator.next();

            Map<Set<Integer>, Integer> set_counts = new HashMap<Set<Integer>, Integer>();

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

                if (!set_counts.containsKey(current_candidates))
                {
                    set_counts.put(current_candidates, 1);
                }
                else
                {
                    set_counts.put(current_candidates, set_counts.get(current_candidates) + 1);
                }
            }

            // System.out.println(set_counts);
            toReturn.add(set_counts);
        }

        return toReturn;
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
