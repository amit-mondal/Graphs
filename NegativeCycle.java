import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static final int infinity = Integer.MAX_VALUE;
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        return bellmanFord(adj, cost);
    }

    private static int bellmanFord(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int[] prev = new int[adj.length];
        int[] dist = new int[adj.length];
        for (int i = 0;i<prev.length;i++) {
            prev[i] = -1;
            prev[i] = infinity;
        }
        for (int i = 0;i<adj.length - 1;i++) {
            for (int u = 0;u < adj.length;u++) {
                for (int j = 0;j<adj[u].size();j++) {
                    Integer v = adj[u].get(j);
                    Integer weightFromU = cost[u].get(j);
                    if (dist[v] > dist[u] + weightFromU && dist[u] != infinity) {
                        dist[v] = dist[u] + weightFromU;
                        prev[v] = u;
                    }
                }
            }
        }
        //one iteration past |V-1|
        for (int u = 0;u < adj.length;u++) {
            for (int j = 0;j<adj[u].size();j++) {
                Integer v = adj[u].get(j);
                Integer weightFromU = cost[u].get(j);
                if (dist[v] > dist[u] + weightFromU && dist[u] != infinity) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

