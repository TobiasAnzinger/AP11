package aufgabe3Polymorphie;

import java.util.ArrayList;

public class Finanzamt {

    public static void main(String[] args) {
        Person p1 = new Arbeitssuchend(6400, "Joe Unemployed");
        Person p2 = new Arbeiter(36000, "Suzi Hard-Working");
        Person p3 = new Banker(4000000, "Fred Moneymaker");
        Person p4 = new Koenigin(1000000, "Elisabeth");

        ArrayList<Person> steuerfahndung = new ArrayList<>();
        steuerfahndung.add(p1);
        steuerfahndung.add(p2);
        steuerfahndung.add(p3);
        steuerfahndung.add(p4);

        steuerfahndung.forEach(System.out::println);

        System.out.println("Gesammtsteuern: " + berechneSteuer(steuerfahndung));

    }

    static int berechneSteuer(ArrayList<Person> list){
        int gesammtSteuern = 0;
        for (Person person : list) gesammtSteuern = gesammtSteuern + person.steuer();
        return gesammtSteuern;
    }

}
