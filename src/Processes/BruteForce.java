package Processes;

import Abstract.AbstractProcess;
import Abstract.IBuildingBlock;
import Main.PuzzleContainer;
import Main.PuzzleConverterUtils;
import PuzzlePieces.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sahil on 6/20/17.
 */
public class BruteForce extends AbstractProcess {

    private PuzzleContainer pc;
    private int[][] cells;
    private int[][] solved;
    private int num_instances = 0;
    private final int UNASSIGNED = 0;
    private final int N = 9;

    public BruteForce(PuzzleContainer pc) {
        super(pc);
        cells = PuzzleConverterUtils.convert2dSquareMatrixToIntegers(pc.standard_puzzle);
        solved = new int[cells.length][cells[0].length];
        this.pc = pc;
    }

    @Override
    public boolean run_process() {
        if (solve(cells))
            pc.set_data(PuzzleConverterUtils.convert2dIntegerMatrixToSquares(cells));

        return false;
    }

    public boolean solve(int[][] grid)
    {
        int[] unassigned_spot = findUnassignedLocation(grid);
        if (unassigned_spot == null)
            return true;

        int row = unassigned_spot[0];
        int col = unassigned_spot[1];

//        int block_index = ((row/3) * 3) + (col%3);
//
//        IBuildingBlock current_row = row_puzzle.get_building_block(row);
//        IBuildingBlock current_col = col_puzzle.get_building_block(col);
//        IBuildingBlock current_block = block_puzzle.get_building_block(block_index);

        for (int n = 1; n <= 9; n++)
        {
            if (isSafe(grid, row, col, n))
            {
                grid[row][col] = n;

                if (solve(grid))
                    return true;

                grid[row][col] = 0;
            }
        }

        return false;
    }

    public int[] findUnassignedLocation(int[][] grid)
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


    // This function finds an entry in grid that is still unassigned
    // bool FindUnassignedLocation(int grid[N][N], int &row, int &col);

    // Checks whether it will be legal to assign num to the given row,col
    // bool isSafe(int grid[N][N], int row, int col, int num);

    /* Takes a partially filled-in grid and attempts to assign values to
      all unassigned locations in such a way to meet the requirements
      for Sudoku solution (non-duplication across rows, columns, and boxes) */
    public boolean SolveSudoku(int[][] grid)
    {
        Map<String, Integer> row_col_data = new HashMap<String, Integer>();

        // If there is no unassigned location, we are done
        if (!FindUnassignedLocation(grid, row_col_data))
            return true; // success!

        int row = row_col_data.get("row");
        int col = row_col_data.get("col");

        // consider digits 1 to 9
        for (int num = 1; num <= 9; num++)
        {
            // if looks promising
            if (isSafe(grid, row, col, num))
            {
                // make tentative assignment
                grid[row][col] = num;

                // return, if success, yay!
                if (SolveSudoku(grid))
                    return true;

                // failure, unmake & try again
                grid[row][col] = UNASSIGNED;
            }
        }
        return false; // this triggers backtracking
    }

    /* Searches the grid to find an entry that is still unassigned. If
       found, the reference parameters row, col will be set the location
       that is unassigned, and true is returned. If no unassigned entries
       remain, false is returned. */
    public boolean FindUnassignedLocation(int[][] grid, Map<String, Integer> row_col_data)
    {
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                if (grid[r][c] == UNASSIGNED)
                {
                    row_col_data.put("row", r);
                    row_col_data.put("col", c);
                    return true;
                }
        return false;
    }

    /* Returns a boolean which indicates whether any assigned entry
       in the specified row matches the given number. */
    public boolean UsedInRow(int[][] grid, int row, int num)
    {
        for (int col = 0; col < N; col++)
            if (grid[row][col] == num)
                return true;
        return false;
    }

    /* Returns a boolean which indicates whether any assigned entry
       in the specified column matches the given number. */
    public boolean UsedInCol(int[][] grid, int col, int num)
    {
        for (int row = 0; row < N; row++)
            if (grid[row][col] == num)
                return true;
        return false;
    }

    /* Returns a boolean which indicates whether any assigned entry
       within the specified 3x3 box matches the given number. */
    public boolean UsedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num)
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row+boxStartRow][col+boxStartCol] == num)
                    return true;
        return false;
    }

    /* Returns a boolean which indicates whether it will be legal to assign
       num to the given row,col location. */
    public boolean isSafe(int[][] grid, int row, int col, int num)
    {
    /* Check if 'num' is not already placed in current row,
       current column and current 3x3 box */
        return !UsedInRow(grid, row, num) &&
                !UsedInCol(grid, col, num) &&
                !UsedInBox(grid, row - row%3 , col - col%3, num);
    }

    /* A utility function to print grid  */
    void printGrid(int[][] grid)
    {
        for (int row = 0; row < N; row++)
        {
//            for (int col = 0; col < N; col++)
//                printf("%2d", grid[row][col]);
//            printf("\n");

            for (int col = 0; col < N; col++)
                System.out.print(grid[row][col]);
            System.out.println();
        }
    }
//
//    /* Driver Program to test above functions */
//    int main()
//    {
//        // 0 means unassigned cells
//        int grid[N][N] = {{9, 0, 4, 0, 3, 0, 0, 0, 0},
//        {0, 0, 3, 0, 0, 5, 0, 2, 7},
//        {0, 0, 0, 0, 0, 4, 0, 0, 9},
//        {0, 6, 2, 0, 0, 0, 0, 0, 0},
//        {4, 8, 0, 0, 2, 0, 0, 1, 5},
//        {0, 0, 0, 0, 0, 0, 2, 7, 0},
//        {2, 0, 0, 9, 0, 0, 0, 0, 0},
//        {6, 3, 0, 4, 0, 0, 9, 0, 0},
//        {0, 0, 0, 0, 5, 0, 1, 0, 8}};
//        if (SolveSudoku(grid) == true)
//            printGrid(grid);
//        else
//            printf("No solution exists");
//
//        return 0;
//    }
}
