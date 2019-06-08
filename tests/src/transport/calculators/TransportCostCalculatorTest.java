package transport.calculators;

import facades.DependencyInjectionFacade;
import org.junit.Before;
import org.junit.Test;
import transport.TransportData;
import transport.vehicle.VehicleRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransportCostCalculatorTest {

    private TransportCostCalculator transportCostCalculator;

    @Before
    public void setup() {
        VehicleRepository vehicleRepository = DependencyInjectionFacade.getInstanceOf(VehicleRepository.class);

        transportCostCalculator = new TransportCostCalculator(vehicleRepository);
    }

    @Test
    public void should_return_zero_for_no_data_provided() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInPavementRoad(0);
        transportData.setDistanceInUnpavementRoad(0);
        transportData.setWeightTon(0);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(0d));
    }

    @Test
    public void should_calculate_the_cost_for_paved_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInPavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(54d));
    }

    @Test
    public void should_calculate_the_cost_for_unpaved_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(62d));
    }

    @Test
    public void should_calculate_the_cost_in_a_route_with_paved_and_unpaved_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInPavementRoad(100);
        transportData.setDistanceInUnpavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(116d));
    }

    @Test
    public void should_calculate_the_cost_without_additional_cost_when_the_weight_ton_is_less_than_or_equal_to_five() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(100);
        transportData.setWeightTon(5);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(62d));
    }

    @Test
    public void should_calculate_the_cost_with_additional_cost_when_the_weight_ton_is_greater_than_five() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(100);
        transportData.setWeightTon(8);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(68d));
    }

    @Test
    public void should_calculate_without_additional_cost_after_apply_multiplying_factor_when_the_vehicle_is_box_truck() {
        int boxTruckId = 1;
        double multiplyingFactor = 1d;
        double transportCostExpected = 116d * multiplyingFactor;

        TransportData transportData = new TransportData();
        transportData.setVehicleId(boxTruckId);
        transportData.setDistanceInPavementRoad(100);
        transportData.setDistanceInUnpavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(transportCostExpected));
    }

    @Test
    public void should_calculate_with_additional_cost_after_apply_multiplying_factor_when_the_vehicle_is_bucket_truck() {
        int bucketTruckId = 2;
        double multiplyingFactor = 1.05d;
        double transportCostExpected = 116d * multiplyingFactor;

        TransportData transportData = new TransportData();
        transportData.setVehicleId(bucketTruckId);
        transportData.setDistanceInPavementRoad(100);
        transportData.setDistanceInUnpavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(transportCostExpected));
    }

    @Test
    public void should_calculate_with_additional_cost_after_apply_multiplying_factor_when_the_vehicle_is_big_truck() {
        int bigTruckVehicle = 3;
        double multiplyingFactor = 1.12d;
        double transportCostExpected = 116d * multiplyingFactor;

        TransportData transportData = new TransportData();
        transportData.setVehicleId(bigTruckVehicle);
        transportData.setDistanceInPavementRoad(100);
        transportData.setDistanceInUnpavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(transportCostExpected));
    }
}
