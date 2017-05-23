/**
 * Created by sahil on 5/23/17.
 */
public class Vector {

    private Square[] vector_data;

    public Vector(Square[] init_data)
    {
        /* verify 9x9 board */
        if (init_data.length != 9)
            throw new IllegalArgumentException("Vector Constructor: init_data must be length 9");

        vector_data = init_data;
    }
}
