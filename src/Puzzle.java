/**
 * Created by sahil on 5/23/17.
 */

public class Puzzle {

    private RowPuzzle rPuzzle;
    private ColPuzzle cPuzzle;

    public Puzzle(Square[][] init_data)
    {
        rPuzzle = new RowPuzzle(init_data);
        cPuzzle = new ColPuzzle(init_data);

        System.out.println("row puzzle");
        System.out.println(rPuzzle);
        System.out.println("column puzzle");
        System.out.println(cPuzzle);
    }
}
