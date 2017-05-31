package Processes;

import Abstract.AbstractProcess;
import Abstract.AbstractVectorPuzzle;
import PuzzlePieces.Block;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;
import SubPuzzles.BlockPuzzle;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by sahil on 5/25/17.
 */
public class UniqueCandidate extends AbstractProcess {

    public UniqueCandidate(Square[][] standard_puzzle,
                         AbstractVectorPuzzle row_puzzle,
                         AbstractVectorPuzzle col_puzzle,
                         BlockPuzzle block_puzzle)
    {
        super(standard_puzzle, row_puzzle, col_puzzle, block_puzzle);
    }

    /**
     * executes unique candidate on rows, cols, and blocks
     * @return true if a change was made, false otherwise
     */
    public boolean run_process()
    {
        boolean row_change = vector_unique_candidate(row_puzzle);
        boolean col_change = vector_unique_candidate(col_puzzle);
        boolean block_change = block_unique_candidate();

        return row_change || col_change || block_change;
    }

    /**
     * performs unique candidate and makes changes on vector-based puzzles
     * @param puzzle
     * @return true if a change was made, false otherwise
     */
    private boolean vector_unique_candidate(AbstractVectorPuzzle puzzle)
    {
        boolean change_made = false;
        Iterator<Vector> vector_iterator = puzzle.iterator();

        /* for all vectors in the puzzle */
        while (vector_iterator.hasNext())
        {
            Vector current_vector = vector_iterator.next();
            Iterator<Square> square_iterator = current_vector.iterator();

            /* for all squares in the vector */
            while (square_iterator.hasNext())
            {
                Square current_square = square_iterator.next();
                int current_row = current_square.get_row();
                int current_col = current_square.get_col();

                Set<Integer> candidates = current_square.get_candidates();
                Iterator<Integer> temp_iter = candidates.iterator();

                Integer[] temp_candidates = new Integer[candidates.size()];
                int spot = 0;
                while (temp_iter.hasNext())
                    temp_candidates[spot++] = temp_iter.next();

                /* test all candidates for uniqueness in vector */
                for (Integer candidate : temp_candidates)
                {
                    /* if the candidate is the only one of its kind */
                    if (current_vector.get_candidate_counts().get(candidate) == 1)
                    {
                        set_value(current_row, current_col, candidate);
                        change_made = true;
                    }
                }
            }
        }

        return change_made;
    }

    private boolean block_unique_candidate()
    {
        boolean change_made = false;
        Iterator<Block> vector_iterator = block_puzzle.iterator();

        /* for all blocks in the puzzle */
        while (vector_iterator.hasNext())
        {
            Block current_block = vector_iterator.next();
            Iterator<Square> square_iterator = current_block.iterator();

            /* for all squares in the block */
            while (square_iterator.hasNext())
            {
                Square current_square = square_iterator.next();
                int current_row = current_square.get_row();
                int current_col = current_square.get_col();

                /* test all candidates for uniqueness in block */
                Set<Integer> candidates = current_square.get_candidates();
                Iterator<Integer> temp_iter = candidates.iterator();

                Integer[] temp_candidates = new Integer[candidates.size()];
                int spot = 0;
                while (temp_iter.hasNext())
                    temp_candidates[spot++] = temp_iter.next();

                for (Integer candidate : temp_candidates)
                {
                    /* if the candidate is the only one of its kind */
                    if (current_block.get_candidate_counts().get(candidate) == 1)
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
