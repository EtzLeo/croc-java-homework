package ru.croc.javaschool.Vehilce.IndividualVehicle;

import ru.croc.javaschool.Vehilce.Vehicle;

/**
 * Велосипед.
 */
public class Bike extends Vehicle {
    /**
     * Назначение.
     * Велосипеды бывают Дорожные, Туристические, Гибридные, Горные, Универсальные.
     */
    public enum Purpose{
        ROAD,
        TOURING,
        HYBRID,
        MOUNTAIN,
        UTILITY
    }

    private final Purpose purpose;

    public Bike(String type, String manufacturer, Purpose purpose) {
        super(type, manufacturer);
        this.purpose = purpose;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "purpose=" + purpose +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", rent=" + rent +
                ", technicalCondition=" + technicalCondition +
                '}';
    }
}
