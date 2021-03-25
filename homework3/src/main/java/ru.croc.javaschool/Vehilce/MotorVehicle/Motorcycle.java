package ru.croc.javaschool.Vehilce.MotorVehicle;

import ru.croc.javaschool.Vehilce.HasEngine;
import ru.croc.javaschool.Vehilce.TechnicalCondition;
import ru.croc.javaschool.Vehilce.Vehicle;

/**
 * Мотоцикл.
 */
public class Motorcycle extends Vehicle implements HasEngine {
    /**
     * Номер.
     */
    private String number;

    /**
     * Максимальная скорость передвижения.
     */
    private float speed;

    /**
     * Тип, основанный на целевом назначении.
     * Мотоциклы бывают Классические, Спортивные, Крузеры, Туристические, Внедорожники.
     */
    public enum Kind {
        STANDART,
        SPORT,
        CRUISER,
        TOURING,
        OFF_ROAD
    }

    private final Kind kind;

    /**
     * Техническое состояние двигателя.
     */
    private TechnicalCondition engine = TechnicalCondition.WORKING;

    public Motorcycle(String type, String manufacturer, String number, float speed, Kind kind) {
        super(type, manufacturer);
        this.number = number;
        this.speed = speed;
        this.kind = kind;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Kind getKind() {
        return kind;
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
}
