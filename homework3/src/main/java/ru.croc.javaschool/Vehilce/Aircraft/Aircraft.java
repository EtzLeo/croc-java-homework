package ru.croc.javaschool.Vehilce.Aircraft;

import ru.croc.javaschool.Vehilce.Vehicle;

/**
 * Летательное средство.
 */
public abstract class Aircraft extends Vehicle {
    /**
     * Высота полета.
     */
    private String attitude;

    /**
     * Дальность полета.
     */
    private String distance;

    public Aircraft(String type, String manufacturer, String attitude, String distance) {
        super(type, manufacturer);
        this.attitude = attitude;
        this.distance = distance;
    }

    @Override
    public void broke() {
        super.broke();
    }

    @Override
    public void repair() {
        super.repair();
    }
}
