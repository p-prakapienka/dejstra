package by.prakapienka.graphdejkstra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestMain {

    private static final Logger LOG = LoggerFactory.getLogger(TestMain.class);

    private static int startVertex = 0; //стартовая вершина, от которой ищется расстояние до всех других

    public static void main(String[] args) throws IOException {
        Graph graph = TestData.getTestGraph();

        LOG.info("Processing graph:\n{}", graph);

        GraphResult result =  GraphProcessor.process(graph, startVertex);

        System.out.println("Start vertex " + result.getStartVertex());
        for (int i = 0; i < graph.getVertexNumber(); i++) {
            System.out.println("Vertex " + i + " distance: " + result.getDistanceToVertex(i));
            result.getWayToVertex(i).forEach(v -> System.out.print("->" + v));
            System.out.println();
        }
    }

}

