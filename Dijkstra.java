import java.util.*;

public class Dijkstra {

    private static final int infinity = Integer.MAX_VALUE;

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        return dijkstra(adj, cost, s, t);
    }

    private static Integer extractMin (HashMap<Integer, Integer> map) {
        Map.Entry<Integer, Integer> firstEntry = map.entrySet().iterator().next();
        Integer minValue = firstEntry.getValue();
        Integer minKey = firstEntry.getKey();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() < minValue) {
                minValue = entry.getValue();
                minKey = entry.getKey();
            }
        }
        map.remove(minKey);
        return minKey;
    }

    private static int dijkstra(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] distance = new int[adj.length];
        int[] prev = new int[adj.length];
        for (int i = 0;i<distance.length;i++) {
            distance[i] = infinity;
            prev[i] = -1;
        }
        distance[s] = 0;
        //key is the node (because nodes are unique), value is non-unique distance
        HashMap<Integer, Integer> g = new HashMap<Integer, Integer>();
        for (int i = 0;i<distance.length;i++) {
            g.put(i, distance[i]);
        }
        while (g.size() > 0) {
            Integer u = extractMin(g);
            for (int i = 0;i<adj[u].size();i++) {
                Integer v = adj[u].get(i);
                Integer weightFromU = cost[u].get(i);
                if (distance[v] > distance[u] + weightFromU && distance[u] != infinity) {
                    distance[v] = distance[u] + weightFromU;
                    prev[v] = u;
                    g.put(v, Math.abs(distance[v]));
                }
            }
        }
        Integer finalVal = distance[t];
        if (finalVal == infinity) return -1;
        return finalVal;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

