
/*
   @auth Amit Mondal
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Maze {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        boolean[] visited = new boolean[adj.length];
        if (explore(adj, x, y, visited, 0)) return 1;
        return 0;
    }

    private static boolean explore(ArrayList<Integer>[] adj, int v, int y, boolean[] visited, int count) {
        if (v == y) {
            return true;
        }
        visited[v] = true;
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                if (explore(adj, i, y, visited, count)) return true;
            }
        }
        return false;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

