package week1.labor2_ISP;

import java.util.Random;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) {
        // write your code here
        Random rand = new Random();
        int n = rand.nextInt(9) + 2;
        System.out.println("Give me " + n + " numbers!");

        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int[] array = new int[n];
        while (i < n) {
            array[i] = scanner.nextInt();
            i++;
        }

        double sum = 0;
        for (i = 0; i < n; i++) {
            sum += array[i];
        }

        System.out.println("Arithmetical mean of the array is: " + sum / n);


    }
}
