import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {


    private static void explore(ArrayList<Integer>[] adj, int v, boolean[] visited, int[] ccnum, int cc) {
        visited[v] = true;
        ccnum[v] = cc;
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                explore(adj, i, visited, ccnum, cc);
            }
        }
    }

    private static int dfs(ArrayList<Integer>[] adj) {
        int cc = 1;
        boolean[] visited = new boolean[adj.length];
        int[] ccnum = new int[adj.length];
        for (int i = 0;i<adj.length;i++) {
            if (!visited[i]) {
                explore(adj, i, visited, ccnum, cc);
                cc++;
            }
        }
        return cc-1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(dfs(adj));
    }
}

