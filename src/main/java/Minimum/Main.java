package Minimum;

public class Main {

    public static void main(String[] args) {


//        Object[] or = {new Integer(1), "hallo"};
        Integer[] or = {1, 2 ,5, 4, 7 ,0};
//        String[] or = { "d", "b", "k", "a"};

        Minimum minimum = new Minimum(or);

        System.out.print(minimum.getMinimum());

    }
}
