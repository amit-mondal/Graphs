import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        if( dfs(adj)) return 1;
        return 0;
    }


    private static boolean explore(ArrayList<Integer>[] adj, int v,  boolean[] visited, boolean[] stack, String flag) {
        visited[v] = true;
        stack[v] = true;
        //System.out.println("Exploring: " + (v + 1) + " " + flag);
        for (Integer i : adj[v]) {
            if (!visited[i] && explore(adj, i, visited, stack, "recur")){
                //System.out.println("Visit v: " + (v+1) + " i: " + (i+1));
                return true;
            } else if (stack[i]) {
                //System.out.println("Stack v: " + (v+1) + " i: " + (i+1));
                return true;
            }

        }
        stack[v] = false;
        return false;
    }


    private static boolean dfs(ArrayList<Integer>[] adj) {
        boolean[] stack = new boolean[adj.length];
        boolean[] visited = new boolean[adj.length];
        for (int i = 0;i<adj.length;i++) {
            if (explore(adj, i, visited, stack, "non-recur")) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

