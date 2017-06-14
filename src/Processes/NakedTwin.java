package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
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

        for (int row = 0; row < row_candidate_counts.size(); row++)
        {
            Map<Set<Integer>, Integer> current_map = row_candidate_counts.get(row);

            for (Set<Integer> pair : current_map.keySet())
            {
                if (current_map.get(pair) == 2)
                {
                    System.out.println("naked twin: " + pair + "on row : " + row);
                    vector_remove_naked_twin_candidates(pair, row_puzzle.get_vector(row));
                }
            }
        }

        for (int col = 0; col < col_candidate_counts.size(); col++)
        {
            Map<Set<Integer>, Integer> current_map = col_candidate_counts.get(col);

            for (Set<Integer> pair : current_map.keySet())
            {
                if (current_map.get(pair) == 2)
                {
                    System.out.println("naked twin: " + pair + "on col : " + col);
                    vector_remove_naked_twin_candidates(pair, col_puzzle.get_vector(col));
                }
            }
        }

        for (int block = 0; block < block_candidate_counts.size(); block++)
        {
            Map<Set<Integer>, Integer> current_map = block_candidate_counts.get(block);

            for (Set<Integer> pair : current_map.keySet())
            {
                if (current_map.get(pair) == 2)
                {
                    System.out.println("naked twin: " + pair + "on block : " + block);
                    block_remove_naked_twin_candidates(pair, block_puzzle.get_block(block/3, block%3));
                }
            }
        }

        return change_made;
    }

    private void vector_remove_naked_twin_candidates(Set<Integer> pair, Vector vector)
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

    private void block_remove_naked_twin_candidates(Set<Integer> pair, Block block)
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

    private List<Map<Set<Integer>, Integer>> get_vector_set_counts(AbstractVectorPuzzle puzzle)
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

    private List<Map<Set<Integer>, Integer>> get_block_set_counts(BlockPuzzle puzzle)
    {
        List<Map<Set<Integer>, Integer>> toReturn = new ArrayList<Map<Set<Integer>, Integer>>();

        /* checking for exposed pairs on rows */
        Iterator<Block> block_iterator = puzzle.iterator();
        while (block_iterator.hasNext())
        {
            Block current_block = block_iterator.next();

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
