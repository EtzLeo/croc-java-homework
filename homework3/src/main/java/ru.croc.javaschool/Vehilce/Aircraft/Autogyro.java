package ru.croc.javaschool.Vehilce.Aircraft;

import ru.croc.javaschool.Vehilce.HasEngine;
import ru.croc.javaschool.Vehilce.TechnicalCondition;

/**
 * Автожир.
 */
public class Autogyro extends Aircraft implements HasEngine {
    /**
     * Тип кабины.
     */
    public enum CabinType{
        OPENED,
        CLOSED
    }
    private CabinType cabinType;

    /**
     * Техническое состояние шасси.
     */
    private TechnicalCondition chassis;

    /**
     * Топливо для двигателя.
     */
    private final String fuel;

    /**
     * Техническое состояние двигателя.
     */
    private TechnicalCondition engine = TechnicalCondition.WORKING;


    public Autogyro(String type, String manufacturer, String attitude, String distance,
                    CabinType cabinType, String fuel) {
        super(type, manufacturer, attitude, distance);
        this.cabinType = cabinType;
        this.fuel = fuel;
    }

    @Override
    public void broke() {
        super.broke();
    }

    @Override
    public void repair() {
        repairEngine();
        repairChassis();
        this.technicalCondition = TechnicalCondition.WORKING;
    }

    public void brokeChassis() {
        broke();
        this.chassis = TechnicalCondition.BROKEN;
    }

    public void repairChassis() {//TODO
        this.chassis = TechnicalCondition.WORKING;
        if (engine == TechnicalCondition.WORKING){
            this.technicalCondition = TechnicalCondition.WORKING;
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
        if (chassis == TechnicalCondition.WORKING){
            this.technicalCondition = TechnicalCondition.WORKING;
        }
    }

    @Override
    public String toString() {
        return "Autogyro{" +
                "cabinType=" + cabinType +
                ", chassis=" + chassis +
                ", fuel='" + fuel + '\'' +
                ", engine=" + engine +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", rent=" + rent +
                ", technicalCondition=" + technicalCondition +
                '}';
    }
}
