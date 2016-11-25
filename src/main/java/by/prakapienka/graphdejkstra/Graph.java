package by.prakapienka.graphdejkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    public final static int INF = Integer.MAX_VALUE / 2;

    private final int vertexNumber; //количество вершин в орграфе
    private int edgeNumber = 0; //количествое дуг в орграфе

    private final List<Integer>[] graphMatrix;
    /*
    private ArrayList<Integer>[] adjacency; //список смежности
    private ArrayList<Integer>[] weight; //вес ребра в орграфе
    */

    private boolean[] used; //массив для хранения информации о пройденных и не пройденных вершинах
    private int[] distance; //массив для хранения расстояний от стартовой вершины
    private int[] previous; //массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины

    {
        /*
        //инициализируем список смежности графа размерности vertexNumber
        adjacency = new ArrayList[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            adjacency[i] = new ArrayList<>();
        }
        //инициализация списка весов ребер
        weight = new ArrayList[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            weight[i] = new ArrayList<>();
        }
        */
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public int getEdgeNumber() {
        return edgeNumber;
    }

    public List<Integer>[] getGraphMatrix() {
        return graphMatrix;
    }

    public boolean[] getUsed() {
        return used;
    }

    public int[] getDistance() {
        return distance;
    }

    public int[] getPrevious() {
        return previous;
    }

    @SuppressWarnings("unchecked")
    public static class Builder {
        private final int vertexNumber;
        private int edgeNumber = 0;
        private List<Integer>[] graphMatrix;

        public Builder(int vertexNumber) {
            this.vertexNumber = vertexNumber;
            graphMatrix = new ArrayList[vertexNumber];
            for (int i = 0; i < graphMatrix.length; i++) {
                graphMatrix[i] = new ArrayList<>(vertexNumber);
                for (int j = 0; j < vertexNumber; j++) {
                    graphMatrix[i].add(null);
                }
            }
        }

        public Builder edge(int startVertex, int endVertex, int weight) {
            graphMatrix[startVertex].set(endVertex, weight);
            this.edgeNumber++;
            return this;
        }

        public Graph build() {
            return new Graph(this);
        }
    }

    private Graph(Builder builder) {
        this.vertexNumber = builder.vertexNumber;
        this.edgeNumber = builder.edgeNumber;
        this.graphMatrix = builder.graphMatrix;
        init();
    }

    private void init() {
        //ни одна вершина не пройдена
        used = new boolean[vertexNumber];
        Arrays.fill(used, false);
        //ни одна вершина не имеет предыдущей вершины
        previous = new int[vertexNumber];
        Arrays.fill(previous, -1);
        //расстояния до всех вершин равны бесконечности
        distance = new int[vertexNumber];
        Arrays.fill(distance, INF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Graph)) return false;

        Graph graph = (Graph) o;

        if (getVertexNumber() != graph.getVertexNumber()) return false;
        if (getEdgeNumber() != graph.getEdgeNumber()) return false;
        return Arrays.equals(getGraphMatrix(), graph.getGraphMatrix());
    }

    @Override
    public int hashCode() {
        int result = getVertexNumber();
        result = 31 * result + getEdgeNumber();
        result = 31 * result + Arrays.hashCode(getGraphMatrix());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Graph{\n");
        for (int i = 0; i < this.getVertexNumber(); i++) {
            List<Integer> weights = this.getGraphMatrix()[i];
            for (int j = 0; j < this.getVertexNumber(); j++) {
                if (weights.get(j) != null) {
                    stringBuilder.append(weights.get(j));
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(" - ");
                }
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
