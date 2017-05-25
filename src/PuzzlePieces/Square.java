package PuzzlePieces; /**
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
        /* initialize value to blank (null) or prefilled value */
        if (init_val < 1 || init_val > 9)
            value = null;
        else
            value = init_val;

        candidates = new HashSet<Integer>();

        /**
         * Fill with all possible candidates on initialization
         * ONLY if the spot hasn't been filled with a value
         */
        if (value == null)
        {
            for (int i = 0; i < 9; i++)
                candidates.add(i + 1);
        }

        this.row = row;
        this.col = col;
    }

    /* Getters/Setters */
    public Set<Integer> get_candidates()
    {
        return candidates;
    }

    public void remove_candidates(Set<Integer> to_remove)
    {
        candidates.remove(to_remove);
    }

    public void remove_single_candidate(int to_remove)
    {
        candidates.remove(to_remove);
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

    public void set_value(int new_value) { value = new_value; }
}
