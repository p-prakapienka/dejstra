package by.prakapienka.graphdejkstra;

public class TestData {

    public static Graph getTestGraph() {
        //test data
        int[][] testMatrix = {{-1, 10, 30, 50, 10},
                              {-1, -1, -1, -1, -1},
                              {-1, -1, -1, -1, 10},
                              {-1, 40, 20, -1, -1},
                              {10, -1, 10, 30, -1}};
        int vertexNumber = 5;
        int edgeNumber = 10;
        int start = 0;

        //test graph
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
        //test preconditions
        if (graph.getVertexNumber() != vertexNumber) {
            throw new RuntimeException("Graph build failed - incorrect number of vertices.");
        }
        if (graph.getEdgeNumber() != edgeNumber) {
            throw new RuntimeException("Graph build failed - incorrect number of edges.");
        }

        return graph;
    }

}
