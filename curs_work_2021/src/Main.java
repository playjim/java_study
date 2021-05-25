import java.util.*;

public class Main {
    public int[] array1;
    public int[] array2;
    public int[] array3;
    public int[] arrayL;
    public static Scanner sc = new Scanner(System.in);
    public Hashtable<String, Double> Map1 = new Hashtable<String, Double>();
    public Hashtable<String, Double> Map2 = new Hashtable<String, Double>();
    public Hashtable<String, Double> Map3 = new Hashtable<String, Double>();
    public Hashtable<String, Double> MapV = new Hashtable<String, Double>();
    public Hashtable<String, Double> MapVC = new Hashtable<String, Double>();
    public ArrayList<String> list = new ArrayList<String>();
    String map1Vert;
    String map2Vert;
    String map3Vert;
    String pickColumn; // Разрежаюший столбец
    int pickRow; // Разрещающая строка


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
        main.Map1.remove("x" + Integer.toString(result[0] + 1));
        main.Map2.remove("x" + Integer.toString(result[0] + 1));
        main.Map3.remove("x" + Integer.toString(result[0] + 1));
        switch (result[1]) { // Какое уравнение;
            case 1:
                main.map1Vert = "x" + Integer.toString(result[0] + 1);
                main.map2Vert = "v1";
                main.map3Vert = "v2";
                break;
            case 2:
                main.map1Vert = "v1";
                main.map2Vert = "x" + Integer.toString(result[0] + 1);
                main.map3Vert = "v2";
                break;
            case 3:
                main.map1Vert = "v1";
                main.map2Vert = "v2";
                main.map3Vert = "x" + Integer.toString(result[0] + 1);
                break;
        }
        System.out.println(main.Map1.toString());
        System.out.println(main.Map2.toString());
        System.out.println(main.Map3.toString());
        main.MapVC.put("v1", (double) 1);
        main.MapVC.put("v2", (double) 1);
        main.MapVC.put("x1", (double) 0);
        main.MapVC.put("x2", (double) 0);
        main.MapVC.put("x3", (double) 0);
        main.MapVC.put("x4", (double) 0);
        main.MapVC.put("x5", (double) 0);
        main.MapVC.put("b", (double) 0);
        main.Map1.forEach((k, v) -> {
            main.list.add(k);
        });
        main.list.forEach((i) -> { // Находим гамма коэфиценты V
            double test = main.MapVC.get(main.map1Vert) * main.Map1.get(i) + main.MapVC.get(main.map2Vert) * main.Map2.get(i) + main.MapVC.get(main.map3Vert) * main.Map3.get(i) - main.MapVC.get(i);
            main.MapV.put(i, test);
        });
        System.out.println(main.list.toString());
        System.out.println(main.MapV.toString());
        main.pickColumn = main.findPickColumn(main.MapV, main.list); // выбираем положительную не базисную гамму
        if (main.pickColumn.equals("none")) {
            System.out.println("Нет положительных гамм, нет решения");
            System.exit(95);
        }
        main.pickRow = main.findPickRow(main.pickColumn, main.Map1, main.Map2, main.Map3); // находим разрещающую строку
        System.out.println(main.pickColumn);
        System.out.println(main.pickRow);
        double lambda = 0.0;
        switch (main.pickRow) { // Какое уравнение;
            case 1:
                lambda = 1 / main.Map1.get(main.pickColumn);
                break;
            case 2:
                lambda = 1 / main.Map2.get(main.pickColumn);
                break;
            case 3:
                lambda = 1 / main.Map3.get(main.pickColumn);
                break;
        }
        System.out.println(lambda);

