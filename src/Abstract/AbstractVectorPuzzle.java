package Abstract;

import PuzzlePieces.Square;
import PuzzlePieces.Vector;

import java.util.Iterator;

/**
 * Created by sahil on 5/24/17.
 */
public abstract class AbstractVectorPuzzle implements Iterable<Vector> {
    protected final int num_rows;
    protected final int num_cols;
    protected Vector[] vector_puzzle_data;

    public AbstractVectorPuzzle(Square[][] init_data)
    {
        num_rows = init_data.length;
        num_cols = init_data[0].length;

        if (num_rows != num_cols || num_rows != 9)
            throw new IllegalArgumentException("SubPuzzles.RowPuzzle Constructor: init_data must be 9x9");

        vector_puzzle_data = new Vector[num_rows];
        init_puzzle_data(init_data);
    }

    protected abstract void init_puzzle_data(Square[][] init_data);

    /**
     * @param index
     * @return PuzzlePieces.Vector at [index] in vector_puzzle_data
     */
    public Vector get_vector(int index)
    {
        return vector_puzzle_data[index];
    }

    public Iterator<Vector> iterator()
    {
        return new AbstractVectorPuzzleIterator();
    }

    private class AbstractVectorPuzzleIterator implements Iterator<Vector>
    {
        private int current_index;

        @Override
        public boolean hasNext() {
            return current_index < vector_puzzle_data.length;
        }

        @Override
        public Vector next() {
            return vector_puzzle_data[current_index++];
        }
    }
}
