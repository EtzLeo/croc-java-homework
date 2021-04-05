package ru.croc.javaschool.homework5.controller;

import ru.croc.javaschool.homework5.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для взаимодействия с файлом данных.
 */
public class DataFileInteraction {
    /**
     * Путь к файлу.
     */
    private String path;

    public DataFileInteraction(String path) {
        this.path = path;
    }

    public DataFileInteraction() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Сохранение задачи в файл.
     *
     * @param task задача
     */
    public void createTask(Task task) {
        List<Task> tasks = getTasksList();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            if (!tasks.isEmpty()) {
                for (Task t : tasks) {
                    objectOutputStream.writeObject(t);
                }
            }
            objectOutputStream.writeObject(task);
        } catch (IOException ignored) {

        }

    }

    /**
     * Изменение задачи.
     *
     * @param newTask измененная задача
     */
    public void editTask(Task newTask) {
        List<Task> tasks = getTasksList();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path)))
        {
            for (Task task: tasks) {
                if (task.getId() == newTask.getId()) {
                    task = newTask;
                }
                objectOutputStream.writeObject(task);
            }
        } catch (IOException ignored) {

        }

    }

    /**
     * Удаление задачи.
     *
     * @param id идентификатор
     */
    public void removeTask(int id) {
        List<Task> tasks = getTasksList();

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            if (!tasks.isEmpty()) {
                for (Task t : tasks) {
                    if (t.getId() != id) {
                        objectOutputStream.writeObject(t);
                    }
                }
            }
        } catch (IOException ignored) {

        }

    }

    /**
     * Получение списка всех задач из файла.
     *
     * @return список задач
     */
    public List<Task> getTasksList() {
        List<Task> tasks = new ArrayList<>();

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            Task readTask  = (Task)objectInputStream.readObject();
            while(readTask != null) {
                tasks.add(readTask);
                readTask = (Task)objectInputStream.readObject();
            }
        } catch (ClassNotFoundException | IOException ignored) {

        }

        return tasks;
    }

    /**
     * Получение задачи по идентификатору.
     *
     * @param id идентификатор
     * @return задача
     */
    public Task getTaskById(int id) {
        List<Task> tasks = new ArrayList<>();

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            Task readTask  = (Task)objectInputStream.readObject();
            while(readTask != null) {
                tasks.add(readTask);
                readTask = (Task)objectInputStream.readObject();
            }
        }
        catch(ClassNotFoundException | IOException ignored) {

        }

        for (Task task: tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Получение идентификатора последней задачи(наибольшего)
     *
     * @return идентификатор
     */
    public int getLastId() {
        List<Task> tasks = getTasksList();
        if (tasks.isEmpty()) {
            return 0;
        }
        return tasks.get(tasks.size() - 1).getId();
    }
}
