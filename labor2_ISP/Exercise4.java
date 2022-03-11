package labor1;

import java.util.Random;
import java.util.Vector;

public class Exercise4 {
    public static void main(String[] args) {
        Random random = new Random();
        Vector<Integer> vector = new Vector<Integer>();
        int n = 5;
        System.out.print("Vector: ");
        for (int i = 0; i < n; i++) {
            vector.add(random.nextInt(100));
            System.out.print(vector.get(i) + " ");
        }
        System.out.println();

        boolean prime = false, odd = false, even = false;
        int primePosition = -1, oddPosition = -1, evenPosition = -1;

        int i = 0;
        while (i < n && (!prime || !odd || !even)) {
            if (!prime) {
                if (isPrime(vector.get(i))) {
                    prime = true;
                    primePosition = i;
                }
            }
            if (!odd) {
                if (vector.get(i) % 2 == 1) {
                    odd = true;
                    oddPosition = i;
                }
            } else {
                evenPosition = i;
                even = true;
            }
            i++;
        }

        if (prime) {
            System.out.println("Prime: " + vector.get(primePosition) + " posistion: " + primePosition);
        } else {
            System.out.println("There is no prime number in Vector");
        }

        if (odd) {
            System.out.println("Odd: " + vector.get(oddPosition) + " position: " + oddPosition);
        } else {
            System.out.println("There is no odd number in Vector");
        }

        if (even) {
            System.out.println("Even: " + vector.get(evenPosition) + " position: " + evenPosition);
        } else {
            System.out.println("There is no even number in Vector");
        }

    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
