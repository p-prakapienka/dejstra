package by.prakapienka.graphdejkstra;

import org.junit.Assert;
import org.junit.Test;

public class GraphProcessorTest {

    @Test
    public void testProcessedSuccessful() {
        GraphResult result = GraphProcessor.process(TestData.getTestGraph(), 0);
        Assert.assertEquals(result.getDistanceToVertex(3), 40);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGraphIsNull() {
        GraphProcessor.process(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartVertexOutOfRange() {
        GraphProcessor.process(TestData.getTestGraph(), 5);
    }
}
