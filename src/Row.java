/**
 * Created by sahil on 5/23/17.
 */

public class Row {

    private Square[] row_data;

    public Row(Square[] init_data)
    {
        /* verify 9x9 board */
        if (init_data.length != 9)
            throw new IllegalArgumentException("Row Constructor: init_data must be length 9");

        row_data = init_data;
    }
}