        switch (main.pickRow) { // Заполняем разрешающую строку и столбец;
            case 1:
                double finalLambda1 = lambda;
                main.Map2.put(main.pickColumn + "tmp", main.Map2.get(main.pickColumn) * (-lambda));
                main.Map3.put(main.pickColumn + "tmp", main.Map3.get(main.pickColumn) * (-lambda));
                main.MapV.put(main.pickColumn + "tmp", main.Map3.get(main.pickColumn) * (-lambda));
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) main.Map1.put(i + "tmp", main.Map1.get(i) * finalLambda1);
                    else main.Map1.put(i + "tmp", finalLambda1);
                });
                break;
            case 2:
                double finalLambda2 = lambda;
                main.Map1.put(main.pickColumn + "tmp", main.Map1.get(main.pickColumn) * (-lambda));
                main.Map3.put(main.pickColumn + "tmp", main.Map3.get(main.pickColumn) * (-lambda));
                main.MapV.put(main.pickColumn + "tmp", main.Map3.get(main.pickColumn) * (-lambda));
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) main.Map2.put(i + "tmp", main.Map2.get(i) * finalLambda2);
                    else main.Map2.put(i + "tmp", finalLambda2);
                });
                break;
            case 3:
                double finalLambda3 = lambda;
                main.Map1.put(main.pickColumn + "tmp", main.Map1.get(main.pickColumn) * (-lambda));
                main.Map2.put(main.pickColumn + "tmp", main.Map2.get(main.pickColumn) * (-lambda));
                main.MapV.put(main.pickColumn + "tmp", main.Map3.get(main.pickColumn) * (-lambda));
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) main.Map3.put(i + "tmp", main.Map3.get(i) * finalLambda3);
                    else main.Map3.put(i + "tmp", finalLambda3);
                });
                break;
        }
        switch (main.pickRow) { // Заполняем остальные элементы;
            case 1:
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) {
                        main.Map2.put(i + "tmp", main.Map2.get(main.pickColumn + "tmp") * main.Map1.get(i));
                        main.Map3.put(i + "tmp", main.Map3.get(main.pickColumn + "tmp") * main.Map1.get(i));
                        main.MapV.put(i + "tmp", main.MapV.get(main.pickColumn + "tmp") * main.Map1.get(i));
                    }
                });
                break;
            case 2:
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) {
                        main.Map1.put(i + "tmp", main.Map1.get(main.pickColumn + "tmp") * main.Map2.get(i));
                        main.Map3.put(i + "tmp", main.Map3.get(main.pickColumn + "tmp") * main.Map2.get(i));
                        main.MapV.put(i + "tmp", main.MapV.get(main.pickColumn + "tmp") * main.Map2.get(i));
                    }
                });
                break;
            case 3:
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) {
                        main.Map2.put(i + "tmp", main.Map2.get(main.pickColumn + "tmp") * main.Map3.get(i));
                        main.Map1.put(i + "tmp", main.Map1.get(main.pickColumn + "tmp") * main.Map3.get(i));
                        main.MapV.put(i + "tmp", main.MapV.get(main.pickColumn + "tmp") * main.Map3.get(i));
                    }
                });
                break;
        }
        switch (main.pickRow) { // Замена;
            case 1:
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) {
                        //main.Map1.replace(i,main.Map1.get(i+"tmp")+main.Map1.get(i));
                        main.Map1.remove(i + "tmp");
                        main.Map2.replace(i, main.Map2.get(i + "tmp") + main.Map2.get(i));
                        main.Map2.remove(i + "tmp");
                        main.Map3.replace(i, main.Map3.get(i + "tmp") + main.Map3.get(i));
                        main.Map3.remove(i + "tmp");
                        main.MapV.replace(i, main.MapV.get(i + "tmp") + main.MapV.get(i));
                        main.MapV.remove(i + "tmp");
                    } else {
                        main.Map1.replace(i, main.Map1.get(i + "tmp"));
                        main.Map1.remove(i + "tmp");
                        main.Map2.replace(i, main.Map2.get(i + "tmp"));
                        main.Map2.remove(i + "tmp");
                        main.Map3.replace(i, main.Map3.get(i + "tmp"));
                        main.Map3.remove(i + "tmp");
                        main.MapV.replace(i, main.Map3.get(i + "tmp"));
                        main.MapV.remove(i + "tmp");
                    }
                });
                main.Map1.put(main.map1Vert, main.Map1.get(main.pickColumn));
                main.Map1.remove(main.pickColumn);
                main.Map2.put(main.map1Vert, main.Map2.get(main.pickColumn));
                main.Map2.remove(main.pickColumn);
                main.Map3.put(main.map1Vert, main.Map3.get(main.pickColumn));
                main.Map3.remove(main.pickColumn);
                main.map3Vert = main.pickColumn;
                main.pickColumn = "none";
                main.pickRow = 0;
                break;
            case 2:
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) {
                        main.Map1.replace(i, main.Map1.get(i + "tmp") + main.Map1.get(i));
                        main.Map1.remove(i + "tmp");
                        //main.Map2.replace(i,main.Map2.get(i+"tmp")+main.Map2.get(i));
                        main.Map2.remove(i + "tmp");
                        main.Map3.replace(i, main.Map3.get(i + "tmp") + main.Map3.get(i));
                        main.Map3.remove(i + "tmp");
                        main.MapV.replace(i, main.MapV.get(i + "tmp") + main.MapV.get(i));
                        main.MapV.remove(i + "tmp");
                    } else {
                        main.Map1.replace(i, main.Map1.get(i + "tmp"));
                        main.Map1.remove(i + "tmp");
                        main.Map2.replace(i, main.Map2.get(i + "tmp"));
                        main.Map2.remove(i + "tmp");
                        main.Map3.replace(i, main.Map3.get(i + "tmp"));
                        main.Map3.remove(i + "tmp");
                        main.MapV.replace(i, main.MapV.get(i + "tmp"));
                        main.MapV.remove(i + "tmp");
                    }

                });
                main.Map1.put(main.map2Vert, main.Map1.get(main.pickColumn));
                main.Map1.remove(main.pickColumn);
                main.Map2.put(main.map2Vert, main.Map2.get(main.pickColumn));
                main.Map2.remove(main.pickColumn);
                main.Map3.put(main.map2Vert, main.Map3.get(main.pickColumn));
                main.Map3.remove(main.pickColumn);
                main.map3Vert = main.pickColumn;
                main.pickColumn = "none";
                main.pickRow = 0;
                break;
            case 3:
                main.list.forEach((i) -> {
                    if (!i.equals(main.pickColumn)) {
                        main.Map1.replace(i, main.Map1.get(i + "tmp") + main.Map1.get(i));
                        main.Map1.remove(i + "tmp");
                        main.Map2.replace(i, main.Map2.get(i + "tmp") + main.Map2.get(i));
                        main.Map2.remove(i + "tmp");
                        //main.Map3.replace(i,main.Map3.get(i+"tmp")+main.Map3.get(i));
                        main.Map3.remove(i + "tmp");
                        main.MapV.replace(i, main.MapV.get(i + "tmp") + main.MapV.get(i));
                        main.MapV.remove(i + "tmp");
                    } else {
                        main.Map1.replace(i, main.Map1.get(i + "tmp"));
                        main.Map1.remove(i + "tmp");
                        main.Map2.replace(i, main.Map2.get(i + "tmp"));
                        main.Map2.remove(i + "tmp");
                        main.Map3.replace(i, main.Map3.get(i + "tmp"));
                        main.Map3.remove(i + "tmp");
                        main.MapV.replace(i, main.MapV.get(i + "tmp"));
                        main.MapV.remove(i + "tmp");
                    }
                });
                main.Map1.put(main.map3Vert, main.Map1.get(main.pickColumn));
                main.Map1.remove(main.pickColumn);
                main.Map2.put(main.map3Vert, main.Map2.get(main.pickColumn));
                main.Map2.remove(main.pickColumn);
                main.Map3.put(main.map3Vert, main.Map3.get(main.pickColumn));
                main.Map3.remove(main.pickColumn);
                main.map3Vert = main.pickColumn;
                main.pickColumn = "none";
                main.pickRow = 0;
                break;
        }
        // Делаем пересчет таблицы

        System.out.println(main.Map1.toString());
        System.out.println(main.Map2.toString());
        System.out.println(main.Map3.toString());
        System.out.println(main.MapV.toString());
        System.out.println(main.map1Vert);
        System.out.println(main.map2Vert);
        System.out.println(main.map3Vert);
    }

    public int findPickRow(String pickColumn, Hashtable<String, Double> Map1, Hashtable<String, Double> Map2, Hashtable<String, Double> Map3) {
        double M1 = Map1.get(pickColumn) > 0 ? Map1.get("b") / Map1.get(pickColumn) : 0;
        double M2 = Map2.get(pickColumn) > 0 ? Map2.get("b") / Map2.get(pickColumn) : 10000;
        double M3 = Map3.get(pickColumn) > 0 ? Map3.get("b") / Map3.get(pickColumn) : 0;
        if (M1 < M2 && M1 < M3) {
            return 1; // 1 строка
        } else if (M2 < M1 && M2 < M3) {
            return 2; // 2 строка
        } else {
            return 3; // 3 строка
        }
    }

    public void calculateV() {

    }

    public String findPickColumn(Hashtable<String, Double> MapV, ArrayList<String> list) { // Выбираем разрещающий столбец
        for (String s : list) {
            if (!s.equals("b")) {
                if (MapV.get(s) > 0) {
                    return s;
                }
            }
        }
        return "none";
    }

    public void initMaps(Hashtable<String, Double> map, int[] array) { // Выбираем разрещающую строку
        map.put("x1", (double) array[0]);
        map.put("x2", (double) array[1]);
        map.put("x3", (double) array[2]);
        map.put("x4", (double) array[3]);
        map.put("x5", (double) array[4]);
        map.put("b", (double) array[5]);
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
