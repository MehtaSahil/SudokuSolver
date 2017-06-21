package main.java.process;

import main.java.abstraction.AbstractProcess;
import main.java.runner.PuzzleContainer;
import main.java.runner.PuzzleConverterUtils;

/**
 * Created by sahil on 6/20/17.
 *
 * Heavily borrowed from http://www.geeksforgeeks.org/backtracking-set-7-suduku/
 * with changes made to go from C --> Java and a few other modifications
 */
public class BruteForce extends AbstractProcess {

    private PuzzleContainer pc;
    private int[][] cells;
    private int num_instances = 0;
    private final int UNASSIGNED = 0;
    private final int N = 9;

    public BruteForce(PuzzleContainer pc) {
        super(pc);
        cells = PuzzleConverterUtils.convert2dSquareMatrixToIntegers(pc.standard_puzzle);
        this.pc = pc;
    }

    @Override
    public boolean run_process() {

        if (solve(cells))
            pc.set_data(PuzzleConverterUtils.convert2dIntegerMatrixToSquares(cells));
        else
            System.out.println("Could not find solution to the puzzle");

        return false;
    }

    /**
     * Solves the puzzle given in grid
     * @param grid
     * @return returns true if a solution was found, otherwise false
     */
    public boolean solve(int[][] grid)
    {
        int[] unassigned_spot = find_unassigned_location(grid);

        /* if an unassigned spot could not be found then the puzzle is solved */
        if (unassigned_spot == null)
            return true;

        int row = unassigned_spot[0];
        int col = unassigned_spot[1];

        for (int n = 1; n <= 9; n++)
        {
            if (is_safe(grid, row, col, n))
            {
                grid[row][col] = n;

                if (solve(grid))
                    return true;

                grid[row][col] = 0;
            }


        }

        return false;
    }

    /**
     * Searches grid for unassigned spots
     * @param grid
     * @return row (int[0]) and column (int[1]) of the first unassigned spot
     * null if there are no more unassigned locations
     */
    public int[] find_unassigned_location(int[][] grid)
    {
        int[] to_return = new int[2];
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                if (grid[r][c] == 0)
                {
                    to_return[0] = r;
                    to_return[1] = c;
                    return to_return;
                }
            }
        }

        return null;
    }

    /* Returns a boolean which indicates whether any assigned entry
       in the specified row matches the given number. */
    public boolean used_in_row(int[][] grid, int row, int num)
    {
        for (int col = 0; col < N; col++)
            if (grid[row][col] == num)
                return true;
        return false;
    }

    /* Returns a boolean which indicates whether any assigned entry
       in the specified column matches the given number. */
    public boolean used_in_col(int[][] grid, int col, int num)
    {
        for (int row = 0; row < N; row++)
            if (grid[row][col] == num)
                return true;
        return false;
    }

    /* Returns a boolean which indicates whether any assigned entry
       within the specified 3x3 box matches the given number. */
    public boolean used_in_box(int[][] grid, int boxStartRow, int boxStartCol, int num)
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row+boxStartRow][col+boxStartCol] == num)
                    return true;
        return false;
    }

    /* Returns a boolean which indicates whether it will be legal to assign
       num to the given row,col location. */
    public boolean is_safe(int[][] grid, int row, int col, int num)
    {
        /* Check if 'num' iWs not already placed in current row,
        current column and current 3x3 box */
        return !used_in_row(grid, row, num) &&
                !used_in_col(grid, col, num) &&
                !used_in_box(grid, row - row%3 , col - col%3, num);
    }
}
