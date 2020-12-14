package uebungInterfacesUndAdapterklassen;

public class MediaAdapter implements Media{

    int mediumNumber;

    public MediaAdapter(int mediumNumber) {
        this.mediumNumber = mediumNumber;
    }

    public void play() {
    }

    public void stop() {
    }

    public void display() {
    }

    @Override
    public String toString() {
        return "Media" + mediumNumber + ":";
    }
}
