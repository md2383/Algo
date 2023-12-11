package HW6;
import java.util.Scanner;

public class NegativeCycle {

    public static String negativeCycle(int[][] graph, int n, int m) {

        int[] length = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            length[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n - 1; i++) { // O(n^2)
            for (int j = 0; j < m; j++) {
                int u = graph[j][0];
                int v = graph[j][1];
                int w = graph[j][2];

                if (length[u] != Integer.MAX_VALUE && length[u] + w < length[v]) {
                    length[v] = length[u] + w;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int u = graph[i][0];
            int v = graph[i][1];
            int w = graph[i][2];

            if ((length[u] != Integer.MAX_VALUE) && (length[u] + w < length[v])) {
                return "YES";
            }
        }
        return "NO";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] graph = new int[m][3];

        for (int i = 0; i < m; i++) {
            graph[i][0] = sc.nextInt();
            graph[i][1] = sc.nextInt();
            graph[i][2] = sc.nextInt();
        }

        String result = negativeCycle(graph, n, m);
        System.out.println(result);
    }
}
