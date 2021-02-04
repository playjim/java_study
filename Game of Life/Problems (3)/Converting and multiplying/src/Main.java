import java.util.Objects;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        int i = 0;
        int j = 0;
        String[] mass = new String[90];
        while (true) {
            input = sc.next();
            mass[i] = input;
            if (!Objects.equals(input, "0")) i++;
            else {
                break;
            }
        }
        while (!mass[j].equals("0")) {
            try {
                System.out.println(Integer.parseInt(mass[j]) * 10);
            } catch (Exception e) {
                System.out.println("Invalid user input: " + mass[j]);
            }
            j++;
        }
    }
}