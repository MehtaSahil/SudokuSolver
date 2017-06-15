package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import Abstract.IBuildingBlock;
import Abstract.IPuzzle;
import Main.PuzzleContainer;
import PuzzlePieces.Block;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;
import SubPuzzles.BlockPuzzle;

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

        List<Map<Set<Integer>, Integer>> row_candidate_counts = get_vector_set_counts(row_puzzle);
        List<Map<Set<Integer>, Integer>> col_candidate_counts = get_vector_set_counts(col_puzzle);
        List<Map<Set<Integer>, Integer>> block_candidate_counts = get_block_set_counts(block_puzzle);

        change_made |= vector_detect_naked_twin(row_candidate_counts, row_puzzle);
        change_made |= vector_detect_naked_twin(col_candidate_counts, col_puzzle);
        change_made |= block_detect_naked_twin(block_candidate_counts, block_puzzle);

        return false;
    }

    private boolean vector_detect_naked_twin(List<Map<Set<Integer>, Integer>> vector_candidate_counts,
                                          IPuzzle puzzle)
    {
        boolean change_made = false;

        for (int row = 0; row < vector_candidate_counts.size(); row++)
        {
            Map<Set<Integer>, Integer> current_map = vector_candidate_counts.get(row);

            for (Set<Integer> pair : current_map.keySet())
            {
                if (current_map.get(pair) == 2)
                {
                    vector_remove_naked_twin_candidates(pair, puzzle.get_building_block(row));
                    change_made = true;
                }
            }
        }

        return change_made;
    }

    private boolean block_detect_naked_twin(List<Map<Set<Integer>, Integer>> block_candidate_counts,
                                          IPuzzle puzzle)
    {
        boolean change_made = false;

        for (int block_index = 0; block_index < block_candidate_counts.size(); block_index++)
        {
            Map<Set<Integer>, Integer> current_map = block_candidate_counts.get(block_index);

            for (Set<Integer> pair : current_map.keySet())
            {
                if (current_map.get(pair) == 2)
                {
                    block_remove_naked_twin_candidates(pair, puzzle.get_building_block(block_index));
                    change_made = true;
                }
            }
        }

        return change_made;
    }


    private void vector_remove_naked_twin_candidates(Set<Integer> pair, IBuildingBlock vector)
    {
        Iterator<Square> vector_iterator = vector.iterator();
        while (vector_iterator.hasNext())
        {
            Square current_square = vector_iterator.next();

            if (current_square.is_assigned() || current_square.get_candidates().equals(pair))
            {
                continue;
            }

            current_square.remove_candidates(pair);
        }
    }

    private void block_remove_naked_twin_candidates(Set<Integer> pair, IBuildingBlock block)
    {
        Iterator<Square> block_iterator = block.iterator();
        while (block_iterator.hasNext())
        {
            Square current_square = block_iterator.next();

            if (current_square.is_assigned() || current_square.get_candidates().equals(pair))
            {
                continue;
            }

            current_square.remove_candidates(pair);
        }
    }

    private List<Map<Set<Integer>, Integer>> get_vector_set_counts(IPuzzle puzzle)
    {
        List<Map<Set<Integer>, Integer>> toReturn = new ArrayList<Map<Set<Integer>, Integer>>();

        /* checking for exposed pairs on rows */
        Iterator<IBuildingBlock> row_iterator = puzzle.iterator();
        while (row_iterator.hasNext())
        {
            IBuildingBlock current_row = row_iterator.next();

            Map<Set<Integer>, Integer> set_counts = new HashMap<Set<Integer>, Integer>();

            /* Iterate through all Squares in current vector */
            Iterator<Square> square_iterator = current_row.iterator();
            while (square_iterator.hasNext())
            {
                Square current_square = square_iterator.next();
                Set<Integer> current_candidates = new HashSet<Integer>(current_square.get_candidates());

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

            toReturn.add(set_counts);
        }

        return toReturn;
    }

    private List<Map<Set<Integer>, Integer>> get_block_set_counts(IPuzzle puzzle)
    {
        List<Map<Set<Integer>, Integer>> toReturn = new ArrayList<Map<Set<Integer>, Integer>>();

        /* checking for exposed pairs on rows */
        Iterator<IBuildingBlock> block_iterator = puzzle.iterator();
        while (block_iterator.hasNext())
        {
            IBuildingBlock current_block = block_iterator.next();

            Map<Set<Integer>, Integer> set_counts = new HashMap<Set<Integer>, Integer>();

            /* Iterate through all Squares in current vector */
            Iterator<Square> square_iterator = current_block.iterator();
            while (square_iterator.hasNext())
            {
                Square current_square = square_iterator.next();
                Set<Integer> current_candidates = new HashSet<Integer>(current_square.get_candidates());

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

            toReturn.add(set_counts);
        }

        return toReturn;
    }
}
