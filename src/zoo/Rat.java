package zoo;

import zoo.Rodent;

public class Rat extends Rodent {
    public Rat(String speciesName, double weight, int age) {
        super(speciesName, weight, age);
    }

    @Override
    public double getDosage() {
        return weight * 0.05;
    }

    @Override
    public String getFeedSchedule() {
        return "Günde 5 kez küçük porsiyon";
    }
}