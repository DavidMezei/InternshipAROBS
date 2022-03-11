package labor2_ISP;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Exercise5 {
    public static void main(String[] args) {
        Random random = new Random();
        Vector<Integer> vector = new Vector<Integer>();
        int n = 20;
        System.out.print("Vector: ");
        for (int i = 0; i < n; i++) {
            vector.add(random.nextInt(2001)-1000);
            System.out.print(vector.get(i) + " ");
        }
        System.out.println();

        Collections.sort(vector);

        System.out.print("Vector sorted: ");
        for (var number:vector){
            System.out.print(number+" ");
        }
    }
}
