package ru.croc.java.school;

import java.util.ArrayList;

/**
 * Зоопарк.
 */
public class Zoo {
    private ArrayList<Animal> animals = new ArrayList<Animal>();

    //Конструктор
    public Zoo(ArrayList<Animal> animals){
        this.animals = animals;
    }

    /**
     * Добавление животного.
     * @param animal животное
     */
    public void add(Animal animal){
        this.animals.add(animal);
    }

    /**
     * Удаление животного.
     * @param animal животное
     */
    public void remove(Animal animal){
        this.animals.remove(animal);
    }

    /**
     * Вывод информации о всех животных
     */
    public void info(){
        animals.forEach(Animal::info);
    }
}