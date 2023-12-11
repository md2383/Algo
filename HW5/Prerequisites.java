package HW5;
import java.util.Scanner;

public class Prerequisites {

    private int V;
    private int[][] adj;
    private int max = 0;

    public Prerequisites(int V) {
        this.V = V;
        adj = new int[V + 1][101];
    }

    public void setADJ(int[][] adj) {
        this.adj = adj;
    }

    public int findCount() {
        boolean[] visited = new boolean[V + 1];
        int[] maxValues = new int[V + 1];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                DFS(v, visited, maxValues);
            }
        }

        int maxValue = 0;
        for (int v = 0; v < V; v++) {
            maxValue = Math.max(maxValue, maxValues[v]);
        }

        return maxValue;
    }

    private void DFS(int v, boolean[] visited, int[] maxValues) {
        visited[v] = true;
        if (adj[v][0] == 0) {
            maxValues[v] = 1;
        }

        for (int w = 1; w < adj[v].length; w++) {

            int neighbour = adj[v][w];
            if (neighbour == 0) {
                break;
            } else if (!visited[neighbour]) {
                DFS(neighbour, visited, maxValues);
            }
            maxValues[v] = Math.max(maxValues[v], maxValues[neighbour] + 1);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n;

        n = sc.nextInt();

        Prerequisites p = new Prerequisites(n);

        // loop through each course and add its prerequisites

        int[][] adj = new int[n + 2][102];
        for (int i = 1; i < n + 1; i++) {

            int prereq = sc.nextInt();
            int j = 1;

            while (prereq != 0) {
                adj[i][j] = prereq;
                j++;
                prereq = sc.nextInt();
            }
        }
        p.setADJ(adj);

        int k = p.findCount();

        System.out.println(k); // to remove 0 component
    }
}
