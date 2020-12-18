package Minimum;

import java.util.Arrays;

public class Minimum<T> {

    private final T[] array;

    public Minimum(T[] array) {
        this.array = array;
    }

    public T getMinimum(){
        Arrays.sort(array);
        return array[0];
    }
}
