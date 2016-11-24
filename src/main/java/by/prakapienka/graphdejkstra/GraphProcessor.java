package by.prakapienka.graphdejkstra;

public class GraphProcessor {

    //процедура запуска алгоритма Дейкстры из стартовой вершины
    public static void process(Graph graph, int startVertex) {
        int vertexNumber = graph.getVertexNumber();
        graph.getDistance()[startVertex] = 0; //кратчайшее расстояние до стартовой вершины равно 0
        for (int i = 0; i < vertexNumber; i++) {
            //сбрасываем номер текущей вершины и минимальное расстояние до неё
            int v = -1;
            int distV = Graph.INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int j = 0; j < vertexNumber; j++) {
                if (graph.getUsed()[j]) {
                    continue;
                }
                if (distV < graph.getDistance()[j]) {
                    continue;
                }
                v = i;
                distV = graph.getDistance()[j];
            }
            //рассматриваем все дуги, исходящие из найденной вершины
            /*for (int j = 0; j < adjacency[v].size(); j++) {
                int u = adjacency[v].get(j);
                int weightU = weight[v].get(j);
                //релаксация вершины
                if (graph.getDistance()[v] + weightU < graph.getDistance()[u]) {
                    graph.getDistance()[u] = graph.getDistance()[v] + weightU;
                    graph.getPrevious()[u] = v;
                }
              }
            }*/
            for (int j = 0; j < vertexNumber; j++) {
                int u = j;
                Integer weightU = graph.getGraphMatrix()[v].get(j);
                if (weightU == null) {
                    continue;
                }
                //релаксация вершины
                if (graph.getDistance()[v] + weightU < graph.getDistance()[u]) {
                    graph.getDistance()[u] = graph.getDistance()[v] + weightU;
                    graph.getPrevious()[u] = v;
                }
            }
            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            graph.getUsed()[v] = true;
        }
    }
}
