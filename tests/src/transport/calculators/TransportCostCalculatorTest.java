package transport.calculators;

import inject.DependencyInjectionFacade;
import org.junit.Before;
import org.junit.Test;
import transport.TransportData;
import transport.exceptions.*;
import transport.vehicle.VehicleNotFoundException;
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
    public void should_calculate_the_cost_without_additional_cost_after_apply_multiplying_factor_when_the_vehicle_is_box_truck() {
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
    public void should_calculate_the_cost_with_additional_cost_after_apply_multiplying_factor_when_the_vehicle_is_bucket_truck() {
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
    public void should_calculate_the_cost_with_additional_cost_after_apply_multiplying_factor_when_the_vehicle_is_big_truck() {
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

    @Test
    public void should_calculate_the_cost_in_paved_road_with_weight_in_excess_applying_multiplying_factor_when_the_vehicle_is_bucket_truck() {
        int bucketTruckVehicle = 2;
        double multiplyingFactor = 1.05d;
        double wightAdditionalCost = 6d;
        double roadCost = 54d;
        double transportCostExpected = (roadCost * multiplyingFactor) + wightAdditionalCost;

        TransportData transportData = new TransportData();
        transportData.setVehicleId(bucketTruckVehicle);
        transportData.setWeightTon(8);
        transportData.setDistanceInPavementRoad(100);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(transportCostExpected));
    }

    @Test
    public void should_calculate_the_cost_in_unpaved_road_with_weight_in_excess_applying_multiplying_factor_when_the_vehicle_is_big_truck() {
        int bigTruckVehicle = 3;
        double multiplyingFactor = 1.12d;
        double wightAdditionalCost = 25.2d;
        double roadCost = 111.6d;
        double transportCostExpected = (roadCost * multiplyingFactor) + wightAdditionalCost;

        TransportData transportData = new TransportData();
        transportData.setVehicleId(bigTruckVehicle);
        transportData.setWeightTon(12);
        transportData.setDistanceInUnpavementRoad(180);

        double transportCost = transportCostCalculator.calculate(transportData);

        assertThat(transportCost, is(transportCostExpected));
    }


    @Test(expected = InvalidDistanceException.class)
    public void should_not_be_possible_to_calculate_transport_cost_with_negative_distance_in_paved_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInPavementRoad(-100);

        transportCostCalculator.calculate(transportData);
    }

    @Test(expected = InvalidDistanceException.class)
    public void should_not_be_possible_to_calculate_transport_cost_with_negative_distance_in_unpaved_road() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setDistanceInUnpavementRoad(-100);

        transportCostCalculator.calculate(transportData);
    }

    @Test(expected = InvalidWeightException.class)
    public void should_not_be_possible_to_calculate_transport_cost_with_negative_weight() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(1);
        transportData.setWeightTon(-5);
        transportData.setDistanceInUnpavementRoad(100);

        transportCostCalculator.calculate(transportData);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void should_not_be_possible_to_calculate_transport_cost_if_there_is_no_vehicle_with_the_provided_id() {
        TransportData transportData = new TransportData();
        transportData.setVehicleId(4);
        transportData.setDistanceInUnpavementRoad(100);

        transportCostCalculator.calculate(transportData);
    }
}
