import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphDejkstra {

    private final static int INF = Integer.MAX_VALUE / 2;

    private int n; //количество вершин в орграфе
    private int m; //количествое дуг в орграфе

    private ArrayList<Integer>[] adjacency; //список смежности
    private ArrayList<Integer>[] weight; //вес ребра в орграфе
    private boolean[] used; //массив для хранения информации о пройденных и не пройденных вершинах
    private int[] distance; //массив для хранения расстояний от стартовой вершины
    //массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    private int[] previous;
    private int start; //стартовая вершина, от которой ищется расстояние до всех других

    private BufferedReader reader;

    //процедура запуска алгоритма Дейкстры из стартовой вершины
    private void processGraph(int start) {
        distance[start] = 0; //кратчайшее расстояние до стартовой вершины равно 0
        for (int i = 0; i < n; i++) {
            //сбрасываем номер текущей вершины и минимальное расстояние до неё
            int v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int j = 0; j < n; j++) {
                if (used[j]) {
                    continue;
                }
                if (distV < distance[j]) {
                    continue;
                }
                v = i;
                distV = distance[j];
            }
            //рассматриваем все дуги, исходящие из найденной вершины
            for (int j = 0; j < adjacency[v].size(); j++) {
                int u = adjacency[v].get(j);
                int weightU = weight[v].get(j);
                //релаксация вершины
                if (distance[v] + weightU < distance[u]) {
                    distance[u] = distance[v] + weightU;
                    previous[u] = v;
                }
            }
            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
    }

    @SuppressWarnings("unchecked")
    private void readGraphData() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите количество вершин графа.");
        n = Integer.parseInt(reader.readLine());
        System.out.println("Введите количество ребер графа.");
        m = Integer.parseInt(reader.readLine());
        System.out.println("Введите стартовую вершину.");
        start = Integer.parseInt(reader.readLine()) - 1;

        initAdjacencyAndWeight();

        System.out.println("Введите параметры ребер графа в формате:");
        System.out.println("startVertexNumber endVertexNumber edgeWeight");
        for (int i = 0; i < m; ++i) {
            String[] edgeData = reader.readLine().split("\\s");
            int u = Integer.parseInt(edgeData[0]);
            int v = Integer.parseInt(edgeData[1]);
            int w = Integer.parseInt(edgeData[2]);
            u--;
            v--;
            adjacency[u].add(v);
            weight[u].add(w);
        }

        fillUsedAndPreviousAndDistance();

        reader.close();
    }

    private void useTestGraph() {
        int[][] testMatrix = {{-1, 10, 30, 50, 10},
                              {-1, -1, -1, -1, -1},
                              {-1, -1, -1, -1, 10},
                              {-1, 40, 20, -1, -1},
                              {10, -1, 10, 30, -1}};
        n = 5;
        m = 10;
        start = 0;

        initAdjacencyAndWeight();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (testMatrix[i][j] != -1) {
                    adjacency[i].add(j);
                    weight[i].add(testMatrix[i][j]);
                }
            }
        }

        fillUsedAndPreviousAndDistance();
    }

    private void initAdjacencyAndWeight() {
        //инициализируем список смежности графа размерности n
        adjacency = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacency[i] = new ArrayList<>();
        }
        //инициализация списка весов ребер
        weight = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            weight[i] = new ArrayList<>();
        }
    }

    private void fillUsedAndPreviousAndDistance() {
        //Исходные данные:
        //ни одна вершина не пройдена
        used = new boolean[n];
        Arrays.fill(used, false);
        //ни одна вершина не имеет предыдущей вершины
        previous = new int[n];
        Arrays.fill(previous, -1);
        //расстояния до всех вершин равны бесконечности
        distance = new int[n];
        Arrays.fill(distance, INF);
    }

    //вывод полученной матрицы смежности
    private void printAdjacencyMatrix() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> adj = adjacency[i];
            for (int j = 0; j < n; j++) {
                if (adj.contains(j)) {
                    System.out.print(weight[i].get(adj.indexOf(j)) + " ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //процедура восстановления кратчайшего пути по массиву предком
    private void printWay(int v) {
        if (v == -1) {
            return;
        }
        printWay(previous[v]);
        if (v == start) {
            System.out.print(v + 1);
        } else {
            System.out.print("->" + (v + 1));
        }
    }

    //процедура вывода данных в консоль
    private void printData() {
        for (int v = 0; v < n; ++v) {
            if (distance[v] != INF) {
                System.out.print(distance[v] + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
        for (int v = 0; v < n; ++v) {
            System.out.print((v + 1) + ": ");
            if (distance[v] != INF) {
                printWay(v);
            }
            System.out.println();
        }
    }

    private void run() {
        /*try {
            readGraphData();
        } catch (IOException e) {
            throw new RuntimeException();
        }*/
        useTestGraph();
        printAdjacencyMatrix();
        processGraph(start);
        printData();
    }

    public static void main(String[] args) throws IOException {
        new GraphDejkstra().run();
    }

}

