package console_int;

import java.util.Scanner;

public class borisov_lab2 {
    static int[] array;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = "";
        System.out.println("Введите \"help\" для помощи");
        while(true){
            System.out.print(">");
            command = sc.nextLine();
            if(command.equals("q"))
                break;
            switch(command)
            {
                case "1":
                    System.out.println("Выполнение команды 1");
                    enter();
                    break;
                case "2":
                    System.out.println("Выполнение команды 2\n Нахождение прозведения минимального и максимального элемента массива");
                    System.out.println(multiplication(array));
                    break;
                case "3":
                    System.out.println("Выполнение команды 3\n Нахождение частного максимального и минимального элемента массива");
                    System.out.println(division(array));
                    break;
                case "help":
                    help();
                default:
                    System.out.println("неверная команда, для помощи введите \"help\"");
            }// tnd of switch(command)
        }// tnd ofwhile(true)
    }// end of main()
    static void help()
    {
        System.out.println("q - Завершение программы");
        System.out.println("1 - Ввод массива");
        System.out.println("2 - Нахождение прозведения минимального и максимального элемента массива");
        System.out.println("3 - Нахождение частного максимального и минимального элемента массива");
        System.out.println("help - показать помощь");
    }
    public static void enter(){
        System.out.println("Введите кол-во элементов создаваемого массива и заполните его");
        Scanner scan = new Scanner(System.in);
        int n;
        n = scan.nextInt();
        array = new int[n];
        System.out.println("Теперь заполните массив размерностью "+n);
        for(int i = 0;i<n;i++){
            array[i] = scan.nextInt();
        }
        System.out.print("Вы создали массив целых чисел array{");
        for(int i: array){ //Вывод массива
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println("}");
    }
    static int multiplication(int[] mass){ // Нахождение прозведения минимального и максимального элемента массива
        int min,max;
        min = mass[0];
        max = mass[0];
        for(int i =0;i<mass.length;i++){ // нахождение минимального и максимального элемента массива
            if (mass[i] > max) max = mass[i];
            if (mass[i] < min) min = mass[i];
            }
        return min * max;
    }
    static int division(int[] mass){ // Нахождение прозведения минимального и максимального элемента массива
        int min,max;
        min = mass[0];
        max = mass[0];
        for(int i =0;i<mass.length;i++){ // нахождение минимального и максимального элемента массива
            if (mass[i] > max) max = mass[i];
            if (mass[i] < min) min = mass[i];
        }
        return max / min;
    }
}// end ofclass Console_int
