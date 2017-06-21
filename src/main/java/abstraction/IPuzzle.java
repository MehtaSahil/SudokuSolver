package main.java.abstraction;

import main.java.puzzlepiece.Square;

import java.util.Iterator;

/**
 * Created by sahil on 6/14/17.
 */
public interface IPuzzle {

    public void init_puzzle_data(Square[][] init_data);
    public IBuildingBlock get_building_block(int index);
    public void update_candidate_counts();

    public String toString();
    public Iterator<IBuildingBlock> iterator();
}
