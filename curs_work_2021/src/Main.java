import java.util.*;

public class Main {
    public int[] array1;
    public int[] array2;
    public int[] array3;
    public int[] arrayL;
    public static Scanner sc = new Scanner(System.in);
    public Hashtable<String,Integer> Map1 = new Hashtable<String,Integer>();
    public Hashtable<String,Integer> Map2 = new Hashtable<String,Integer>();
    public Hashtable<String,Integer> Map3 = new Hashtable<String,Integer>();
    public Hashtable<String,Integer> state = new Hashtable<String,Integer>();


    public static void main(String[] args) {
        System.out.println("Входные данные:\n Количество переменных = 5\n Количество ограничений = 3\n Задача на max, записанна в виде общего ввида.\n Применяем V - задачу.");
        Main main = new Main();
        /*
        main.arrayL = main.initArray(5);
        main.limitOne = main.initArray(6);
        main.limitTwo = main.initArray(6);
        main.limitThree = main.initArray(6);
         */
        /*
        main.arrayL = new int[]{0, 0, 2, 2, -1};
        main.limitOne = new int[]{-2, 1, 1, 0, 0, 2};
        main.limitTwo = new int[]{4, 3, 2, 1, 1, 13};
        main.limitThree = new int[]{3, 2, 0, 0, 1, 16};
        */
        main.arrayL = new int[]{6, 0, -1, 1, 2};
        main.array1 = new int[]{4, 1, 1, 2, 1, 8};
        main.array2 = new int[]{2, -1, 0, 1, 0, 2};
        main.array3 = new int[]{1, 1, 0, 0, 1, 2};

        main.outArray(main.arrayL);
        main.outArray(main.array1);
        main.outArray(main.array2);
        main.outArray(main.array3);
        int[] result = main.checkArrays(main.array1, main.array2, main.array3);
        System.out.printf("Переменная x%d встречается только в %d уравнение, " +
                "выражаем его, а в %d и %d уравнение добавляем v - переменную.%n", result[0] + 1, result[1], result[2], result[3]);// xi + 1,уравнение , 1 и 2 выражаем через v
        main.initMaps(main.Map1, main.array1);
        main.initMaps(main.Map2, main.array2);
        main.initMaps(main.Map3, main.array3);
        main.Map1.remove("x"+Integer.toString(result[0]+1));
        main.Map2.remove("x"+Integer.toString(result[0]+1));
        main.Map3.remove("x"+Integer.toString(result[0]+1));

        switch (result[1]) { // Какое уравнение
            case 1:
                main.state.put("v1",2);
                main.state.put("v2",3);
                main.state.put("v1",2);
                main.state.put("v1",2);

                break;
            case 2:
                main.Map2.put("flag",result[1]);
                break;
            case 3:
                main.Map3.put("flag",result[1]);
                break;
        }

        main.outArray(main.array1);
        main.outArray(main.array2);
        main.outArray(main.array3);
    }

    public void initMaps(Hashtable<String,Integer> map, int[]array){
        map.put("x1",array[0]);
        map.put("x2",array[1]);
        map.put("x3",array[2]);
        map.put("x4",array[3]);
        map.put("x5",array[4]);
        map.put("b",array[5]);
    }

    public int[] moveValueAtIndexToFront(int[] arrayToBeShifted, int index) {
        int valueBeingMoved = arrayToBeShifted[index];

        for (int i = index; i > 0; i--) {
            arrayToBeShifted[i] = arrayToBeShifted[i - 1];
        }

        arrayToBeShifted[0] = valueBeingMoved;

        return arrayToBeShifted;
    }

    public int[] checkArrays(int[] arrayOne, int[] arrayTwo, int[] arrayThree) {
        int[] result = new int[0];
        for (int i = 0; i < arrayOne.length; i++) {
            if (arrayOne[i] == 0 && arrayTwo[i] == 0) {
                result = new int[]{i, 3, 1, 2};// xi,уравнение , 1 и 2 выражаем через v
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
