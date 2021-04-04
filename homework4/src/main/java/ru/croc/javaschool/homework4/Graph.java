package ru.croc.javaschool.homework4;

import java.util.*;

/**
 * Граф.
 * Представлен списком смежности вершин.
 *
 * @param <T> тип.
 */
public class Graph<T> {
    /**
     * Список смежности вершин.
     */
    private Map<Vertex<T>, Set<Vertex<T>>> vertexesList;

    /**
     * Список компонент связности.
     */
    private Map<Integer, ConnectivityComponent<T>> connectivityComponents;

    /**
     * Создание пустого графа.
     */
    public Graph() {
        this.vertexesList = new HashMap<>();
        this.connectivityComponents = new TreeMap<>();
    }

    public Map<Vertex<T>, Set<Vertex<T>>> getVertexesList() {
        return vertexesList;
    }

    public void setVertexesList(Map<Vertex<T>, Set<Vertex<T>>> vertexesList) {
        this.vertexesList = vertexesList;
    }

    public Map<Integer, ConnectivityComponent<T>> getConnectivityComponents() {
        return connectivityComponents;
    }

    /**
     * Считает количество компонент связности.
     *
     * @return количество компонент связности
     */
    public int getAmountOfConnectivityComponents() {
        return connectivityComponents.size();
    }

    /**
     * Существует ли вершина.
     *
     * @param vertex вершина
     * @return true если существует, false если не существует
     */
    public boolean isVertexExistence(Vertex<T> vertex) {
        return vertexesList.containsKey(vertex);
    }

    /**
     * Существует ли ребро.
     *
     * @param vertex1 первая вершина
     * @param vertex2 вторая вершина
     * @return true если существует, false если не существует
     */
    public boolean isEdgeExistence(Vertex<T> vertex1, Vertex<T> vertex2) {
        if (!isVertexExistence(vertex1) || !isVertexExistence(vertex2)) {
            return false;
        }
        return vertexesList.get(vertex1).contains(vertex2);
    }

    /**
     * Пересчёт компонент связности.
     * Используется алгоритм поиска в глубину.
     * Компоненты связности располагаются по возрастанию количества вершин в компоненте.
     */
    public void updateConnectComponents() {
        connectivityComponents = new TreeMap<>();

        //список посещенных вершин
        List<Vertex<T>> used = new ArrayList<>();

        // выполняется, пока все вершины не посещены
        while (!used.containsAll(vertexesList.keySet())) {
            // top - вершина, из которой будем посещать другие вершины, смежные с ней;
            // по умолчанию равна первому элементу списка вершин-ключей;
            // если найдется другая непосещенная вершина, то top становится ей
            Vertex<T> top = vertexesList.keySet().stream().findFirst().get();

            for (Vertex<T> v: vertexesList.keySet()) {
                if (!used.contains(v)) {
                    top = v;
                }
            }

            // key - ключ для добавления элементов в компонент связности;
            // если список компонент связности пуст, key = 1,
            // иначе - максимальному значению ключа + 1 среди всех существующих ключей
            Integer key = 1;
            if (!connectivityComponents.keySet().isEmpty()) {
                key = connectivityComponents.keySet().stream().max(Integer::compareTo).get() + 1;
            }

            connectivityComponents.put(key, new ConnectivityComponent<>());

            used.add(top);

            // создаем стек и добавляем в него посещенную вершину top
            Stack<Vertex<T>> stack = new Stack<>();
            stack.push(top);

            while (!stack.empty()) {
                // добавляем в стек все непосещенные вершины, смежные с top
                for (Vertex<T> v: vertexesList.get(top)) {
                    if (!stack.contains(v) && !used.contains(v)) {
                        stack.push(v);
                    }
                }

                // если смежных с top вершин нет или вершина, находящаяся на стеке посещена,
                // извлекаем из стека вершину в компонент связности по ключу key;
                // иначе присваиваем top значение элемена вершины стека и отмечаем как посещенную
                if (top == stack.peek() || used.contains(stack.peek())) {
                    Set<Vertex<T>> newList = connectivityComponents.get(key).getVertexes();
                    newList.add(stack.pop());
                    ConnectivityComponent<T> newComp = new ConnectivityComponent<>();
                    newComp.setVertexes(newList);
                    connectivityComponents.put(key, newComp);
                }
                else {
                    top = stack.peek();
                    used.add(top);
                }
            }
            // создаем новый ключ для компоненты связности, основанный на количетсве элементов в ней
            // и добавляем в map, удаляя элемент со старым ключом
            ConnectivityComponent<T> newComp = connectivityComponents.remove(key);
            key = newComp.getVertexes().size();
            searchSame(key);
            connectivityComponents.put(key, newComp);
        }
    }

    /**
     * Поиск ключа совпадающего с key.
     * Если ключ найден происходит сдвиг всех ключей, больше или равных key на +1.
     *
     * @param key ключ
     */
    public void searchSame(int key) {
        if (connectivityComponents.containsKey(key)) {
            Map<Integer, ConnectivityComponent<T>> connectivityComponents2 = new TreeMap<>();

            connectivityComponents.keySet().forEach(i -> {
                if (i >= key) {
                    connectivityComponents2.put(i + 1, connectivityComponents.get(i));
                }
                else {
                    connectivityComponents2.put(i, connectivityComponents.get(i));
                }
            });
            connectivityComponents = connectivityComponents2;
        }
    }

    /**
     * Добавление вершины.
     *
     * @param vertex вершина
     */
    public void addVertex(Vertex<T> vertex){
        vertexesList.putIfAbsent(vertex, new HashSet<>());
        searchSame(1);
        connectivityComponents.put(1, new ConnectivityComponent<T>(vertex));
    }

    /**
     * Удаление вершины.
     *
     * @param vertex вершина
     * @return true - если вершина успешно удалилась, false - если вершины не существует
     */
    public boolean removeVertex(Vertex<T> vertex) {
        if (!isVertexExistence(vertex)) {
            return false;
        }

        for (Set<Vertex<T>> value: vertexesList.values()) {
                value.remove(vertex);
        }
        vertexesList.remove(vertex);

        updateConnectComponents();

        return true;
    }

    /**
     * Добавление ребра между двумя вершинами.
     *
     * @param vertex1 первая вершина
     * @param vertex2 вторая вершина
     * @return true - если ребро успешно добавилось, false - если ребро уже существует
     */
    public boolean addEdge(Vertex<T> vertex1, Vertex<T> vertex2){
        if (isEdgeExistence(vertex1, vertex2)) {
            return false;
        }

        vertexesList.get(vertex1).add(vertex2);
        vertexesList.get(vertex2).add(vertex1);

        updateConnectComponents();

        return true;
    }

    /**
     * Удаление ребра, соединяющего две вершины.
     *
     * @param vertex1 первая вершина
     * @param vertex2 вторая вершина
     * @return true - если ребро успешно удалилось, false - если не существует ребра
     */
    public boolean removeEdge(Vertex<T> vertex1, Vertex<T> vertex2){
        if (!isEdgeExistence(vertex1, vertex2)) {
            return false;
        }

        vertexesList.get(vertex1).remove(vertex2);
        vertexesList.get(vertex2).remove(vertex1);

        updateConnectComponents();

        return true;
    }


    @Override
    public String toString() {
        return "Graph{" +
                "vertexesList=" + vertexesList +
                ", connectivityComponents=" + connectivityComponents +
                '}';
    }
}
