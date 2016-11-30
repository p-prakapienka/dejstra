package by.prakapienka.graphdejkstra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 *  Util class to process the Graph object with Dijkstra's algorithm which means
 *  to find the shortest way from the specified vertex to all it is connected with.
 *  The result of this process is a GraphResult object with all the needed data in it.
 *
 *  Not thread safe!!!
 *
 *  @author Paviel Prakapienka
 */
public class GraphProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(GraphProcessor.class);

    private final static int INF = Integer.MAX_VALUE / 2;

    private static boolean[] used; //массив для хранения информации о пройденных и не пройденных вершинах
    private static int[] distance; //массив для хранения расстояний от стартовой вершины
    private static int[] previous; //массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины

    /**
     *  Root method for graph processing. Method finds the ways and distances from the
     *  root vertex to all the other available vertices root vertex has a connection with
     *  @param graph well-formed graph object to find distances from the root vertex
     *  @param startVertex root vertex, must be in range [0, Graph.vertexNumber - 1]
     *
     *  @return result object, containing initial unprocessed graph object, distances
     *  and ways from the root vertex.
     *  @throws IllegalArgumentException in case of incorrect parameters
     */
    public static GraphResult process(Graph graph, int startVertex) {
        if (graph == null) {
            LOG.error("Graph object is null.");
            throw new IllegalArgumentException("Graph object is null.");
        }
        int vertexNumber = graph.getVertexNumber();
        if (startVertex < 0 || startVertex > vertexNumber - 1) {
            LOG.error("Root vertex is out of range.");
            throw new IllegalArgumentException("Root vertex is out of range.");
        }
        LOG.info("Start processing from vertex {}\n for {}", startVertex, graph);

        prepareToProcess(vertexNumber);

        distance[startVertex] = 0; //кратчайшее расстояние до стартовой вершины равно 0
        for (int i = 0; i < vertexNumber; i++) {
            //сбрасываем номер текущей вершины и минимальное расстояние до неё
            int v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int j = 0; j < vertexNumber; j++) {
                if (used[j]) {
                    continue;
                }
                if (distV < distance[j]) {
                    continue;
                }
                v = i;
                distV = distance[j];
            }

            for (int j = 0; j < vertexNumber; j++) {
                int u = j;
                Integer weightU = graph.getGraphMatrix()[v].get(j);
                if (weightU == null) {
                    continue;
                }
                //релаксация вершины
                if (distance[v] + weightU < distance[u]) {
                    distance[u] = distance[v] + weightU;
                    previous[u] = v;
                }
            }
            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
        LOG.info("Processing finished.");
        LOG.info("Preparing result.");
        GraphResult result = new GraphResult(graph, distance, previous, startVertex);
        if (result != null) {
            LOG.info("Result successfully builded.");
        } else {
            LOG.error("Result is null.");
        }
        LOG.info("Clearing processor state.");
        used = null;
        distance = null;
        previous = null;
        return result;
    }


    private static void prepareToProcess(int vertexNumber) {
        //ни одна вершина не пройдена
        used = new boolean[vertexNumber];
        Arrays.fill(used, false);
        //ни одна вершина не имеет предыдущей вершины
        previous = new int[vertexNumber];
        Arrays.fill(previous, -1);
        //расстояния до всех вершин равны бесконечности
        distance = new int[vertexNumber];
        Arrays.fill(distance, INF);
    }

    private GraphProcessor() {}

}
