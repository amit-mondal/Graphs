import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] r_adj) {
        return sccHelper(adj, r_adj);
    }

    private static void exploredfs(ArrayList<Integer>[] adj, int v, boolean[] visited, Stack<Integer> order) {
        visited[v] = true;
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                exploredfs(adj, i, visited, order);
            }
        }
        order.push(v);
    }

    private static Stack<Integer> dfs(ArrayList<Integer>[] adj) {
        Stack<Integer> order = new Stack<Integer>();
        boolean[] visited = new boolean[adj.length];
        for (int i = 0;i<adj.length;i++) {
            if (!visited[i]) {
                exploredfs(adj, i, visited, order);
            }
        }
        return order;
    }

    private static void explore(ArrayList<Integer>[] adj, int v, boolean[] visited, boolean[] removed) {
        visited[v] = true;
        //System.out.print(v + " ");
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                explore(adj, i, visited, removed);
            }
        }
    }

    private static int sccHelper(ArrayList<Integer>[] adj, ArrayList<Integer>[] r_adj) {
        int sccCounter = 0;
        Stack<Integer> s = dfs(adj);
        boolean[] visited = new boolean[adj.length];
        boolean[] removed = new boolean[adj.length];
        while (s.size() > 0) {
            Integer v = s.pop();
            if (!visited[v]) {
                explore(r_adj, v, visited, removed);
                //System.out.println();
                sccCounter++;
            }
        }
        return sccCounter;
}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] r_adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            r_adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            r_adj[y-1].add(x-1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, r_adj));
    }
}

