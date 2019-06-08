package services;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import valueObjects.TransportData;

public class TransportCostCalculatorTest {

    @Test
    public void should_return_zero_for_no_data_provided() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInPavementRoad(0);
        transportData.setDistanceInUnpavementRoad(0);
        transportData.setWeightTon(0);

        double transportCost = new TransportCostCalculator().calculate(transportData);

        assertThat(transportCost, is(0d));
    }

}
