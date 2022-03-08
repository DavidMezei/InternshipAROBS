import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("Give me the words!");
        Scanner scanner = new Scanner(System.in);
        String wordsInLine = scanner.nextLine();

        System.out.println("Give me the first letter that you are searching for!");
        char letter = scanner.next().charAt(0);

        String[] words = wordsInLine.split(",");

        System.out.print("The result: ");
        for (var word : words) {
            if (word.charAt(0) == letter) {
                System.out.print(word + " ");
            }
        }

    }
}
