package life;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Condition life = new Condition(scanner.nextInt(),scanner.nextLong());
        Generation generation = new Generation(scanner.nextInt(), life.planetField);
    }
}