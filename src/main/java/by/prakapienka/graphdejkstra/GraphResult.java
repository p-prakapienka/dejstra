package by.prakapienka.graphdejkstra;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *  Result object returned by the GraphProcessor. Contains an initial unprocessed
 *  Graph object and methods to access process results.
 *
 *  @author Paviel Prakapienka
 */
public class GraphResult {

    private final static int INF = Integer.MAX_VALUE / 2;

    private final Graph graph;
    private final int[] distance;
    private final int[] previous;
    private final int start;

    GraphResult(Graph graph, int[] distance, int[] previous, int start) {
        this.graph = graph;
        this.distance = distance;
        this.previous = previous;
        this.start = start;
    }

    /**
     *  @return initial unprocessed Graph object
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     *  @return root vertex result object processed for
     */
    public int getStartVertex() {
        return start;
    }

    /**
     *  @param vertex method finds the shortest way to this vertex
     *  @return list of indices, each index indicates a vertex to pass through
     *  or empty list if no way exists.
     *  For the way 0 -> 1 -> 5 will return {0, 1, 5}.
     *  @throws IllegalArgumentException if vertex is out of graph range
     */
    public List<Integer> getWayToVertex(int vertex) {
        if (vertex > graph.getVertexNumber() - 1) {
            throw new IllegalArgumentException("No vertex found.");
        }
        List<Integer> way = new LinkedList<>();
        if (distance[vertex] != INF) {
            getWay(way, vertex);
        }
        return way.isEmpty() ? Collections.emptyList() : way;
    }

    private void getWay(List<Integer> way, int v) {
        if (v == -1) {
            return;
        }
        getWay(way, previous[v]);
        way.add(v);
    }

    /**
     *  @param vertex must be in range [0, vertexNumber -1]
     *  @return distance to vertex, 0 if it is root vertex, -1 if root vertex is not
     *  connected with the specified vertex
     *  @throws IllegalArgumentException if vertex is out of graph range
     */
    public int getDistanceToVertex(int vertex) {
        if (vertex > graph.getVertexNumber() - 1) {
            throw new IllegalArgumentException("No vertex found.");
        }
        if (distance[vertex] == INF) return -1;
        return distance[vertex];
    }

}
