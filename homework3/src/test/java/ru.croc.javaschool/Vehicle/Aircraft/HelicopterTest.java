package ru.croc.javaschool.Vehicle.Aircraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.Vehilce.Aircraft.Helicopter;
import ru.croc.javaschool.Vehilce.Rent;

import java.time.LocalDate;

public class HelicopterTest {

    @Test
    @DisplayName("Проверка механизма сдачи в аренду в случае поломки транспорта")
    public void helicopterTest(){
        Helicopter helicopter = new Helicopter("Вертолет", "Airbus", "6 км",
                "800 км", Helicopter.Weight.LIGHT, "ТС-1");

        Rent date = new Rent(LocalDate.of(2021,3,20),
                LocalDate.of(2021, 3, 23));

        helicopter.setRent(date);
        Assertions.assertTrue(helicopter.isRent());

        helicopter.broke();
        Assertions.assertFalse(helicopter.isRent());

        helicopter.repair();
        helicopter.setRent(date);
        Assertions.assertTrue(helicopter.isRent());

        helicopter.brokeEngine();
        Assertions.assertFalse(helicopter.isRent());

        helicopter.repairEngine();
        helicopter.setRent(date);
        Assertions.assertTrue(helicopter.isRent());

        helicopter.brokeRotor();
        Assertions.assertFalse(helicopter.isRent());

        helicopter.repairRotor();
        helicopter.setRent(date);
        Assertions.assertTrue(helicopter.isRent());
    }
}
