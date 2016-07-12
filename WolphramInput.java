import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by amit on 6/19/16.
 */
public class WolphramInput {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            System.out.print(x + "->" + y + ", ");
        }
    }
}
