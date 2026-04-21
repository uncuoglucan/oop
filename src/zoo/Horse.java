package zoo;
import zoo.Equine;

public class Horse extends Equine {
    public Horse(String speciesName, double weight, int age) {
        super(speciesName, weight, age);
    }

    @Override
    public double getDosage() {
        return weight * 0.1;
    }

    @Override
    public String getFeedSchedule() {
        return "Günde 3 kez ot";
    }
}