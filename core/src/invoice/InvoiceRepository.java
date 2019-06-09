package invoice;

import java.util.List;

public interface InvoiceRepository {

    List<Invoice> getByNumbers(List<Integer> numbers);
}
