package ru.croc.javaschool.homework5;

import ru.croc.javaschool.homework5.view.ConsoleInteraction;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        ConsoleInteraction consoleInteraction = new ConsoleInteraction();
        consoleInteraction.commandHandling();
    }
}
