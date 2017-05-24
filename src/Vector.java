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

    public int get(int index)
    {
        if (index > vector_data.length)
            throw new IllegalArgumentException("Vector index out of bounds");

        return vector_data[index].get_value();
    }

    public Square[] get_vector_data()
    {
        return vector_data;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (int i = 0; i < vector_data.length - 1; i++)
        {
            s.append(vector_data[i].get_value() + ", ");
        }
        s.append(vector_data[vector_data.length - 1].get_value() + "]");

        return s.toString();
    }
}