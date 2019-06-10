package invoice;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class RandomInvoiceRepositoryTest {

    @Test
    public void should_be_possible_to_get_invoices_by_number() {

        RandomInvoiceRepository invoiceRepository = new RandomInvoiceRepository();

        List<Invoice> invoices = invoiceRepository.getByNumbers(Arrays.asList(1, 2, 3, 4, 5));

        Assert.assertEquals(5, invoices.size());
    }
}
