package zoo;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(new Horse("At", 400, 5));
        animals.add(new Tiger("Kaplan", 220, 4));
        animals.add(new Rat("Sıçan", 2, 1));

        for (Animal animal : animals) {
            System.out.println(animal.getDosage());
            System.out.println(animal.getFeedSchedule());
        }

    }
}