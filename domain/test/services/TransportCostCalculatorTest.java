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

    @Test
    public void should_calculate_the_cost_for_pavement_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInPavementRoad(100);

        double transportCost = new TransportCostCalculator().calculate(transportData);

        assertThat(transportCost, is(54d));
    }

    @Test
    public void should_calculate_the_cost_for_unpavement_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(100);

        double transportCost = new TransportCostCalculator().calculate(transportData);

        assertThat(transportCost, is(62d));
    }

    @Test
    public void should_calculate_the_cost_without_additional_cost_when_the_weight_ton_is_less_than_or_equal_to_five()
    {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(100);
        transportData.setWeightTon(5);

        double transportCost = new TransportCostCalculator().calculate(transportData);

        assertThat(transportCost, is(62d));
    }

    @Test
    public void should_calculate_the_cost_with_additional_cost_when_the_weight_ton_is_greater_than_five()
    {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(100);
        transportData.setWeightTon(8);

        double transportCost = new TransportCostCalculator().calculate(transportData);

        assertThat(transportCost, is(68d));
    }
}
