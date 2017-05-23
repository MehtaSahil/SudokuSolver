/**
 * Created by sahil on 5/23/17.
 */

public class Block {

    /* "high level" row and col represents index of block in 3x3 matrix*/
    private final int hl_row;
    private final int hl_col;

    private Square[][] block_data;

    public Block(int hl_row, int hl_col, int[][] init_data)
    {
        this.hl_row = hl_row;
        this.hl_col = hl_col;

        /* check that init_data is a 3x3 matrix */
        if (init_data.length != 3 || init_data[0].length != 3)
            throw new IllegalArgumentException("Block Constructor: init_data must be 3x3");
        init_block_data(init_data);
    }

    private void init_block_data(int[][] init_data)
    {
        block_data = new Square[init_data.length][init_data[0].length];

        /* create Squares from init_data */
        for (int r = 0; r < block_data.length; r++)
        {
            for (int c = 0; c < block_data[0].length; c++)
            {
                block_data[r][c] = new Square(r, c, init_data[r][c]);
            }
        }
    }

    public int get_value_at_index(int sub_row, int sub_col)
    {
        return block_data[sub_row][sub_col].get_value();
    }
}
