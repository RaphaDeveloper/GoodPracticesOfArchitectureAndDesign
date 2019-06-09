package observation.invoice;

import invoice.Invoice;
import invoice.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepositoryStub implements InvoiceRepository {

    @Override
    public List<Invoice> getByNumbers(List<Integer> numbers) {
        double value = 0;

        List<Invoice> invoices = new ArrayList<>();

        for (int invoiceNumber : numbers) {
            value += 10;

            invoices.add(new Invoice(invoiceNumber, value));
        }

        return invoices;
    }
}
