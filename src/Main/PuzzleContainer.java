package Main;

import Abstract.AbstractVectorPuzzle;
import Abstract.IPuzzle;
import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;
import SubPuzzles.ColPuzzle;
import SubPuzzles.RowPuzzle;

/**
 * Created by sahil on 6/11/17.
 */
public class PuzzleContainer {

    public Square[][] standard_puzzle;
    public IPuzzle row_puzzle;
    public IPuzzle col_puzzle;
    public IPuzzle block_puzzle;

    public PuzzleContainer(Square[][] init_data)
    {
        standard_puzzle = init_data;
        row_puzzle = new RowPuzzle(init_data);
        col_puzzle = new ColPuzzle(init_data);
        block_puzzle = new BlockPuzzle(init_data);
    }

    public void set_data(Square[][] new_data)
    {
        standard_puzzle = new_data;
        row_puzzle = new RowPuzzle(new_data);
        col_puzzle = new ColPuzzle(new_data);
        block_puzzle = new BlockPuzzle(new_data);
    }

}
