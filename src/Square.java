/**
 * Created by sahil on 5/23/17.
 */

import java.util.Set;
import java.util.HashSet;

public class Square {

    /* Stores options for the true value */
    private Set<Integer> candidates;
    private Integer value;

    /* Stores location of square in puzzle */
    private final int row;
    private final int col;

    public Square(int row, int col, int init_val)
    {
        candidates = new HashSet<Integer>();

        /* Fill with all possible candidates on initialization */
        for (int i = 0; i < 9; i++)
            candidates.add(i + 1);

        /* initialize value to blank (null) or prefilled value */
        if (init_val < 1 || init_val > 9)
            value = null;
        else
            value = init_val;

        this.row = row;
        this.col = col;
    }

    /* Getters */
    public Set<Integer> get_candidates()
    {
        return candidates;
    }

    public int get_value()
    {
        if (value == null)
            return 0;

        return value;
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
     * No reason for outside objects to manipulate candidates/value
     * row and col cannot (and should not) be able to change after creation
     */
}
