package by.prakapienka.graphdejkstra;

import java.io.IOException;

public class TestMain {

    private static int startVertex = 0; //стартовая вершина, от которой ищется расстояние до всех других

    public static void main(String[] args) throws IOException {
        Graph graph = TestData.getTestGraph();

        ConsoleHelper.printGraphMatrix(graph);

        GraphProcessor.process(graph, startVertex);

        ConsoleHelper.printData(graph, startVertex);
    }

}

