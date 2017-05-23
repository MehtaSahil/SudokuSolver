/**
 * Created by sahil on 5/23/17.
 */

import java.util.Set;
import java.util.HashSet;

public class Square {

    /* Stores options for the true final_value */
    private Set<Integer> candidates;
    private int final_value;

    /* Stores location of square in puzzle */
    private final int row;
    private final int col;

    public Square(int row, int col)
    {
        candidates = new HashSet<Integer>();

        /* Fill with all possible candidates on initialization */
        for (int i = 0; i < 9; i++)
            candidates.add(i + 1);

        final_value = 0;
        this.row = row;
        this.col = col;
    }

    /* Getters */
    public Set<Integer> get_candidates()
    {
        return candidates;
    }

    public int get_final_value()
    {
        return final_value;
    }

    public int get_row()
    {
        return row;
    }

    public int get_col()
    {
        return col;
    }

    /**
     * Setters are uneccessary
     * No reason for outside objects to manipulate candidates/final_value
     * row and col cannot (and should not) be able to change after creation
     */
}
