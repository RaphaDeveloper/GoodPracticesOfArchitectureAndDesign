package invoice;

public class Invoice {
    private int number;
    private double value;

    public Invoice(int number, double value) {
        this.number = number;
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }
}
