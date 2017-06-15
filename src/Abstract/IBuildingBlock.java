package Abstract;

import PuzzlePieces.Square;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by sahil on 6/14/17.
 */
public interface IBuildingBlock {

    /**
     * Uses standard indexing for Vectors
     * Uses the convention that 0 is the top left and 8 is the bottom right for Blocks
     * @param index
     * @return
     */
    public int get_value_at_index(int index);

    public void add_to_contained_values(int to_add);
    public Set<Integer> get_contained_values();
    public Map<Integer, Integer> get_candidate_counts();
    public void update_candidate_counts();
    public String toString();
    public Iterator<Square> iterator();

}
