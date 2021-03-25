package ru.croc.javaschool.Vehilce.MotorVehicle;

import ru.croc.javaschool.Vehilce.HasEngine;
import ru.croc.javaschool.Vehilce.TechnicalCondition;
import ru.croc.javaschool.Vehilce.Vehicle;

import java.time.LocalDate;

/**
 * Электромобиль. Имеет двигатель(электрический, работает от аккумулятора).
 */
public class ElectricCar extends Vehicle implements HasEngine {
    /**
     * Номер.
     */
    private String number;

    /**
     * Время полной зарядки в часах.
     */
    private int fullCharge;

    /**
     * Расход энергии в км/кВтч.
     */
    private int consumption;

    /**
     * Техническое состояние двигателя.
     */
    private TechnicalCondition engine = TechnicalCondition.WORKING;

    public ElectricCar(String type, String manufacturer, String number, int fullCharge, int consumption) {
        super(type, manufacturer);
        this.number = number;
        this.fullCharge = fullCharge;
        this.consumption = consumption;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFullCharge() {
        return fullCharge;
    }

    public void setFullCharge(int fullCharge) {
        this.fullCharge = fullCharge;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public TechnicalCondition getEngine() {
        return engine;
    }

    @Override
    public void broke() {
        super.broke();
    }

    @Override
    public void repair() {
        repairEngine();
        this.technicalCondition = TechnicalCondition.WORKING;
    }

    @Override
    public void brokeEngine() {
        broke();
        this.engine = TechnicalCondition.BROKEN;
    }

    @Override
    public void repairEngine() {//TODO
        repair();
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "fullCharge=" + fullCharge +
                ", consumption='" + consumption + '\'' +
                ", engine=" + engine +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", rent=" + rent +
                ", technicalCondition=" + technicalCondition +
                '}';
    }
}
