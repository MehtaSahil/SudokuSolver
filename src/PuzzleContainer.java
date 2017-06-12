import Abstract.AbstractVectorPuzzle;
import PuzzlePieces.Square;
import SubPuzzles.BlockPuzzle;
import SubPuzzles.ColPuzzle;
import SubPuzzles.RowPuzzle;

/**
 * Created by sahil on 6/11/17.
 */
public class PuzzleContainer {

    public Square[][] standard_puzzle;
    public AbstractVectorPuzzle row_puzzle;
    public AbstractVectorPuzzle col_puzzle;
    public BlockPuzzle block_puzzle;

    public PuzzleContainer(Square[][] init_data)
    {
        standard_puzzle = init_data;
        row_puzzle = new RowPuzzle(init_data);
        col_puzzle = new ColPuzzle(init_data);
        block_puzzle = new BlockPuzzle(init_data);
    }
}
