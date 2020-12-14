package uebungInterfacesUndAdapterklassen;

public class Picture extends MediaAdapter{


    public Picture(int mediumNumber) {
        super(mediumNumber);
    }

    public void display() {

        System.out.println("class " + this.getClass().getCanonicalName() + ": Display Picture");
    }
}
