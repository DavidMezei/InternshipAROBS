import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give me the number from where to start: ");
        int a = scanner.nextInt();

        System.out.print("Give me the number for where to stop: ");
        int b = scanner.nextInt();

        boolean[] primeBool = new boolean[b + 1];
        Arrays.fill(primeBool, true);
        primeBool[1] = false;
        primeBool[0] = false;

        for (int i = 2; (i * i) <= b; i++) {
            if (primeBool[i]) {
                for (int j = i * i; j <= b; j = j + i) {
                    primeBool[j] = false;
                }
            }
        }

        Vector<Integer> primes = new Vector<Integer>();
        int sum = 1;
        int n = 0;
        for (int i = 2; i <= b; i++) {
            if (primeBool[i] && i >= a) {
                sum *= i;
                primes.add(i);
                n++;
            }
        }
        System.out.println("Geometric mean: " + Math.pow(sum, 1.0 / n));
        
        for (int i = 0; i < primes.size(); i++) {
            int number = primes.get(i);
            int sumOfDigits = 0;
            while (number > 0) {
                sumOfDigits += number % 10;
                number /= 10;
            }
            if (sumOfDigits % 2 == 0) {
                System.out.print(primes.get(i) + " ");
            }
        }
    }
}
