import java.util.Scanner;

public class Main {
    public int[] limitOne;
    public int[] limitTwo;
    public int[] limitThree;
    public int[] arrayL;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Входные данные:\n Количество переменных = 5\n Количество ограничений = 3\n Задача на max, записанна в виде общего ввида.\n Применяем V - задачу.");
        Main main = new Main();
        /*
        main.arrayL = main.initArray(5);
        main.limitOne = main.initArray(6);
        main.limitTwo = main.initArray(6);
        main.limitThree = main.initArray(6);
         */
        main.arrayL = new int[]{0, 0, 2, 2, -1};
        main.limitOne = new int[]{-2, 1, 1, 0, 0, 2};
        main.limitTwo = new int[]{4, 3, 2, 1, 1, 13};
        main.limitThree = new int[]{3, 2, 0, 0, 1, 16};
        main.outArray(main.arrayL);
        main.outArray(main.limitOne);
        main.outArray(main.limitTwo);
        main.outArray(main.limitThree);
        int[] result = main.checkArrays(main.limitOne, main.limitTwo, main.limitThree);
        System.out.printf("Переменная x%d встречается только в %d уравнение, " +
                "выражаем его, а в %d и %d уравнение добавляем v - переменную.%n", result[0] + 1, result[1], result[2], result[3]);
        System.out.println(result[0]);
        System.out.println(result[1]);

        switch (result[1]){
            case 1:
                main.limitOne[result[0]] = 0;
                main.moveValueAtIndexToFront(main.limitOne, 5);
                main.moveValueAtIndexToFront(main.limitTwo, 5);
                main.moveValueAtIndexToFront(main.limitThree, 5);
                break;
            case 2:
                main.limitTwo[result[0]] = 0;
                main.moveValueAtIndexToFront(main.limitOne, 5);
                main.moveValueAtIndexToFront(main.limitTwo, 5);
                main.moveValueAtIndexToFront(main.limitThree, 5);
                break;
            case 3:
                main.limitThree[result[0]] = 0;
                main.moveValueAtIndexToFront(main.limitOne, 5);
                main.moveValueAtIndexToFront(main.limitTwo, 5);
                main.moveValueAtIndexToFront(main.limitThree, 5);
                break;
        }
        main.outArray(main.limitOne);
        main.outArray(main.limitTwo);
        main.outArray(main.limitThree);
    }
    public int[] moveValueAtIndexToFront(int[] arrayToBeShifted, int index) {
        int valueBeingMoved = arrayToBeShifted[index];

        for (int i = index; i > 0; i--) {
            arrayToBeShifted[i] = arrayToBeShifted[i-1];
        }

        arrayToBeShifted[0] = valueBeingMoved;

        return arrayToBeShifted;
    }
    public int[] checkArrays(int[] arrayOne, int[] arrayTwo, int[] arrayThree) {
        int[] result = new int[0];
        for (int i = 0; i < arrayOne.length; i++) {
            if (arrayOne[i] == 0 && arrayTwo[i] == 0) {
                result = new int[]{i, 3, 1, 2};// Третье уравнение, xi, 1 и 2 выражаем через v
            }
            if (arrayOne[i] == 0 && arrayThree[i] == 0) {
                result = new int[]{i, 2, 1, 3}; // Второе уравнение, xi, 1 и 3 выражаем через v
            }
            if (arrayThree[i] == 0 && arrayTwo[i] == 0) {
                result = new int[]{i, 1, 2, 3}; // Второе уравнение, xi, 2 и 3 выражаем через v
            }
        }
        return result;
    }

    public void outArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }


    public int[] initArray(int countEnv) {
        int[] array = new int[countEnv];
        System.out.println("Введите коэффициенты:");
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }
        return array;
    }
}
