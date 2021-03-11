package ru.croc.java.school;

import java.util.Date;

/**
 * Вольер.
 */
public class Aviary {
    private int number;
    private Date cleaningDate;

    //Конструктор
    public Aviary(int number){
        this(number, new Date());
    }

    //Конструктор
    public Aviary(Date cleaningDate){
        this(0, cleaningDate);
    }

    //Конструктор
    public Aviary(int number, Date cleaningDate){
        this.number = number;
        this.cleaningDate = cleaningDate;
    }

    /**
     * Установка даты последней уборки вольера
     * @param cleaningDate дата последней уборки
     */
    public void setCleaningDate(Date cleaningDate){
        this.cleaningDate = cleaningDate;
    }

    /**
     * Вывод информации о вольере
     */
    public void info(){
        System.out.println("Вольер №" + number + ", убран " + cleaningDate);
    }
}
