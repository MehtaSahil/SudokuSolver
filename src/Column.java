/**
 * Created by sahil on 5/23/17.
 */

public class Column {

    private Square[] col_data;

    public Column(Square[] init_data)
    {
        /* verify 9x9 board */
        if (init_data.length != 9)
            throw new IllegalArgumentException("Row Constructor: init_data must be length 9");

        col_data = init_data;
    }
}
