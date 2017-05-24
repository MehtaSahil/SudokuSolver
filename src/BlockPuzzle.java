/**
 * Created by sahil on 5/23/17.
 */

public class BlockPuzzle {

    private final int num_rows;
    private final int num_cols;
    private Block[][] block_puzzle_data;

    public BlockPuzzle(Square[][] init_data)
    {
        num_rows = init_data.length;
        num_cols = init_data[0].length;

        block_puzzle_data = new Block[3][3];
        init_block_puzzle_data(init_data);
    }

    private void init_block_puzzle_data(Square[][] init_data)
    {
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                int hl_row = r / 3;
                int hl_col = c / 3;
                int sub_row = r % 3;
                int sub_col = c % 3;

                if (block_puzzle_data[hl_row][hl_col] == null)
                {
                    block_puzzle_data[hl_row][hl_col] = new Block(hl_row, hl_col, new Square[3][3]);
                }
                Block temp = block_puzzle_data[hl_row][hl_col];

                Square[][] block_data = temp.get_block_data();
                block_data[sub_row][sub_col] = init_data[r][c];
            }
        }
    }

    public Block get_block(int sub_row, int sub_col)
    {
        return block_puzzle_data[sub_row][sub_col];
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (int r = 0; r < num_rows; r++)
        {
            for (int c = 0; c < num_cols; c++)
            {
                int hl_row = r / 3;
                int hl_col = c / 3;
                int sub_row = r % 3;
                int sub_col = c % 3;

                Block temp = block_puzzle_data[hl_row][hl_col];

                s.append(temp.get_value(sub_row, sub_col) + ", ");
            }

            s.append("\n");
        }

        return s.toString();
    }
}
