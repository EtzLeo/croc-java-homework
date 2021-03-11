package ru.croc.java.school;

/**
 * Сотрудник.
 */
public class Employee {
    private final String name;
    private final String surname;

    //Конструктор
    public Employee(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    /**
     * Вывод информации о сотруднике
     */
    public void info(){
        System.out.println("Сотрудник: " + name + " " + surname);
    }
}
