package ru.croc.javaschool.Vehicle.IndividualVehicle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.Vehilce.IndividualVehicle.Bike;
import ru.croc.javaschool.Vehilce.Rent;

import java.time.LocalDate;

public class BikeTest {

    @Test
    @DisplayName("Проверка механизма сдачи в аренду в случае поломки транспорта")
    public void bikeTest(){
        Bike testBike = new Bike("Велосипед", "Cube", Bike.Purpose.ROAD);
        testBike.setRent(new Rent(LocalDate.of(2021,3,20),
                LocalDate.of(2021, 3, 23)));

        Assertions.assertTrue(testBike.isRent());

        testBike.broke();

        Assertions.assertFalse(testBike.isRent());
    }
}
