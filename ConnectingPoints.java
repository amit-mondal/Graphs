import java.util.*;

public class ConnectingPoints {

    private static double minimumDistance(int[] x, int[] y) {
        ArrayList<Set<Integer>> sets = new ArrayList<Set<Integer>>();
        //generate sets
        for (int i = 0;i<x.length;i++) {
            Set<Integer> tempSet = new HashSet<Integer>();
            tempSet.add(i);
            sets.add(tempSet);
        }
        //sort edges
        LinkedList<Edge> edges = new LinkedList<Edge>();
        double result = 0.;
        for (int i = x.length-1;i>=0;i--) {
            for (int j = 0;j<i;j++) {
                edges.add(new Edge(i, j, x, y));
            }
        }
        Collections.sort(edges);
        //loop through edges
        while (edges.size() > 0) {
            //get smallest edge
            Edge e = edges.removeFirst();
            int u = e.node1;
            int v = e.node2;
            int index1 = find(u, sets);
            int index2 = find(v, sets);
            //verify nodes are in different sets
            if (index1 != index2) {
                //Add to result
                result += e.distance;
                //Merge sets
                Set set1 = sets.get(index1);
                Set set2 = sets.get(index2);
                set1.addAll(set2);
                sets.remove(set2);
            }
        }

        return result;
    }

    public static int find(int node, ArrayList<Set<Integer>> sets) {
        for (int i = 0;i<sets.size();i++) {
            if (sets.get(i).contains(node)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

class Edge implements Comparable<Edge> {
    public static final double precision = 10e5;
    public int node1;
    public int node2;
    public double distance;
    public Edge(int firstNode, int secondNode, int[] x, int[] y) {
        node1 = firstNode;
        node2 = secondNode;
        int x1 = x[firstNode];
        int x2 = x[secondNode];
        int y1 = y[firstNode];
        int y2 = y[secondNode];
        distance = Math.hypot((x2 - x1), (y2 - y1));
    }
    public int compareTo(Edge e) {
        return ((int)(this.distance * precision) - (int)(e.distance * precision));
    }
}
