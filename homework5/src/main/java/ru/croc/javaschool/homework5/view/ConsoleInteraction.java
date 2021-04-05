package ru.croc.javaschool.homework5.view;

import ru.croc.javaschool.homework5.controller.DataFileInteraction;
import ru.croc.javaschool.homework5.model.Status;
import ru.croc.javaschool.homework5.model.Task;

import java.io.File;
import java.util.Scanner;

/**
 * Взаимодействие с консолью.
 */
public class ConsoleInteraction {
    /**
     * Сканер. Считывает данные с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Контроллер для взаимодействия с файлом данных.
     */
    private DataFileInteraction dataFileInteraction = new DataFileInteraction();

    /**
     * Выбор файла для записи данных.
     * Файл должен быть создан заранее.
     */
    public void chooseFile() {
        System.out.println("Введите путь к файлу с задачами, либо пропустите этот пункт.");
        String path = scanner.nextLine();
        if (path.isBlank()) {
            System.out.println("Имя файла по умолчанию \"tasks.txt\"");
            dataFileInteraction.setPath("tasks.txt");
        }
        else {
            File f = new File(path);
            if(f.isFile() && !f.isDirectory()) {
                dataFileInteraction.setPath(path);
            }
            else {
                System.out.println("Имя файла задано неверно. Имя файла по умолчанию \"tasks.txt\"");
                dataFileInteraction.setPath("tasks.txt");
            }
        }
    }

    /**
     * Обработка команд для взаимодействия с консолью.
     */
    public void commandHandling() {
        chooseFile();

        int command = 0;
        while(command != 5) {
            System.out.println("Выберите команду:\n1. Вывести список задач." +
                    "\n2. Создать задачу." +
                    "\n3. Отредактировать задачу." +
                    "\n4. Удалить задачу." +
                    "\n5. Выход.");

            command = scanner.nextInt();

            switch (command) {
                case 1:
                    if (dataFileInteraction.getTasksList() == null) {
                        System.out.println("Пока задач нет!");
                    }
                    else {
                        System.out.println(dataFileInteraction.getTasksList());
                    }
                    break;
                case 2:
                    System.out.print("Введите данные:\nНаименование: ");
                    String name = scanner.next();
                    System.out.print("Описание: ");
                    String description = scanner.next();
                    System.out.print("Исполнитель: ");
                    String executer = scanner.next();
                    System.out.print("Статус: ");
                    Status status = statusSelectionHandling();

                    Task task = new Task(dataFileInteraction.getLastId() + 1, name, description, executer, status);
                    dataFileInteraction.createTask(task);
                    break;
                case 3:
                case 4:
                    if (dataFileInteraction.getTasksList().isEmpty()) {
                        System.out.println("Пока задач нет!");
                    }
                    else {
                        System.out.print("Введите id задачи: ");
                        int id = scanner.nextInt();
                        if (dataFileInteraction.getTaskById(id) == null) {
                            System.out.println("Такой задачи не существует!");
                        } else {
                            if (command == 3) {
                                taskEditingHandling(id);
                            }
                            if (command == 4) {
                                dataFileInteraction.removeTask(id);
                            }
                        }
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Неверная команда. Попробуйте ещё раз!");
            }
        }
    }

    /**
     * Обработка выбора статуса задачи.
     *
     * @return статус
     */
    public Status statusSelectionHandling() {
        Status status;
        System.out.print("Выберете статус задачи:\n1. Готово." +
                "\n2. В процессе." +
                "\n3. Не готово." +
                "\n");
        int statusCommand = scanner.nextInt();
        switch (statusCommand) {
            case 1:
                status = Status.COMPLETE;
                break;
            case 2:
                status = Status.IN_PROGRESS;
                break;
            case 3:
                status = Status.BACKLOG;
                break;
            default:
                status = Status.BACKLOG;
                System.out.println("Неверно задан номер. По умолчанию присвоен статус - не готово.");
        }
        return status;
    }

    /**
     * Обработка редактирования записи по id через консоль.
     *
     * @param id идентификатор
     */
    public void taskEditingHandling(int id) {
        int editCommand = 0;
        Task task = dataFileInteraction.getTaskById(id);
        while (editCommand != 5) {
            System.out.print("Выберете поля для изменения:\n1. Наименование." +
                    "\n2. Описание." +
                    "\n3. Исполнитель." +
                    "\n4. Статус." +
                    "\n5. Не хочу ничего менять.\n");
            editCommand = scanner.nextInt();
            switch (editCommand) {
                case 1:
                    System.out.print("Введите наименование: ");
                    task.setName(scanner.next());
                    break;
                case 2:
                    System.out.print("Введите описание: ");
                    task.setDescription(scanner.next());
                    break;
                case 3:
                    System.out.print("Введите исполнителя: ");
                    task.setExecutor(scanner.next());
                    break;
                case 4:
                    System.out.print("Введите статус: ");
                    Status status = statusSelectionHandling();
                    task.setStatus(status);
                    break;
                case 5:
                    dataFileInteraction.editTask(task);
                    break;
                default:
                    System.out.println("Неверная команда. Попробуйте ещё раз!");
            }
        }
    }
}
