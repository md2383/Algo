package HW6;

import java.util.Scanner;

public class Daycare {

    static String[] dressChildren(int n, int a, int b, int c, int[][] preferences) {
        StringBuilder result = new StringBuilder("YES\n");
        int[][] graph = new int[n][a * b * c];
        int[] matching = new int[a * b * c];
        boolean[] visited = new boolean[a * b * c];

        int edgeCount = 0;
        for (int i = 0; i < n; i++) {
            int[] hats = preferences[i * 3];
            int[] mittens = preferences[i * 3 + 1];
            int[] jackets = preferences[i * 3 + 2];

            for (int hat : hats) {
                for (int mitten : mittens) {
                    for (int jacket : jackets) {
                        int clothingItem = (hat - 1) * b * c + (mitten - 1) * c + (jacket - 1);
                        graph[i][edgeCount++] = clothingItem;
                    }
                }
            }
        }

        if (!bipartiteMatching(graph, matching, visited)) {
            return new String[] { "NO" };
        }

        for (int i = 0; i < n; i++) {
            int clothingItem = matching[i];
            int hat = (clothingItem / (b * c)) + 1;
            int mitten = ((clothingItem % (b * c)) / c) + 1;
            int jacket = ((clothingItem % (b * c)) % c) + 1;

            result.append(hat).append(" ").append(mitten).append(" ").append(jacket).append("\n");
        }

        return result.toString().split("\n");
    }

    static boolean bipartiteMatching(int[][] graph, int[] matching, boolean[] visited) {
        for (int i = 0; i < graph.length; i++) {
            visited[i] = false;
            matching[i] = -1;
        }

        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 0) {
                    break;
                }

                if (dfs(i, graph, matching, visited)) {
                    count++;
                }
            }
        }

        return count == graph.length;
    }

    static boolean dfs(int child, int[][] graph, int[] matching, boolean[] visited) {
        for (int i = 0; i < graph[child].length; i++) {
            int clothingItem = graph[child][i];
            if (clothingItem == 0) {
                break;
            }

            if (!visited[clothingItem]) {
                visited[clothingItem] = true;

                if (matching[clothingItem] == -1 || dfs(matching[clothingItem], graph, matching, visited)) {
                    matching[clothingItem] = child;
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        int[][] preferences = new int[n * 3][];
        for (int i = 0; i < n * 3; i++) {
            int preferenceCount = scanner.nextInt();
            preferences[i] = new int[preferenceCount];
            for (int j = 0; j < preferenceCount; j++) {
                preferences[i][j] = scanner.nextInt();
            }
        }

        dressChildren(n, a, b, c, preferences);
    }
}
