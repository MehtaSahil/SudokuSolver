package main.java.puzzlepiece; /**
 * Created by sahil on 5/23/17.
 */

import main.java.abstraction.IBuildingBlock;

import java.util.*;

public class Block implements IBuildingBlock, Iterable<Square> {

    /* "high level" row and col represents index of block in 3x3 matrix*/
    private final int hl_row;
    private final int hl_col;
    private final int num_rows;
    private final int num_cols;

    private Square[][] block_data;
    private final int data_size;

    private Set<Integer> contained_values;
    private Map<Integer, Integer> candidate_counts;

    /**
     * @param hl_row "high level" row index
     * @param hl_col "high level" column index
     * @param init_data subset of main input data that represents data for THIS BLOCK ONLY (3x3)
     */
    public Block(int hl_row, int hl_col, Square[][] init_data)
    {
        this.hl_row = hl_row;
        this.hl_col = hl_col;

        /* check that init_data is a 3x3 matrix */
        if (init_data.length != 3 || init_data[0].length != 3)
            throw new IllegalArgumentException("main.java.puzzlepiece.Block Constructor: init_data must be 3x3");

        block_data = init_data;
        num_rows = block_data.length;
        num_cols = block_data[0].length;

        data_size = num_rows * num_cols;

        contained_values = new HashSet<Integer>();
        candidate_counts = new HashMap<Integer, Integer>();

        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                int to_add = block_data[r][c].get_value();
                if (to_add == 0)
                    continue;

                contained_values.add(to_add);
            }
        }
    }

    /**
     * @param sub_row row index within block
     * @param sub_col column index within block
     * @return integer value of Square at specified location within block
     */
    public int get_value(int sub_row, int sub_col)
    {
        return block_data[sub_row][sub_col].get_value();
    }

    public int get_value_at_index(int index) { return get_square_at_index(index).get_value(); }

    public Square get_square_at_index(int index) { return block_data[index/3][index%3]; }

    public void add_to_contained_values(int to_add)
    {
        contained_values.add(to_add);
    }

    public Set<Integer> get_contained_values()
    {
        return contained_values;
    }

    public boolean contains_value(int to_find) {return get_contained_values().contains(to_find); }

    public Map<Integer, Integer> get_candidate_counts()
    {
        return candidate_counts;
    }

    public void update_candidate_counts()
    {
        candidate_counts.clear();
        Iterator<Square> iter = iterator();
        while (iter.hasNext())
        {
            /* dealing with candidates within each Square */
            Set<Integer> candidates = iter.next().get_candidates();
            for (Integer num : candidates)
            {
                if (candidate_counts.containsKey(num))
                {
                    int new_value = candidate_counts.get(num) + 1;
                    candidate_counts.put(num, new_value);
                }
                else
                {
                    candidate_counts.put(num, 1);
                }
            }
        }
    }

    public Set<Square> get_squares_by_index_list(Set<Integer> indices)
    {
        Set<Square> to_return = new HashSet<Square>();
        for (Integer index : indices)
            to_return.add(get_square_at_index(index));

        return to_return;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        Iterator<Square> iter = iterator();
        while (iter.hasNext())
            s.append(iter.next().get_value() + ", ");

        return s.toString();
    }

    /**
     * @return Iterator of Squares which runs from the top left to the bottom right corner
     */
    public Iterator<Square> iterator()
    {
        return new BlockIterator();
    }

    private class BlockIterator implements Iterator<Square>
    {
        private int current_row;
        private int current_col;

        public BlockIterator()
        {
            current_row = 0;
            current_col = 0;
        }

        @Override
        public boolean hasNext() {
            return ((3*current_row)+current_col) < data_size;
        }

        @Override
        public Square next() {
            Square toReturn = block_data[current_row][current_col];
            if (current_col + 1 >= 3)
            {
                current_row++;
                current_col = 0;
            }
            else
            {
                current_col++;
            }

            return toReturn;
        }
    }
}
