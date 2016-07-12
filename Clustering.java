import java.util.*;

public class Clustering {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }

    private static double clustering(int[] x, int[] y, int k) {
        ArrayList<Set<Integer>> sets = new ArrayList<Set<Integer>>();
        //generate sets
        for (int i = 0;i<x.length;i++) {
            Set<Integer> tempSet = new HashSet<Integer>();
            tempSet.add(i);
            sets.add(tempSet);
        }
        //sort edges
        LinkedList<Edge> edges = new LinkedList<Edge>();
        for (int i = x.length-1;i>=0;i--) {
            for (int j = 0;j<i;j++) {
                edges.add(new Edge(i, j, x, y));
            }
        }
        Collections.sort(edges);
        LinkedList<Edge> edgesCopy = (LinkedList<Edge>)edges.clone();
        //loop through edges
        while (sets.size() > k) {
            //get smallest edge
            Edge e = edges.removeFirst();
            int u = e.node1;
            int v = e.node2;
            int index1 = find(u, sets);
            int index2 = find(v, sets);
            //verify nodes are in different sets
            if (index1 != index2) {
                //Merge sets
                Set set1 = sets.get(index1);
                Set set2 = sets.get(index2);
                set1.addAll(set2);
                sets.remove(set2);
            }

        }
        /*
            At this point, the sets list contains the optimized clusters
            Must sort the edges to find the smallest distance d
         */
        LinkedList<Edge> connectingEdges = new LinkedList<Edge>();
        for (Edge e : edgesCopy) {
            //Check if the edge connects two clusters
            if (find(e.node1, sets) != find(e.node2, sets)) {
                connectingEdges.add(e);
            }
        }
        //return the smallest connecting edge
        return  (Collections.min(connectingEdges).distance);

    }
    public static int find(int node, ArrayList<Set<Integer>> sets) {
        for (int i = 0;i<sets.size();i++) {
            if (sets.get(i).contains(node)) {
                return i;
            }
        }
        return -1;
    }
}

