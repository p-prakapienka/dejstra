package by.prakapienka.graphdejkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    public static Graph readGraphData() throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            System.out.println("Введите количество вершин графа.");
            int vertexNumber = Integer.parseInt(reader.readLine());
            System.out.println("Введите количество ребер графа.");
            int edgeNumber = Integer.parseInt(reader.readLine());
            System.out.println("Введите стартовую вершину.");
            int startVertex = Integer.parseInt(reader.readLine()) - 1;

            Graph.Builder graphBuilder = new Graph.Builder(vertexNumber);

            System.out.println("Введите параметры ребер графа в формате:");
            System.out.println("startVertexNumber endVertexNumber edgeWeight");
            for (int i = 0; i < edgeNumber; ++i) {
                String[] edgeData = reader.readLine().split("\\s");
                int u = Integer.parseInt(edgeData[0]);
                int v = Integer.parseInt(edgeData[1]);
                int w = Integer.parseInt(edgeData[2]);
                graphBuilder = graphBuilder.edge(u, v, w);
                /*
                    adjacency[u].add(v);
                    weight[u].add(w);
                 */
            }
            return graphBuilder.build();
        }
    }

    //вывод полученной матрицы смежности
    public static void printGraphMatrix(Graph graph) {
        System.out.println();
        for (int i = 0; i < graph.getVertexNumber(); i++) {
            List<Integer> weights = graph.getGraphMatrix()[i];
            for (int j = 0; j < graph.getVertexNumber(); j++) {
                if (weights.get(j) != null) {
                    System.out.print(weights.get(j) + " ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //процедура восстановления кратчайшего пути по массиву предком
    private static void printWay(Graph graph, int v, int start) {
        if (v == -1) {
            return;
        }
        printWay(graph, graph.getPrevious()[v], start);
        if (v == start) {
            System.out.print(v + 1);
        } else {
            System.out.print("->" + (v + 1));
        }
    }

    //процедура вывода данных в консоль
    public static void printData(Graph graph, int start) {
        for (int v = 0; v < graph.getVertexNumber(); ++v) {
            if (graph.getDistance()[v] != Graph.INF) {
                System.out.print(graph.getDistance()[v] + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
        for (int v = 0; v < graph.getVertexNumber(); ++v) {
            System.out.print((v + 1) + ": ");
            if (graph.getDistance()[v] != Graph.INF) {
                printWay(graph, v, start);
            }
            System.out.println();
        }
    }

}
