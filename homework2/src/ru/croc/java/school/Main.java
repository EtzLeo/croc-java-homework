package ru.croc.java.school;

import java.util.ArrayList;
import java.util.Date;

/**
 * Вход в программу
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Алексей", "Лебедев"));
        employees.add(new Employee("Матвей", "Волков"));
        employees.add(new Employee("Елена", "Зайцева"));

        System.out.println("Список сотрудников:");
        employees.forEach(Employee::info);

        ArrayList<Aviary> aviaries = new ArrayList<Aviary>();
        aviaries.add(new Aviary(1));
        aviaries.add(new Aviary(2));
        aviaries.add(new Aviary(3));

        System.out.println("\nСписок вольеров:");
        aviaries.forEach(Aviary::info);

        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Лев", aviaries.get(0), employees.get(0)));
        animals.add(new Animal("Волк", "Болит зуб", new Date(), aviaries.get(1), employees.get(0)));
        animals.add(new Animal("Волк", aviaries.get(1), employees.get(1)));

        Zoo zoo = new Zoo(animals);

        System.out.println("\nСписок животных:");
        zoo.info();

        animals.get(1).setSickness("Здоров");
        System.out.println(">Вылечили больного волка");

        aviaries.get(2).setCleaningDate(new Date());
        System.out.println(">Сделали уборку в новом вольере");

        animals.get(1).setAviary(aviaries.get(2));
        System.out.println(">Переместили волка в новый вольер");

        animals.get(1).setEmployee(employees.get(2));
        System.out.println(">Назначили другого ответственного сотрудника волку");

        animals.get(1).setFeedingDate(new Date());
        System.out.println(">Покормили волка");

        System.out.println("\nИнформация о волке:");
        animals.get(1).info();

        zoo.remove(animals.get(1));
        System.out.println(">Отправили волка в заповедник");

        System.out.println("\nСписок животных:");
        zoo.info();
    }
}
