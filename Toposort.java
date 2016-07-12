import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Toposort {
    private static Stack<Integer> toposort(ArrayList<Integer>[] adj) {
        boolean[] visited = new boolean[adj.length];
        Stack<Integer> order = new Stack<Integer>();
        for (int i = 0;i<adj.length;i++) {
            if (!visited[i]) {
                explore(adj, i, visited, order);
            }
        }
        return order;
    }


    private static void explore(ArrayList<Integer>[] adj, int v,  boolean[] visited,  Stack<Integer> order) {
        visited[v] = true;
        for (Integer i : adj[v]) {
            if (!visited[i]) {
                explore(adj, i, visited, order);
            }

        }
        order.push(v + 1);
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
        }
        Stack<Integer> order = toposort(adj);
        while(order.size() > 0) {
            System.out.print(order.pop() + " ");
        }
    }
}

