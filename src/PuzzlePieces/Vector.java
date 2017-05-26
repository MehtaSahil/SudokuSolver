package PuzzlePieces; /**
 * Created by sahil on 5/23/17.
 */

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class Vector implements Iterable<Square> {

    /**
     * vector_data holds the Squares present in the vector
     * contained_values is a Set all VALUES contained in vector_data
     *      this is useful in updating PuzzlePieces.Square candidates
     */
    private Square[] vector_data;
    private Set<Integer> contained_values;
    private Map<Integer, Integer> candidate_counts;

    /**
     * copies a reference to a PuzzlePieces.Square[] (PuzzlePieces.Vector) into vector_data
     * initializes contained_values (used to maintain MainPuzzle)
     * @param init_data
     */
    public Vector(Square[] init_data)
    {
        /* verify 9x9 board */
        if (init_data.length != 9)
            throw new IllegalArgumentException("PuzzlePieces.Vector Constructor: init_data must be length 9");

        vector_data = init_data;

        contained_values = new HashSet<Integer>();
        candidate_counts = new HashMap<Integer, Integer>();

        for (int i = 0; i < vector_data.length; i++)
        {
            /* dealing with values of Squares */
            int to_add = vector_data[i].get_value();
            if (to_add == 0)
                continue;

            contained_values.add(to_add);
        }
    }

    /**
     * @param index
     * @return Integer value of the PuzzlePieces.Square at [index] in vector_data
     */
    public int get_value(int index)
    {
        if (index > vector_data.length)
            throw new IllegalArgumentException("PuzzlePieces.Vector index out of bounds");

        return vector_data[index].get_value();
    }

    public void add_to_contained_values(int to_add)
    {
        contained_values.add(to_add);
    }

    public Set<Integer> get_contained_values()
    {
        return contained_values;
    }

    public Map<Integer, Integer> get_candidate_counts()
    {
        return candidate_counts;
    }

    public void update_candidate_counts()
    {
        candidate_counts.clear();
        Iterator<Square> iter = iterator();
        while (iter.hasNext())
        {
            /* dealing with candidates within each Square */
            Set<Integer> candidates = iter.next().get_candidates();
            for (Integer num : candidates)
            {
                if (candidate_counts.containsKey(num))
                {
                    int new_value = candidate_counts.get(num) + 1;
                    candidate_counts.put(num, new_value);
                }
                else
                {
                    candidate_counts.put(num, 1);
                }
            }
        }
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (int i = 0; i < vector_data.length - 1; i++)
        {
            s.append(vector_data[i].get_value() + ", ");
        }
        s.append(vector_data[vector_data.length - 1].get_value() + "]");

        return s.toString();
    }

    @Override
    public Iterator<Square> iterator() {
        return new VectorIterator();
    }

    private class VectorIterator implements Iterator<Square>
    {
        private int current_index;

        public VectorIterator()
        {
            current_index = 0;
        }

        @Override
        public boolean hasNext() {
            return current_index < vector_data.length;
        }

        @Override
        public Square next() {
            return vector_data[current_index++];
        }
    }
}