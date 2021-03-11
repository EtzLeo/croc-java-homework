package ru.croc.java.school;

import java.util.Date;

/**
 * Животное.
 */
public class Animal {
    private final String name;
    //TODO предусмотреть возможность изменения статуса животного (болен, здоров)
    // и возможность хранить историю болезни (class Sickness)
    private String sickness; // болезнь
    private Date feedingDate; // кормление
    private Aviary aviary;
    private Employee employee;

    //Конструктор
    public Animal(String name, Aviary aviary, Employee employee){
        this(name, "Здоров", new Date(), aviary, employee);
    }

    //Конструктор
    public Animal(String name, String sickness, Date feedingDate, Aviary aviary, Employee employee){
        this.name = name;
        this.sickness = sickness;
        this.feedingDate = feedingDate;
        this.aviary = aviary;
        this.employee = employee;
    }

    /**
     * Установка болезни, если она есть
     * @param sickness болезнь
     */
    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    /**
     * Установка последней даты кормления животного
     * @param feedingDate дата кормления
     */
    public void setFeedingDate(Date feedingDate) {
        this.feedingDate = feedingDate;
    }

    /**
     * Установка вольера для животного
     * @param aviary вольер
     */
    public void setAviary(Aviary aviary) {
        this.aviary = aviary;
    }

    /**
     * Установка ответственного сотрудника для животного
     * @param employee сотрудник
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Вывод информации о животном
     */
    public void info(){
        System.out.println("Животное: " + name + ", " + sickness + ", кормили " + feedingDate + " ");
        aviary.info();
        employee.info();
        System.out.println();
    }
}
