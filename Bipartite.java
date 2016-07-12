import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        if (isBipartite(adj, 0)) return 1;
        return 0;
    }

    private static boolean isBipartite(ArrayList<Integer>[] adj, int s) {
        int[] distance = new int[adj.length];
        char[] color = new char[adj.length];
        for (int i = 0;i<color.length;i++) {
            color[i] = ' ';
        }
        for (int i = 0;i<distance.length;i++) {
            distance[i] = -1;
        }
        distance[s] = 0;
        color[s] = 'b';
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while(q.size() > 0) {
            /*
            for(char c : color) {
                System.out.print(c + ", ");
            }
            */
            Integer u = q.poll();
            //System.out.println("u: " + u);
            for (Integer v : adj[u]) {
                //System.out.println("u: " + u + "(" + color[u] + ")"  + ", v: " + v + "(" + color[v] + ")" );
                if (distance[v] == -1) {
                    //System.out.println("v: " + v);
                    q.add(v);
                    distance[v] = distance[u] + 1;
                    if (color[v]==' ') {
                        if (color[u] == 'b') {
                            color[v] = 'w';
                        } else {
                            color[v] = 'b';
                        }
                    }
                }
                if (color[u] == color[v]) return false;
            }
        }
        return true;
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
        System.out.println(bipartite(adj));
    }
}

