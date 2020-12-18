package Minimum;

import java.util.Arrays;

public class Minimum<T> {

    private Object[] array;

    public Minimum(Object[] array) {
        this.array = array;
    }

    public Object getMinimum(){
        Arrays.sort(array);
        return array[0];
    }
}
