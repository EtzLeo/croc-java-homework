package ru.croc.javaschool;

import ru.croc.javaschool.Booking.AccountingSystem;
import ru.croc.javaschool.Booking.Record;
import ru.croc.javaschool.Vehilce.*;
import ru.croc.javaschool.Vehilce.Aircraft.*;
import ru.croc.javaschool.Vehilce.IndividualVehicle.*;
import ru.croc.javaschool.Vehilce.MotorVehicle.*;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        //Создание системы учета
        AccountingSystem accountingSystem = AccountingSystem.getInstance(new Record[]{});

        //Описание всех транспортных средств компании
        Vehicle bike1 = new Bike("Велосипед", "Cube", Bike.Purpose.ROAD);

        Vehicle skate1 = new Skateboard("Скейтборд", "Zero", Skateboard.Kind.CLASSIC);

        Vehicle motorcycle1 = new Motorcycle("Мотоцикл", "Hero","1111AA",
                235,  Motorcycle.Kind.CRUISER);

        Vehicle tesla1 = new ElectricCar("Электромобиль", "Tesla", "А111АА",
                28, 5);

        Helicopter helicopter1 = new Helicopter("Вертолет", "Airbus", "6 км",
                "800 км", Helicopter.Weight.LIGHT, "ТС-1");

        Vehicle autogyro1 = new Autogyro("Автожир", "Apollo Aircrafts", "300 м",
                "600 км", Autogyro.CabinType.OPENED, "АИ-95");

        //Добавление всех средств компании в массив
        Vehicle[] allVehicles = new Vehicle[] {bike1, motorcycle1, helicopter1,
        skate1, autogyro1, tesla1};

        //Вывод имеющихся средств
        System.out.println("\nСписок всех транспортных средств, имеющихся в наличии:");
        for(Vehicle vehicle: allVehicles){
            System.out.println(vehicle.toString());
        }

        //Совершаем действия с транспортными средствами.
        bike1.setRent(new Rent(LocalDate.of(2021,3,20), LocalDate.of(2021, 3, 23)));
        accountingSystem.add(new Record(bike1.toString()));

        helicopter1.setRent(new Rent(LocalDate.of(2021,3,25), LocalDate.of(2021, 3, 26)));
        accountingSystem.add(new Record(helicopter1.toString()));

        helicopter1.brokeEngine();
        accountingSystem.add(new Record(helicopter1.toString()));

        helicopter1.brokeRotor();
        accountingSystem.add(new Record(helicopter1.toString()));

        helicopter1.repair();
        accountingSystem.add(new Record(helicopter1.toString()));

        helicopter1.setRent(new Rent(LocalDate.of(2021,3,25), LocalDate.of(2021, 3, 26)));
        accountingSystem.add(new Record(helicopter1.toString()));

        //Выводим совершенные действия
        System.out.println("\nУчет действий с транспортными средствами:");
        for(Record record: accountingSystem.records()){
            System.out.println(record.toString());
        }
    }
}
