package ru.croc.javaschool.homework4;

import java.util.Objects;

/**
 * Вершина.
 *
 * @param <T> тип.
 */
public class Vertex<T> {
    /**
     * Ключ для обращения.
     */
    private final int key;

    /**
     * Данные.
     */
    private T data;

    /**
     * Создание вершины.
     *
     * @param key ключ
     * @param data данные
     */
    public Vertex(int key, T data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "key=" + key +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Vertex<T> vertex = (Vertex<T>) obj;
        if (this.key == vertex.getKey() && this.data == vertex.getData()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, data);
    }
}
