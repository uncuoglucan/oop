package zoo;

import zoo.Feline;

public class Tiger extends Feline {
    public Tiger(String speciesName, double weight, int age) {
        super(speciesName, weight, age);
    }

    @Override
    public double getDosage() {
        return weight * 0.2;
    }

    @Override
    public String getFeedSchedule() {
        return "Günde 2 kez et";
    }
}