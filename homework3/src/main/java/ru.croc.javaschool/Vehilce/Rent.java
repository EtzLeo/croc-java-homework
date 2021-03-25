package ru.croc.javaschool.Vehilce;

import java.time.Period;
import java.time.LocalDate;

/**
 * Аренда.
 */
public class Rent {
    /**
     * Дата начала аренды.
     */
    private LocalDate start;

    /**
     * Дата окончания аренды.
     */
    private LocalDate end;

    public Rent(){}//TODO

    public Rent(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    /**
     * Длительность аренды.
     * @return количество дней аренды
     */
    public Period duration(){
        Period period = Period.between(this.start, this.end);
        return period;
    }

    /**
     * Дней до конца аренды.
     */
    public Period end(){
        Period period = Period.between(LocalDate.now(), this. end);
        return period;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
