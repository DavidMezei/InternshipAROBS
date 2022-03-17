package week1.labor2_ISP;

import java.util.Scanner;
import java.util.Vector;

public class Exercise6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give me the n: ");
        int n = scanner.nextInt();

        Vector<Integer> vector = new Vector<Integer>();
        vector.add(1);
        vector.add(2);

        //non-recursively
        int i = 2;
        while (i < n) {
            vector.add(vector.get(i - 2) * vector.get(i - 1));
            i++;
        }
        System.out.print("Non-recursively: ");
        printVector(vector);

        vector.clear();
        vector.add(1);
        vector.add(2);

        //recursively
        generateRecursively(vector, 2, n);
        System.out.print("Recursively: ");
        printVector(vector);
    }

    public static void generateRecursively(Vector<Integer> vector, int i, int n) {
        if (i < n) {
            vector.add(vector.get(i - 2) * vector.get(i - 1));
            generateRecursively(vector, i + 1, n);
        }
    }

    public static void printVector(Vector<Integer> vector) {
        for (var number : vector) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
