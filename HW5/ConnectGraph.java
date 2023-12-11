package HW5;

import java.util.Scanner;

public class ConnectGraph {
    private int V;
    private int[][] adj;

    public ConnectGraph(int V) {
        this.V = V + 1;
        adj = new int[V + 1][V + 1];
    }

    public void addEdge(int v, int w) {
        adj[v][w] = 1;
        adj[w][v] = 1;
    }

    public int DisjointComponents() {
        boolean[] visited = new boolean[V];
        int disjoint = 0;

        for (int v = 0; v < V; v++) {

            if (!visited[v]) {
                DFS(v, visited);
                disjoint++;
            }
        }

        return disjoint;
    }

    private void DFS(int v, boolean[] visited) {
        visited[v] = true;
        for (int w = 0; w < V; w++) {
            if (adj[v][w] == 1 && !visited[w]) {
                DFS(w, visited);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int v, e;

        v = sc.nextInt();
        e = sc.nextInt();

        ConnectGraph g = new ConnectGraph(v);

        for (int i = 0; i < e; i++) {
            int temp1, temp2;
            temp1 = sc.nextInt();
            temp2 = sc.nextInt();
            g.addEdge(temp1, temp2);
        }

        int k = g.DisjointComponents();

        System.out.println(k - 2); // to remove 0 component and the very last component (extra)
    }
}
