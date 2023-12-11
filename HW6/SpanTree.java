package HW6;

import java.util.Scanner;

public class SpanTree {

    private static int findMinCostFContainingSpanningTree(int[][] graph, int n) {

        int minCost = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] < 0) {
                    // Edge is part of F, include it in the spanning tree
                    minCost += (-graph[i][j]);
                    // Mark the edge as visited by setting its weight to 0
                    graph[i][j] = graph[j][i] = 0;

                    // Update the weights of other edges incident to the same vertices
                    for (int k = 1; k <= n; k++) {
                        if (graph[i][k] < 0) {
                            graph[i][k] = graph[k][i] = 0;
                        }
                        if (graph[j][k] < 0) {
                            graph[j][k] = graph[k][j] = 0;
                        }
                    }
                }
            }
        }

        // Check if the graph is connected
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != 0) {
                    // The graph is not connected
                    return -1;
                }
            }
        }

        return minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // number of vertices
        int m = scanner.nextInt(); // number of edges

        // Initialize the graph
        int[][] graph = new int[n + 1][n + 1];

        // Read and populate the graph
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            int isInF = scanner.nextInt();

            graph[u][v] = graph[v][u] = weight;

            // If the edge is in F, set the corresponding weight in the graph
            if (isInF == 1) {
                graph[u][v] = graph[v][u] = -weight; // negate the weight to mark it as part of F
            }
        }
        int result = findMinCostFContainingSpanningTree(graph, n);

        // Output the result
        System.out.println(result);
    }
}
