package PuzzlePieces; /**
 * Created by sahil on 5/23/17.
 */

import java.util.Set;
import java.util.HashSet;

public class Vector {

    /**
     * vector_data holds the Squares present in the vector
     * contained_values is a Set all VALUES contained in vector_data
     *      this is useful in updating PuzzlePieces.Square candidates
     */
    private Square[] vector_data;
    private Set<Integer> contained_values;

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
        for (int i = 0; i < vector_data.length; i++)
        {
            contained_values.add(vector_data[i].get_value());
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

    public Square[] get_vector_data()
    {
        return vector_data;
    }

    public Set<Integer> get_contained_values()
    {
        return contained_values;
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
}