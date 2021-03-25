package ru.croc.javaschool.Vehilce.IndividualVehicle;

import ru.croc.javaschool.Vehilce.Vehicle;

/**
 * Скейтборд.
 */
public class Skateboard extends Vehicle {
    /**
     * Виды скейтбордов.
     */
    public enum Kind{
        CLASSIC,
        SNAKEBOARD,
        LONGBOARD
    }

    private final Kind kind;

    public Skateboard(String type, String manufacturer, Kind kind) {
        super(type, manufacturer);
        this.kind = kind;
    }

    @Override
    public void broke() {
        super.broke();
    }

    @Override
    public void repair() {
        super.repair();
    }

    @Override
    public String toString() {
        return "Skateboard{" +
                "type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", rent=" + rent +
                ", technicalCondition=" + technicalCondition +
                '}';
    }
}
