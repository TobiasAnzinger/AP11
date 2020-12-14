package uebungInterfacesUndAdapterklassen;

public class Audio extends MediaAdapter{


    public Audio(int mediumNumber) {
        super(mediumNumber);
    }

    public void play() {
        System.out.println(this.getClass().getCanonicalName() + ": Play Audio");
    }

    public void stop() {
        System.out.println(this.getClass().getCanonicalName() + ": Audio stopped");
    }

}
