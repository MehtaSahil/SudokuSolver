/**
 * Created by sahil on 5/23/17.
 */

import java.util.Set;
import java.util.HashSet;

public class Block {

    /* "high level" row and col represents index of block in 3x3 matrix*/
    private final int hl_row;
    private final int hl_col;

    private Square[][] block_data;
    private Set<Integer> contained_values;

    public Block(int hl_row, int hl_col, Square[][] init_data)
    {
        this.hl_row = hl_row;
        this.hl_col = hl_col;

        /* check that init_data is a 3x3 matrix */
        if (init_data.length != 3 || init_data[0].length != 3)
            throw new IllegalArgumentException("Block Constructor: init_data must be 3x3");

        block_data = init_data;
        contained_values = new HashSet<Integer>();
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                contained_values.add(block_data[r][c].get_value());
            }
        }
    }

    public int get_value(int sub_row, int sub_col)
    {
        return block_data[sub_row][sub_col].get_value();
    }

    public Square[][] get_block_data()
    {
        return block_data;
    }

    public Set<Integer> get_contained_values()
    {
        return contained_values;
    }

    public String toString()
    {
        return "";
    }
}
