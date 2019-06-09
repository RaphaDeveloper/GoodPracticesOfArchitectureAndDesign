package transport.vehicle;

public class Vehicle {
    private int id;
    private String name;
    private double multiplyingFactor;

    public Vehicle(int id, String name, double multiplyingFactor) {
        this.id = id;
        this.name = name;
        this.multiplyingFactor = multiplyingFactor;
    }

    public double getMultiplyingFactor() {
        return multiplyingFactor;
    }
}
