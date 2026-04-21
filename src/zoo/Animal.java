package zoo;

public abstract class Animal {
    protected String speciesName;
    protected double weight;
    protected int age;

    public Animal(String speciesName, double weight, int age) {
        this.speciesName = speciesName;
        this.weight = weight;
        this.age = age;
    }

    public abstract double getDosage();
    public abstract String getFeedSchedule();
}