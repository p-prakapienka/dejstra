package by.prakapienka.graphdejkstra;

import by.prakapienka.graphdejkstra.util.ConsoleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestMain {

    private static final Logger LOG = LoggerFactory.getLogger(TestMain.class);

    private static int startVertex = 0; //стартовая вершина, от которой ищется расстояние до всех других

    public static void main(String[] args) throws IOException {
        Graph graph = TestData.getTestGraph();

        LOG.info("Processing graph:\n{}", graph);

        GraphProcessor.process(graph, startVertex);

        ConsoleHelper.printData(graph, startVertex);
    }

}

