package uebungInterfacesUndAdapterklassen;


public class Video extends MediaAdapter {

    public Video(int mediumNumber) {
        super(mediumNumber);
    }

    public void play() {
        System.out.println("class " + this.getClass().getCanonicalName() + ": Play Video");
    }

    public void stop() {
        System.out.println("class " + this.getClass().getCanonicalName() + ": Video stopped");
    }

    public void display() {
        System.out.println("class " + this.getClass().getCanonicalName() + ": Display Video");
    }
}
