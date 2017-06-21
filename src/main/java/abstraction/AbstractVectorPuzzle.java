package main.java.abstraction;

import main.java.puzzlepiece.Square;
import main.java.puzzlepiece.Vector;

import java.util.Iterator;

/**
 * Created by sahil on 5/24/17.
 */
public abstract class AbstractVectorPuzzle implements IPuzzle, Iterable<IBuildingBlock> {

    protected final int num_rows;
    protected final int num_cols;
    protected Vector[] vector_puzzle_data;

    public AbstractVectorPuzzle(Square[][] init_data)
    {
        num_rows = init_data.length;
        num_cols = init_data[0].length;

        if (num_rows != num_cols || num_rows != 9)
            throw new IllegalArgumentException("main.java.subpuzzle.RowPuzzle Constructor: init_data must be 9x9");

        vector_puzzle_data = new Vector[num_rows];
        init_puzzle_data(init_data);
    }

    public abstract void init_puzzle_data(Square[][] init_data);

    public void update_candidate_counts()
    {
        Iterator<IBuildingBlock> iter = iterator();
        while (iter.hasNext())
            iter.next().update_candidate_counts();
    }

    /**
     * @param index
     * @return main.java.puzzlepiece.Vector at [index] in vector_puzzle_data
     */
    public IBuildingBlock get_building_block(int index)
    {
        return vector_puzzle_data[index];
    }

    public Iterator<IBuildingBlock> iterator()
    {
        return new AbstractVectorPuzzleIterator();
    }

    private class AbstractVectorPuzzleIterator implements Iterator<IBuildingBlock>
    {
        private int current_index;

        public AbstractVectorPuzzleIterator()
        {
            current_index = 0;
        }


        @Override
        public boolean hasNext() {
            return current_index < vector_puzzle_data.length;
        }

        @Override
        public IBuildingBlock next() {
            return vector_puzzle_data[current_index++];
        }
    }
}
