package ru.croc.javaschool.homework5.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Задача.
 */
public class Task implements Serializable {

    //private static final long serialVersionUID = 1L;

    /**
     * Код задачи.
     */
    private int id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * Описание.
     */
    private String description;

    /**
     * Исполнитель.
     */
    private String executor;

    /**
     * Статус.
     */
    private Status status;

    public Task(int id, String name, String description, String executor, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }

    public Task(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", executor='" + executor + '\'' +
                ", status=" + status +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(executor, task.executor) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, executor, status);
    }
}
