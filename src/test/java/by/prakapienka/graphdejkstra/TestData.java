package by.prakapienka.graphdejkstra;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final List[] TEST_MATRIX = {
            Arrays.asList(null,  10,   30,   50,   10 ),
            Arrays.asList(null, null, null, null, null),
            Arrays.asList(null, null, null, null,  10 ),
            Arrays.asList(null,  40,   20,  null, null),
            Arrays.asList( 10,  null,  10,   30,  null),
    };

    public static Graph getTestGraph() {

        Graph graph = new Graph.Builder(5)
                .edge(0, 1, 10)
                .edge(0, 2, 30)
                .edge(0, 3, 50)
                .edge(0, 4, 10)
                .edge(2, 4, 10)
                .edge(3, 1, 40)
                .edge(3, 2, 20)
                .edge(4, 0, 10)
                .edge(4, 2, 10)
                .edge(4, 3, 30)
                .build();

        return graph;
    }

}
