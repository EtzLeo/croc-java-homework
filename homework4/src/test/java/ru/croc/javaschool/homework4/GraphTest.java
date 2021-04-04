package ru.croc.javaschool.homework4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GraphTest {

    Graph<String> graph = new Graph<>(){};
    Vertex<String> vertex1 = new Vertex<String>(1, "1");
    Vertex<String> vertex2 = new Vertex<String>(2,"2");
    Vertex<String> vertex3 = new Vertex<String>(3, "3");
    Vertex<String> vertex4 = new Vertex<String>(4, "4");

    @Test
    @DisplayName("Тест метода подсчета компонент связности.")
    public void testAmountOfComponents(){
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        // три несмежных вершины
        Assertions.assertEquals(3, graph.getAmountOfConnectivityComponents());

        // добавляем ребро 1-2
        graph.addEdge(vertex1, vertex2);
        Assertions.assertEquals(2, graph.getAmountOfConnectivityComponents());

        //добавляем вершину
        graph.addVertex(vertex4);
        Assertions.assertEquals(3, graph.getAmountOfConnectivityComponents());

        //добавляем ребро 3-4
        graph.addEdge(vertex3, vertex4);
        Assertions.assertEquals(2, graph.getAmountOfConnectivityComponents());

        //добавляем ребро 3-1
        graph.addEdge(vertex3, vertex1);
        Assertions.assertEquals(1, graph.getAmountOfConnectivityComponents());
    }

    @Test
    @DisplayName("Тест метода добавления ребра.")
    public void testAddEdge() {
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        // добавляем ребро между существующими несмежными вершинами
        Assertions.assertTrue(graph.addEdge(vertex1, vertex2));

        // добавляем ребро между существующими смежными вершинами
        Assertions.assertFalse(graph.addEdge(vertex1, vertex2));

        // добавляем ребро между несуществующей и сущестующей вершинами
        Assertions.assertFalse(graph.addEdge(vertex4, vertex2));

    }

    @Test
    @DisplayName("Тест метода удаления ребра.")
    public void testRemoveEdge() {
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addEdge(vertex1, vertex2);

        // удаляем несуществующее ребро
        Assertions.assertFalse(graph.removeEdge(vertex1, vertex3));

        // удаляем существующее ребро
        Assertions.assertTrue(graph.removeEdge(vertex1, vertex2));

        // удаляем несуществующее ребро с несуществующей вершиной
        Assertions.assertFalse(graph.removeEdge(vertex4, vertex1));

    }

    @Test
    @DisplayName("Тест метода удаления вершины.")
    public void testRemoveVertex() {
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addEdge(vertex1, vertex2);

        // удаление несуществующей
        Assertions.assertFalse(graph.removeVertex(vertex4));

        // удаление существующей несмежной вершины
        Assertions.assertTrue(graph.removeVertex(vertex3));

        // удаление вершины из которой выходит ребро
        Assertions.assertTrue(graph.removeVertex(vertex1));

        // проверка существования ребра, соединяющего удаленную вершину с существующей
        Assertions.assertFalse(graph.isEdgeExistence(vertex1, vertex2));

        // в графе осталась одна вершина
        Assertions.assertEquals(1, graph.getVertexesList().size());
    }

    @Test
    @DisplayName("Тест методов существования вершины и ребра.")
    public void testIsEdgeAndVertexExistence() {
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addEdge(vertex1, vertex2);

        // проверка существования вершины
        Assertions.assertTrue(graph.isVertexExistence(vertex1));

        // проверка существования ребра
        Assertions.assertTrue(graph.isEdgeExistence(vertex1, vertex2));

        graph.removeVertex(vertex1);

        // проверка существования удаленной вершины
        Assertions.assertFalse(graph.isVertexExistence(vertex1));

        // проверка существования ребра, соединяющего удаленную вершину с существующей
        Assertions.assertFalse(graph.isEdgeExistence(vertex1, vertex2));
    }
}
