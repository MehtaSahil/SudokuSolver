package main.java.process;

import main.java.abstraction.AbstractProcess;
import main.java.abstraction.IBuildingBlock;
import main.java.abstraction.IPuzzle;
import main.java.runner.PuzzleContainer;
import main.java.puzzlepiece.Square;

import java.util.*;

/**
 * Created by sahil on 6/17/17.
 */
public class CoverElimination extends AbstractProcess {

    private Set<Set<Integer>> combinations;

    public CoverElimination(PuzzleContainer pc)
    {
        super(pc);
        combinations = new HashSet<Set<Integer>>();
    }

    @Override
    public boolean run_process()
    {
        boolean change_made = false;

        List<IPuzzle> puzzles = new ArrayList<IPuzzle>();
        puzzles.add(row_puzzle);
        puzzles.add(col_puzzle);
        puzzles.add(block_puzzle);

        for (IPuzzle puzzle : puzzles)
            process_covers_for_IPuzzle(puzzle);

        /**
         * identify which indices are valid based on which spots are already filled
         * find all combinations of those indices
         * test all combinations for coverage
         *
         * if the union of the candidate sets of n blocks itself has n elements, then those n
         * blocks are said to "cover" those elements present in the union
         *
         * if a union is found, remove covered candidates from the appropriate row/col/block and mark change_made
         */

        return false;
    }

    /**
     * finds covering sets in the provided puzzle and eliminates candidates in affected sets
     * @param puzzle
     */
    private void process_covers_for_IPuzzle(IPuzzle puzzle)
    {
        Iterator<IBuildingBlock> building_block_iter = puzzle.iterator();
        while (building_block_iter.hasNext())
        {
            IBuildingBlock current = building_block_iter.next();

            List<Integer> valid_indices = new ArrayList<Integer>();
            for (int i = 0; i < 9; i++)
                valid_indices.add(i);

            /* remove indices that have already been assigned */
            int current_index = 0;
            Iterator<Square> iter = current.iterator();
            while (iter.hasNext())
            {
                Square current_square = iter.next();
                if (current_square.is_assigned())
                    valid_indices.remove(valid_indices.indexOf(current_index));

                current_index++;
            }

            fill_combinations_for_indices(valid_indices);

            /* go through all possible indices looking for a cover */
            for (Set<Integer> combination : combinations)
            {
                Set<Square> squares = get_squares_by_index_list(combination, current);
                Set<Integer> candidate_union = get_candidate_union(squares);

                if (combination.size() == candidate_union.size())
                {
                    /* remove covered candidates from rest of IBuildingBlock */
                    int temp_index = 0;
                    Iterator<Square> temp_iter = current.iterator();
                    while (temp_iter.hasNext())
                    {
                        Square temp_square = temp_iter.next();
                        if (combination.contains(temp_index++))
                            continue;

                        temp_square.remove_candidates(candidate_union);
                    }
                }
            }
        }
    }

    /**
     * Finds the union of the candidate sets of the provided squares
     * @param squares
     * @return the union of the candidate sets of the provided squares
     */
    private Set<Integer> get_candidate_union(Set<Square> squares) {

        Set<Integer> union = new HashSet<Integer>();

        for (Square s : squares) {
            union.addAll(s.get_candidates());
        }

        return union;
    }

    /**
     * Given a set of indices, get the squares in the building block at those indices
     * @param indices
     * @param building_block
     * @return the Squares in the building block at those indices
     */
    private Set<Square> get_squares_by_index_list(Set<Integer> indices, IBuildingBlock building_block)
    {
        return building_block.get_squares_by_index_list(indices);
    }

    /**
     * fills instance variable combinations with all combinations of the supplied indices
     * @param indices
     */
    private void fill_combinations_for_indices(List<Integer> indices)
    {
        combinations.clear();

        Integer[] input_indices = indices.toArray(new Integer[0]);
        Map<Integer, Integer> char_counts = new HashMap<Integer, Integer>();

        for (int i = 0; i < input_indices.length; i++)
        {
            int to_count = input_indices[i];
            if (char_counts.containsKey(to_count))
            {
                char_counts.put(to_count, char_counts.get(to_count) + 1);
            }
            else
            {
                char_counts.put(to_count, 1);
            }
        }

        int[] input = new int[char_counts.size()];
        int[] counts = new int[char_counts.size()];
        int[] output = new int[char_counts.size()];

        int spot = 0;
        for (Map.Entry<Integer, Integer> entry : char_counts.entrySet())
        {
            input[spot] = entry.getKey();
            counts[spot] = entry.getValue();
            spot++;
        }

        combination(input, counts, output, 0, 0);

        clean_combinations(indices.size());
    }

    /**
     * Removes unecessary entries in combinations (0, 1, and max length aren't useful to test)
     * @param max_length
     */
    private void clean_combinations(int max_length)
    {
        Iterator<Set<Integer>> combination_iter = combinations.iterator();
        while (combination_iter.hasNext())
        {
            Set<Integer> curr_str = combination_iter.next();
            if (curr_str.size() == 0 || curr_str.size() == 1 || curr_str.size() == max_length)
            {
                combination_iter.remove();
            }
        }
    }

    /**
     * Recursive method to generate all combinations of a String
     * given input (the unique characters) and counts (the respective counts of those characters)
     * @param input
     * @param counts
     * @param output
     * @param start_pos
     * @param level
     */
    private void combination(int[] input, int[] counts, int[] output, int start_pos, int level)
    {
        save_combination(output, level);
        for (int i = start_pos; i < input.length; i++)
        {
            if (counts[i] == 0)
                continue;

            output[level] = input[i];
            counts[i]--;
            combination(input, counts, output, i, level + 1);
            counts[i]++;
        }
    }

    private void save_combination(int[] input, int stop)
    {
        Set<Integer> to_add = new HashSet<Integer>();
        for (int i = 0; i < stop; i++)
            to_add.add(input[i]);

        combinations.add(to_add);
    }
}
