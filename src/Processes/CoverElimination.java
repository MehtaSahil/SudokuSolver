package Processes;

import Abstract.AbstractProcess;
import Main.PuzzleContainer;

import java.util.*;

/**
 * Created by sahil on 6/17/17.
 */
public class CoverElimination extends AbstractProcess {

    private Set<String> combinations;

    public CoverElimination(PuzzleContainer pc)
    {
        super(pc);
        combinations = new HashSet<String>();
    }

    @Override
    public boolean run_process()
    {
        // for each row
        // for each col
        // for each block

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

        List<Integer> indices = new ArrayList<Integer>();
        indices.add(1);
        indices.add(2);
        indices.add(3);

        fill_combinations_for_indices(indices);
        System.out.println(combinations);

        return false;
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

        clean_combinations();
    }

    private void clean_combinations()
    {
        Iterator<String> comb_iterator = combinations.iterator();
        while (comb_iterator.hasNext())
        {
            String curr_str = comb_iterator.next();
            if (curr_str.length() == 0 || curr_str.length() == 1)
            {
                comb_iterator.remove();
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
        print(output, level);
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

    private void print(int[] input, int stop)
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < stop; i++)
        {
//            System.out.print(input[i] + " ");
            s.append(input[i]);
        }
//        System.out.println();

        combinations.add(s.toString());
    }
}
