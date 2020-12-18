package Minimum;

import java.util.Arrays;

public class MinimumInteger {

    private Integer[] array;

    public MinimumInteger(Integer[] array) {
        this.array = array;
    }

    public Integer getMinimum(){
        Arrays.sort(array);
        return array[0];
    }
}
