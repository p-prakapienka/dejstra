package by.prakapienka.graphdejkstra.util;

import by.prakapienka.graphdejkstra.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleHelper.class);

    private static StringBuilder builder;

    public static Graph readGraphData() throws IOException {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            LOG.info("Введите количество вершин графа.");
            int vertexNumber = Integer.parseInt(reader.readLine());
            LOG.info("Введите количество ребер графа.");
            int edgeNumber = Integer.parseInt(reader.readLine());
            LOG.info("Введите стартовую вершину.");
            int startVertex = Integer.parseInt(reader.readLine()) - 1;

            Graph.Builder graphBuilder = new Graph.Builder(vertexNumber);

            LOG.info("Введите параметры ребер графа в формате:");
            LOG.info("startVertexNumber endVertexNumber edgeWeight");
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

    //процедура восстановления кратчайшего пути по массиву предком
    private static void printWay(Graph graph, int v, int start) {
        if (v == -1) {
            return;
        }
        printWay(graph, graph.getPrevious()[v], start);
        if (v == start) {
            builder.append(v + 1);
        } else {
            builder.append("->");
            builder.append(v + 1);
        }
    }

    //процедура вывода данных в консоль
    public static void printData(Graph graph, int start) {
        builder = new StringBuilder();
        builder.append("Processing result:\n");
        for (int v = 0; v < graph.getVertexNumber(); ++v) {
            if (graph.getDistance()[v] != Graph.INF) {
                builder.append(graph.getDistance()[v]);
                builder.append(" ");
            } else {
                builder.append("null ");
            }
        }
        builder.append("\n");
        for (int v = 0; v < graph.getVertexNumber(); ++v) {
            builder.append(v + 1);
            builder.append(": ");
            if (graph.getDistance()[v] != Graph.INF) {
                printWay(graph, v, start);
            }
            builder.append("\n");
        }
        LOG.info(builder.toString());
    }

}
