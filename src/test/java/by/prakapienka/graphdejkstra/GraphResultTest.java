package by.prakapienka.graphdejkstra;

import org.junit.Assert;
import org.junit.Test;

public class GraphResultTest {

    @Test
    public void testGetWayAndDistance() {
        GraphResult result = GraphProcessor.process(TestData.getTestGraph(), 0);
        Assert.assertArrayEquals(
                result.getWayToVertex(2).toArray(),
                new Integer[]{0, 4, 2});
        Assert.assertEquals(result.getDistanceToVertex(2), 20);
    }

    @Test
    public void testNoConnectionBetweenVertices() {
        GraphResult result = GraphProcessor.process(TestData.getTestGraph(), 1);
        Assert.assertEquals(result.getDistanceToVertex(2), -1);
        Assert.assertTrue(result.getWayToVertex(3).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWayVertexOutOfRange() {
        GraphResult result = GraphProcessor.process(TestData.getTestGraph(), 0);
        result.getWayToVertex(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDistanceVertexOutOfRange() {
        GraphResult result = GraphProcessor.process(TestData.getTestGraph(), 0);
        result.getWayToVertex(5);
    }
}
