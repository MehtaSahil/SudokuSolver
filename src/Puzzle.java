/**
 * Created by sahil on 5/23/17.
 */

public class Puzzle {

    private Square[][] standard_puzzle;
    private RowPuzzle row_puzzle;
    private ColPuzzle col_puzzle;
    private BlockPuzzle block_puzzle;

    public Puzzle(Square[][] init_data)
    {
        standard_puzzle = init_data;
        row_puzzle = new RowPuzzle(init_data);
        col_puzzle = new ColPuzzle(init_data);
        block_puzzle = new BlockPuzzle(init_data);
    }

    public void solve()
    {
        /* Solve the puzzle! (lots more work to be done) */
    }

    public String toString()
    {
        /* could return any of the puzzle types */
        return block_puzzle.toString();
    }
}
