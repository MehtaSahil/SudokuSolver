/**
 * Created by sahil on 5/23/17.
 */

public class Puzzle {

    private Block[][] puzzle;

    public Puzzle(int[][] init_data)
    {
        /* check that init_data is 9x9 matrix (standard) */
        if (init_data.length != 9 || init_data[0].length != 9)
            throw new IllegalArgumentException("Puzzle Constructor: init_data must be 9x9");
        init_puzzle(init_data);
    }

    private void init_puzzle(int[][] init_data)
    {
        
    }
}
