package by.prakapienka.graphdejkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Root directed graph object. Contains a matrix of edge weights where row number
 *  is an edge start vertex and column number is an edge end vertex. null means
 *  there is no connection between vertices.
 *
 *  You cannot create a Graph object directly. Use nested builder class instead.
 *
 *  @author Paviel Prakapienka
 */
public final class Graph {

    private final int vertexNumber;
    private int edgeNumber;

    private final List<Integer>[] graphMatrix;

    /**
     *  @return number of vertices (rows/columns in edge weights matrix). Edge weight
     *  can have null value, means there is no connection between vertices.
     */
    public int getVertexNumber() {
        return vertexNumber;
    }

    /**
     *  @return total of directed graph edges
     */
    public int getEdgeNumber() {
        return edgeNumber;
    }

    /**
     *  @return edge weights matrix
     */
    public List<Integer>[] getGraphMatrix() {
        return graphMatrix;
    }

    /**
     *  Builder class to create a Graph object.
     */
    @SuppressWarnings("unchecked")
    public static class Builder {
        private final int vertexNumber;
        private int edgeNumber = 0;
        private List<Integer>[] graphMatrix;

        /**
         *  @param vertexNumber initial number of vertices. Must be greater than 1.
         *  @throws IllegalArgumentException
         */
        public Builder(int vertexNumber) {
            if (vertexNumber < 2) {
                throw new IllegalArgumentException(
                        "Graph must have at least 2 vertices.");
            }
            this.vertexNumber = vertexNumber;
            graphMatrix = new ArrayList[vertexNumber];
            for (int i = 0; i < graphMatrix.length; i++) {
                graphMatrix[i] = new ArrayList<>(vertexNumber);
                for (int j = 0; j < vertexNumber; j++) {
                    graphMatrix[i].add(null);
                }
            }
        }

        /**
         *  Adds an edge for the building Graph. At least one edge must be added.
         *  @param startVertex edge start. Must be in range [0, vertexNumber - 1]
         *  @param endVertex edge end. Must be in range [0, vertexNumber - 1] and
         *  not be equal to startVertex
         *  @param weight edge weight. Must be a positive integer.
         *
         *  @throws IllegalArgumentException in case of incorrect parameters.
         *  @return Builder object which can be safely used to construct Graph object
         *  calling its build() method.
         */
        public Builder edge(int startVertex, int endVertex, int weight) {
            if (startVertex < 0 || endVertex < 0) {
                throw new IllegalArgumentException(
                        "Vertices must be positive integers.");
            }
            if (startVertex > vertexNumber - 1 || endVertex > vertexNumber - 1) {
                throw new IllegalArgumentException(
                        "Vertex is out of range: [" + 0 + ", " + (vertexNumber - 1) + "].");
            }
            if (startVertex == endVertex) {
                throw new IllegalArgumentException(
                        "Vertex cannot be connected to itself.");
            }
            if (weight <= 0) {
                throw new IllegalArgumentException(
                        "Weight must have a positive value.");
            }
            graphMatrix[startVertex].set(endVertex, weight);
            this.edgeNumber++;
            return this;
        }

        /**
         * @throws IllegalStateException if no edges have been added.
         * @return correctly constructed Graph object.
         */
        public Graph build() {
            if (edgeNumber < 1) {
                throw new IllegalStateException("Graph must have at least one edge.");
            }

            return new Graph(this);
        }
    }

    private Graph(Builder builder) {
        this.vertexNumber = builder.vertexNumber;
        this.edgeNumber = builder.edgeNumber;
        this.graphMatrix = builder.graphMatrix;
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
