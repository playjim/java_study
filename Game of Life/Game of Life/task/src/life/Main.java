package life;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Condition life = new Condition(scanner.nextInt(),scanner.nextLong());
<<<<<<< HEAD
        Generation generation = new Generation(scanner.nextInt(), life.planetField.clone());
        generation.getNewGeneration();
=======
        Generation generation = new Generation(scanner.nextInt(), life.planetField);
>>>>>>> 6ae9334216508aa070150915fb0c0124fe8c8bd7
    }
}