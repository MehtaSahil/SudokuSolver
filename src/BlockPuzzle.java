/**
 * Created by sahil on 5/23/17.
 */

public class BlockPuzzle {

    private Block[][] block_puzzle;

    public BlockPuzzle(int[][] init_data)
    {
        /* check that init_data is 9x9 matrix (standard) */
        if (init_data.length != 9 || init_data[0].length != 9)
            throw new IllegalArgumentException("BlockPuzzle Constructor: init_data must be 9x9");
        init_puzzle(init_data);
    }

    private void init_puzzle(int[][] init_data)
    {
        block_puzzle = new Block[init_data.length/3][init_data[0].length/3];
    }

    private void get_sub_matrices(int[][] init_data)
    {

    }
}
