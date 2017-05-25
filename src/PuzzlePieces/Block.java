package PuzzlePieces; /**
 * Created by sahil on 5/23/17.
 */

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class Block implements Iterable<Square> {

    /* "high level" row and col represents index of block in 3x3 matrix*/
    private final int hl_row;
    private final int hl_col;
    private final int num_rows;
    private final int num_cols;

    private Square[][] block_data;
    private final int data_size;

    private Set<Integer> contained_values;

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
            throw new IllegalArgumentException("PuzzlePieces.Block Constructor: init_data must be 3x3");

        block_data = init_data;
        num_rows = block_data.length;
        num_cols = block_data[0].length;

        data_size = num_rows * num_cols;

        contained_values = new HashSet<Integer>();
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

    public void add_to_contained_values(int to_add)
    {
        contained_values.add(to_add);
    }

    public Set<Integer> get_contained_values()
    {
        return contained_values;
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
