package by.prakapienka.graphdejkstra;

import org.junit.Assert;
import org.junit.Test;

import static by.prakapienka.graphdejkstra.TestData.TEST_MATRIX;

public class GraphBulderTest {

    @Test
    public void testBuildSuccess() {
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
        Assert.assertArrayEquals(graph.getGraphMatrix(), TEST_MATRIX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeVertexIndex() {
        new Graph.Builder(3).edge(-1, 0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVertexIndexOutOfRange() {
        new Graph.Builder(3).edge(1, 3, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalVertexNumber() {
        new Graph.Builder(1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSameEndAndStartVertexForEdge() {
        new Graph.Builder(2).edge(1, 1, 1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalEdgeWeight() {
        new Graph.Builder(2).edge(0, 1, -1).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testGraphWithoutEdges() {
        new Graph.Builder(3).build();
    }

}
