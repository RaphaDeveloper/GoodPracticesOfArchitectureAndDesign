package invoice;

import java.util.*;

public class RandomInvoiceRepository implements InvoiceRepository {

    @Override
    public List<Invoice> getByNumbers(List<Integer> numbers) {
        List<Invoice> invoices = new ArrayList<>();

        for (int invoiceNumber : numbers) {
            invoices.add(new Invoice(invoiceNumber, generateRandomValue()));
        }

        return invoices;
    }

    private double generateRandomValue() {
        Random r = new Random();

        double rangeMin = 0d;
        double rangeMax = 15000d;

        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        return randomValue;
    }
}
