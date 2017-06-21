package Main;

import Abstract.AbstractProcess;
import Abstract.IBuildingBlock;
import Processes.*;
import PuzzlePieces.Square;
import PuzzlePieces.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by sahil on 5/23/17.
 */

public class Solver {

    /**
     * Different representations of the input data
     * These types all point to the same underlying set of Square objects,
     *      so changing data in one will change data in the others
     *      In other words, they do NOT require extra maintenance
     */
    private PuzzleContainer pc;

    private int num_rows;
    private int num_cols;

    public Solver(Square[][] init_data)
    {
        pc = new PuzzleContainer(init_data);

        num_rows = init_data.length;
        num_cols = init_data[0].length;

        init_candidates();
        init_candidate_counts();
    }

    public void solve()
    {
        /**
         * Plan to have a list of AbstractProcesses through which we can just call .execute()
         * This should lead to a really clean solving loop when more Processes come online
         */

        /* ALWAYS SIMPLIFY BEFORE SOLVING (known issue) */
        List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
        processes.add(new NakedTwin(pc));
        processes.add(new CoverElimination(pc));
        processes.add(new SoleCandidate(pc));
        processes.add(new UniqueCandidate(pc));

        /* continue executing until none of the available processes can make a change */
        while (true)
        {
            System.out.println("new process runthrough");

            boolean curr_iter_change_made = false;

            /* will set curr_iter_change_made to true if a change was made */
            for (AbstractProcess proc : processes)
                curr_iter_change_made |= proc.execute();

            /* if a change was not made after running all of the processes, stop */
            if (!curr_iter_change_made)
                break;

            /* stop trying to solve if puzzle is already solved */
            if (is_puzzle_solved())
                break;
        }

        /* only as a last resort do we want to brute force a puzzle */
        AbstractProcess brute_force = new BruteForce(pc);
        brute_force.execute();
    }

    /**
     * Goes through every PuzzlePieces.Square and updates its availabe candidates
     * based on filled values in Row/Col/PuzzlePieces.Block Puzzles
     */
    private void init_candidates()
    {
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                Square curr = pc.standard_puzzle[r][c];

                Set<Integer> vals_to_remove = pc.row_puzzle.get_building_block(r).get_contained_values();
                curr.remove_candidates(vals_to_remove);

                vals_to_remove = pc.col_puzzle.get_building_block(c).get_contained_values();
                curr.remove_candidates(vals_to_remove);


                int hl_row = r / 3;
                int hl_col = c / 3;

                int index = (hl_row)*3 + hl_col;
                vals_to_remove = pc.block_puzzle.get_building_block(index).get_contained_values();
                curr.remove_candidates(vals_to_remove);
            }
        }
    }

    private void init_candidate_counts()
    {
        pc.row_puzzle.update_candidate_counts();
        pc.col_puzzle.update_candidate_counts();
        pc.block_puzzle.update_candidate_counts();
    }

    /* true if the puzzle has been solved, false otherwise */
    public boolean is_puzzle_solved()
    {
        /* go through all Squares in puzzle */
        Iterator<IBuildingBlock> building_block_iter = pc.row_puzzle.iterator();
        while (building_block_iter.hasNext())
        {
            Iterator<Square> square_iter = building_block_iter.next().iterator();
            while (square_iter.hasNext())
            {
                if (!square_iter.next().is_assigned())
                    return false;
            }
        }

        return true;
    }

    public String toString()
    {
        /* could use any of the puzzle types */
        return pc.block_puzzle.toString();
    }
}
