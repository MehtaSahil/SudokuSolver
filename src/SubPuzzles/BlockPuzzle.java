package SubPuzzles;

import PuzzlePieces.Block;
import PuzzlePieces.Square;

/**
 * Created by sahil on 5/23/17.
 */

import java.util.Iterator;

public class BlockPuzzle implements Iterable<Block> {

    private final int num_rows;
    private final int num_cols;
    private Block[][] block_puzzle_data;
    private final int data_size;

    public BlockPuzzle(Square[][] init_data)
    {
        num_rows = init_data.length;
        num_cols = init_data[0].length;

        block_puzzle_data = new Block[3][3];
        data_size = 3 * 3;

        init_block_puzzle_data(init_data);
    }

    /**
     * Creates blocks to represent input data
     * @param init_data input data
     */
    private void init_block_puzzle_data(Square[][] init_data)
    {
        for (int hl_row = 0; hl_row < 3; hl_row++)
        {
            for (int hl_col = 0; hl_col < 3; hl_col++)
            {
                Square[][] toFill = get_square_sub_matrix(hl_row, hl_col, init_data);
                block_puzzle_data[hl_row][hl_col] = new Block(hl_row, hl_col, toFill);
            }
        }
    }

    /**
     * @param hl_row the "high level" row index of the PuzzlePieces.Block to be created
     * @param hl_col the "high level" col index of the PuzzlePieces.Block to be created
     * @param init_data input data
     * @return Returns a sub-matrix representing a 3x3 section of the puzzle (i.e. a PuzzlePieces.Block)
     */
    private Square[][] get_square_sub_matrix(int hl_row, int hl_col, Square[][] init_data)
    {
        int start_row = hl_row * 3;
        int start_col = hl_col * 3;

        Square[][] toReturn = new Square[3][3];
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                toReturn[r][c] = init_data[start_row + r][start_col + c];
            }
        }

        return toReturn;
    }

    /**
     * @param hl_row "high level" row index
     * @param hl_col "high level" column index
     * @return Block at the specified location
     */
    public Block get_block(int hl_row, int hl_col)
    {
        return block_puzzle_data[hl_row][hl_col];
    }

    public void update_candidate_counts()
    {
        Iterator<Block> iter = iterator();
        while (iter.hasNext())
            iter.next().update_candidate_counts();
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

    /**
     * @return An iterator which runs from the top left corner to
     * the bottom right corner of block_puzzle_data[][]
     */
    public Iterator<Block> iterator()
    {
        return new BlockPuzzleIterator();
    }

    private class BlockPuzzleIterator implements Iterator<Block>
    {
        private int current_hl_row;
        private int current_hl_col;

        public BlockPuzzleIterator()
        {
            current_hl_row = 0;
            current_hl_col = 0;
        }

        @Override
        public boolean hasNext() {
            return ((3*current_hl_row)+current_hl_col) < data_size;
        }

        @Override
        public Block next() {
            Block toReturn = block_puzzle_data[current_hl_row][current_hl_col];
            if (current_hl_col + 1 >= 3)
            {
                current_hl_row++;
                current_hl_col = 0;
            }
            else
            {
                current_hl_col++;
            }

            return toReturn;
        }
    }
}
