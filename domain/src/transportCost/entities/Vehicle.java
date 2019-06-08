package transportCost.entities;

public class Vehicle {
    private int id;
    private String name;
    private double multiplyingFactor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMultiplyingFactor() {
        return multiplyingFactor;
    }

    public void setMultiplyingFactor(double multiplyingFactor) {
        this.multiplyingFactor = multiplyingFactor;
    }
}
