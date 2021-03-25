package ru.croc.javaschool.Vehilce.Aircraft;

import ru.croc.javaschool.Vehilce.HasEngine;
import ru.croc.javaschool.Vehilce.TechnicalCondition;

/**
 * Вертолет.
 */
public class Helicopter extends Aircraft implements HasEngine {
    /**
     * Классификация по взлетному весу.
     */
    public enum Weight{
        ULTRALIGHT,
        LIGHT,
        MEDIUM
    }
    private final Weight weight;

    /**
     * Техническое состояние несущего винта.
     */
    private TechnicalCondition rotor = TechnicalCondition.WORKING;

    /**
     * Топливо для двигателя.
     */
    private String fuel;

    /**
     * Техническое состояние двигателя.
     */
    private TechnicalCondition engine = TechnicalCondition.WORKING;


    public Helicopter(String type, String manufacturer, String attitude, String distance, Weight weight, String fuel) {
        super(type, manufacturer, attitude, distance);
        this.weight = weight;
        this.fuel = fuel;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public TechnicalCondition getRotor() {
        return rotor;
    }

    public TechnicalCondition getEngine() {
        return engine;
    }

    public Weight getWeight() {
        return weight;
    }


    @Override
    public void broke() {
        super.broke();
    }

    @Override
    public void repair() {
        repairEngine();
        repairRotor();
        this.technicalCondition = TechnicalCondition.WORKING;
    }

    /**
     * Поломка винта.
     */
    public void brokeRotor() {
        broke();
        this.rotor = TechnicalCondition.BROKEN;
    }

    /**
     * Починка винта.
     */
    public void repairRotor() {//TODO
        this.rotor = TechnicalCondition.WORKING;
        if (engine == TechnicalCondition.WORKING){
            technicalCondition = TechnicalCondition.WORKING;
        }
    }

    @Override
    public void brokeEngine() {
        broke();
        this.engine = TechnicalCondition.BROKEN;
    }

    @Override
    public void repairEngine() {//TODO
        this.engine = TechnicalCondition.WORKING;
        if (rotor == TechnicalCondition.WORKING){
            technicalCondition = TechnicalCondition.WORKING;
        }
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "weight=" + weight +
                ", rotor=" + rotor +
                ", fuel='" + fuel + '\'' +
                ", engine=" + engine +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", rent=" + rent +
                ", technicalCondition=" + technicalCondition +
                '}';
    }
}
