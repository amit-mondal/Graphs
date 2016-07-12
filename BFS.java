import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int[] distance = new int[adj.length];
        for (int i = 0;i<distance.length;i++) {
            distance[i] = -1;
        }
        distance[s] = 0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while(q.size() > 0) {
            Integer u = q.poll();
            //System.out.println("u: " + u);
            for (Integer v : adj[u]) {
                //if (v == t) return distance[u] + 1;
                if (distance[v] == -1) {
                    //System.out.println("v: " + v);
                    q.add(v);
                    distance[v] = distance[u] + 1;
                }
            }
        }
        return distance[t];
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
        System.out.println(distance(adj, x, y));
    }
}

class Queue<T>{
    final int DATAL = 50;
    Object[] data = new Object[DATAL];
    int size = 0;
    public boolean isEmpty(){
        return (size == 0);
    }
    public boolean add(T o){
        if(size<data.length){
            size++;
            data[size-1] = (Object)o;
        }
        else throw new QueueException("Queue out of bounds");
        return true;
    }
    public T remove(){
        if(size<=data.length){
            Object temp = data[0];
            for(int i = 1;i<data.length;i++){
                data[i-1] = data[i];
            }
            size--;
            return (T)temp;
        }
        else throw new QueueException("Queue out of bounds");
    }
    public T peek(){
        return (T)(data[0]);
    }
    public int size(){
        return size;
    }
    //Method that copies data from queue to array
    public Object[] copy(){
        Object[] result = new Object[size];
        for(int i = 0;i<size;i++) result[i] = data[i];
        return result;
    }
}
class QueueException extends RuntimeException{
    public QueueException(String message){
        super(message);
    }
}
