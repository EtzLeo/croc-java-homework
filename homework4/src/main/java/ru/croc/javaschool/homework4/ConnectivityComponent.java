package ru.croc.javaschool.homework4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Компонент связности.
 *
 * @param <T> тип
 */
public class ConnectivityComponent<T> {
    /**
     * Множество связанных вершин.
     */
    private Set<Vertex<T>> vertexes;

    /**
     * Создание пустого компонента связности.
     */
    public ConnectivityComponent() {
        this.vertexes = new HashSet<>();
    }

    /**
     * Создание компонента связности с одной вершиной.
     * @param vertex
     */
    public ConnectivityComponent(Vertex<T> vertex) {
        this.vertexes = new HashSet<>(Collections.singletonList(vertex));
    }

    public Set<Vertex<T>> getVertexes() {
        return vertexes;
    }

    public void setVertexes(Set<Vertex<T>> vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public String toString() {
        return "ConnectivityComponent{" +
                "vertexes=" + vertexes +
                '}';
    }
}
