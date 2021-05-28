import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
    public Hashtable<String, Double> MapL = new Hashtable<String, Double>();
    public Hashtable<String, Double> MapLC = new Hashtable<String, Double>();
    public ArrayList<String> list = new ArrayList<String>();
    //double lambda = 0.0;
    String map1Vert;
    String map2Vert;
    String map3Vert;
    //String pickColumn; // Разрежаюший столбец
    //int pickRow; // Разрещающая строка


    public static void main(String[] args) {
        System.out.println("Входные данные:\n Количество переменных = 5\n Количество ограничений = 3\n Задача на max, записанна в виде общего ввида.\n Применяем V - задачу.");
        Main main = new Main();
        /*
        main.arrayL = main.initArray(5);
        main.array1 = main.initArray(6);
        main.array2 = main.initArray(6);
        main.array3 = main.initArray(6);
        */

        main.arrayL = new int[]{0, 0, 2, 2, -1};
        main.array1 = new int[]{-2, 1, 1, 0, 0, 2};
        main.array2 = new int[]{4, 3, 2, 1, 1, 13};
        main.array3 = new int[]{3, 2, 0, 0, 1, 16};

 /*
        main.arrayL = new int[]{6, 0, -1, 1, 2};
        main.array1 = new int[]{4, 1, 1, 2, 1, 8};
        main.array2 = new int[]{2, -1, 0, 1, 0, 2};
        main.array3 = new int[]{1, 1, 0, 0, 1, 2};
*/
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
        main.initMaps(main.MapLC, main.arrayL);
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

        main.MapVC.put("v1", (double) 1);
        main.MapVC.put("v2", (double) 1);
        main.MapVC.put("x1", (double) 0);
        main.MapVC.put("x2", (double) 0);
        main.MapVC.put("x3", (double) 0);
        main.MapVC.put("x4", (double) 0);
        main.MapVC.put("x5", (double) 0);
        main.MapVC.put("b", (double) 0);

        main.findGamma(main.MapVC, main.MapV);
        System.out.println(main.Map1.toString());
        System.out.println(main.Map2.toString());
        System.out.println(main.Map3.toString());
        System.out.println(main.MapV.toString());
        System.out.println();
        String pickColumn;

        while (true) {
            pickColumn = main.findGammaPositive(main.Map1, main.Map2, main.Map3, main.MapV, main.map1Vert, main.map2Vert, main.map3Vert);
            if (!pickColumn.equals("nullqwe")) {
                main.calculateV(main.Map1, main.Map2, main.Map3, main.MapV, pickColumn);
            } else {
                System.out.println("Ошибка!");
                if (main.map1Vert.equals("v1") || main.map2Vert.equals("v1") || main.map3Vert.equals("v1") || main.map1Vert.equals("v2") || main.map2Vert.equals("v2") || main.map3Vert.equals("v2")) {
                    System.out.println("Гамма положительные закончились, а v переменная базисная. Нет решения");
                    System.out.println(main.map1Vert);
                    System.out.println(main.map2Vert);
                    System.out.println(main.map3Vert);
                    System.exit(0);
                }
                main.conversionToL();
                main.findGamma(main.MapLC,main.MapL);
                System.out.println(main.Map1.toString());
                System.out.println(main.Map2.toString());
                System.out.println(main.Map3.toString());
                System.out.println(main.MapL.toString());
                System.out.println();
                break;
            }
            System.out.println(main.Map1.toString());
            System.out.println(main.Map2.toString());
            System.out.println(main.Map3.toString());
            System.out.println(main.MapV.toString());
            System.out.println();
        }

        while (true) {
            pickColumn = main.findGammaPositive(main.Map1, main.Map2, main.Map3, main.MapL, main.map1Vert, main.map2Vert, main.map3Vert);
            if (!pickColumn.equals("nullqwe")) {
                main.calculateV(main.Map1, main.Map2, main.Map3, main.MapL, pickColumn);
            } else {
                System.out.println("Ошибка2!");
                if (main.map1Vert.equals("v1") || main.map2Vert.equals("v1") || main.map3Vert.equals("v1") || main.map1Vert.equals("v2") || main.map2Vert.equals("v2") || main.map3Vert.equals("v2")) {
                    System.out.println("Гамма положительные закончились, а v переменная базисная. Нет решения");
                    System.out.println(main.map1Vert);
                    System.out.println(main.map2Vert);
                    System.out.println(main.map3Vert);
                    System.exit(0);
                }
                System.out.println(main.Map1.toString());
                System.out.println(main.Map2.toString());
                System.out.println(main.Map3.toString());
                System.out.println(main.MapL.toString());
                System.out.println();
                main.outAnswer();
                break;
            }
            System.out.println(main.Map1.toString());
            System.out.println(main.Map2.toString());
            System.out.println(main.Map3.toString());
            System.out.println(main.MapL.toString());
            System.out.println();
        }

    }

    public String findGammaPositive(Hashtable<String, Double> Map1, Hashtable<String, Double> Map2, Hashtable<String, Double> Map3, Hashtable<String, Double> MapV, String map1Vert, String map2Vert, String map3Vert) {
        ArrayList<String> list = new ArrayList<>();
        MapV.forEach((k, v) -> {
            list.add(k);
        });
        String Return = "nullqwe";
        for (String s : list) {
            if (!s.equals("b") && MapV.get(s) > 0.0 && checkGamma(Map1, Map2, Map3, MapV, s, map1Vert, map2Vert, map3Vert)) {
                Return = s;
                break;
            }
        }
        System.out.println(Return);
        return Return;
    }

    public boolean checkGamma(Hashtable<String, Double> Map1, Hashtable<String, Double> Map2, Hashtable<String, Double> Map3, Hashtable<String, Double> MapV, String pickColumnTest, String map1Vert, String map2Vert, String map3Vert) {
        Hashtable<String, Double> Map1tmp = new Hashtable<String, Double>();
        Hashtable<String, Double> Map2tmp = new Hashtable<String, Double>();
        Hashtable<String, Double> Map3tmp = new Hashtable<String, Double>();
        Hashtable<String, Double> MapVtmp = new Hashtable<String, Double>();
        String map1VertTmp = map1Vert.toString();
        String map2VertTmp = map2Vert.toString();
        String map3VertTmp = map3Vert.toString();

        Map1.forEach(Map1tmp::put);
        Map2.forEach(Map2tmp::put);
        Map3.forEach(Map3tmp::put);
        MapV.forEach(MapVtmp::put);
        calculateV(Map1tmp, Map2tmp, Map3tmp, MapVtmp, pickColumnTest, map1VertTmp, map2VertTmp, map3VertTmp);
        return Map1tmp.get("b") > 0.0 && Map2tmp.get("b") > 0.0 && Map3tmp.get("b") > 0.0 && !findPickColumn(MapV).equals("nonew");
    }

    public void outAnswer() {
        System.out.println(map1Vert + "=" + Map1.get("b"));
        System.out.println(map2Vert + "=" + Map2.get("b"));
        System.out.println(map3Vert + "=" + Map3.get("b"));
        list.clear();
        Map1.forEach((k, v) -> {
            list.add(k);
        });
        list.forEach((i) -> {
            if (!i.equals("b")) System.out.println(i + "=" + "0");
        });
        MapL.put("Lmax", 0.0);
        MapL.forEach((k, v) -> {
            if (-v > 0.0) MapL.replace("Lmax", -v);
        });
        System.out.println("Lmax=" + MapL.get("Lmax"));
    }

    public void conversionToL() {
        Map1.remove("v1");
        Map1.remove("v2");
        Map2.remove("v1");
        Map2.remove("v2");
        Map3.remove("v1");
        Map3.remove("v2");
    }

    public void calculateV(Hashtable<String, Double> Map1, Hashtable<String, Double> Map2, Hashtable<String, Double> Map3, Hashtable<String, Double> MapV, String pickColumnTest, String map1Vert, String map2Vert, String map3Vert) {
        ArrayList<String> list = new ArrayList<String>();
        double lambda = 0.0;
        Map1.forEach((k, v) -> {
            list.add(k);
        });

        //pickColumnTest = findPickColumn(MapV, list); // выбираем положительную не базисную гамму
        if (pickColumnTest.equals("none")) {
            System.out.println("Нет положительных гамм, нет решения");
            System.exit(95);
        }
        int pickRow = findPickRow(pickColumnTest, Map1, Map2, Map3); // находим разрещающую строку
        //System.out.println(pickColumnTest);
        //System.out.println(pickRow);
        switch (pickRow) { // Находим лямбда
            case 1:
                lambda = 1 / Map1.get(pickColumnTest);
                break;
            case 2:
                lambda = 1 / Map2.get(pickColumnTest);
                break;
            case 3:
                lambda = 1 / Map3.get(pickColumnTest);
                break;
        }
        switch (pickRow) { // Заполняем разрешающую строку и столбец;
            case 1:
                Map2.put(pickColumnTest + "tmp", Map2.get(pickColumnTest) * (-lambda));
                Map3.put(pickColumnTest + "tmp", Map3.get(pickColumnTest) * (-lambda));
                MapV.put(pickColumnTest + "tmp", MapV.get(pickColumnTest) * (-lambda));
                String finalPickColumnTest = pickColumnTest;
                double finalLambda = lambda;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest)) Map1.put(i + "tmp", Map1.get(i) * finalLambda);
                    else Map1.put(i + "tmp", finalLambda);
                });
                break;
            case 2:
                Map1.put(pickColumnTest + "tmp", Map1.get(pickColumnTest) * (-lambda));
                Map3.put(pickColumnTest + "tmp", Map3.get(pickColumnTest) * (-lambda));
                MapV.put(pickColumnTest + "tmp", MapV.get(pickColumnTest) * (-lambda));
                String finalPickColumnTest1 = pickColumnTest;
                double finalLambda1 = lambda;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest1)) Map2.put(i + "tmp", Map2.get(i) * finalLambda1);
                    else Map2.put(i + "tmp", finalLambda1);
                });
                break;
            case 3:
                Map1.put(pickColumnTest + "tmp", Map1.get(pickColumnTest) * (-lambda));
                Map2.put(pickColumnTest + "tmp", Map2.get(pickColumnTest) * (-lambda));
                MapV.put(pickColumnTest + "tmp", MapV.get(pickColumnTest) * (-lambda));
                String finalPickColumnTest2 = pickColumnTest;
                double finalLambda2 = lambda;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest2)) Map3.put(i + "tmp", Map3.get(i) * finalLambda2);
                    else Map3.put(i + "tmp", finalLambda2);
                });
                break;
        }
        switch (pickRow) { // Заполняем остальные элементы;
            case 1:
                String finalPickColumnTest3 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest3)) {
                        Map2.put(i + "tmp", Map2.get(finalPickColumnTest3 + "tmp") * Map1.get(i));
                        Map3.put(i + "tmp", Map3.get(finalPickColumnTest3 + "tmp") * Map1.get(i));
                        MapV.put(i + "tmp", MapV.get(finalPickColumnTest3 + "tmp") * Map1.get(i));
                    }
                });
                break;
            case 2:
                String finalPickColumnTest4 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest4)) {
                        Map1.put(i + "tmp", Map1.get(finalPickColumnTest4 + "tmp") * Map2.get(i));
                        Map3.put(i + "tmp", Map3.get(finalPickColumnTest4 + "tmp") * Map2.get(i));
                        MapV.put(i + "tmp", MapV.get(finalPickColumnTest4 + "tmp") * Map2.get(i));
                    }
                });
                break;
            case 3:
                String finalPickColumnTest5 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest5)) {
                        Map2.put(i + "tmp", Map2.get(finalPickColumnTest5 + "tmp") * Map3.get(i));
                        Map1.put(i + "tmp", Map1.get(finalPickColumnTest5 + "tmp") * Map3.get(i));
                        MapV.put(i + "tmp", MapV.get(finalPickColumnTest5 + "tmp") * Map3.get(i));
                    }
                });
                break;
        }
        switch (pickRow) { // Замена;
            case 1:
                list.forEach((i) -> {
                    if (!i.equals(pickColumnTest)) {
                        //Map1.replace(i,Map1.get(i+"tmp")+Map1.get(i));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp") + Map2.get(i));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp") + Map3.get(i));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp") + MapV.get(i));
                    } else {
                        Map1.replace(i, Map1.get(i + "tmp"));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp"));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp"));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp"));
                    }
                    MapV.remove(i + "tmp");
                });
                Map1.put(map1Vert, Map1.get(pickColumnTest));
                Map1.remove(pickColumnTest);
                Map2.put(map1Vert, Map2.get(pickColumnTest));
                Map2.remove(pickColumnTest);
                Map3.put(map1Vert, Map3.get(pickColumnTest));
                Map3.remove(pickColumnTest);
                map1Vert = pickColumnTest;
                MapV.put(map1Vert, MapV.get(pickColumnTest));
                MapV.remove(pickColumnTest);
                break;
            case 2:
                list.forEach((i) -> {
                    if (!i.equals(pickColumnTest)) {
                        Map1.replace(i, Map1.get(i + "tmp") + Map1.get(i));
                        Map1.remove(i + "tmp");
                        //Map2.replace(i,Map2.get(i+"tmp")+Map2.get(i));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp") + Map3.get(i));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp") + MapV.get(i));
                    } else {
                        Map1.replace(i, Map1.get(i + "tmp"));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp"));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp"));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp"));
                    }
                    MapV.remove(i + "tmp");

                });
                Map1.put(map2Vert, Map1.get(pickColumnTest));
                Map1.remove(pickColumnTest);
                Map2.put(map2Vert, Map2.get(pickColumnTest));
                Map2.remove(pickColumnTest);
                Map3.put(map2Vert, Map3.get(pickColumnTest));
                Map3.remove(pickColumnTest);
                MapV.put(map2Vert, MapV.get(pickColumnTest));
                MapV.remove(pickColumnTest);
                map2Vert = pickColumnTest;
                break;
            case 3:
                String finalPickColumnTest8 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest8)) {
                        Map1.replace(i, Map1.get(i + "tmp") + Map1.get(i));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp") + Map2.get(i));
                        Map2.remove(i + "tmp");
                        //Map3.replace(i,Map3.get(i+"tmp")+Map3.get(i));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp") + MapV.get(i));
                    } else {
                        Map1.replace(i, Map1.get(i + "tmp"));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp"));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp"));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp"));
                    }
                    MapV.remove(i + "tmp");
                });
                Map1.put(map3Vert, Map1.get(pickColumnTest));
                Map1.remove(pickColumnTest);
                Map2.put(map3Vert, Map2.get(pickColumnTest));
                Map2.remove(pickColumnTest);
                Map3.put(map3Vert, Map3.get(pickColumnTest));
                Map3.remove(pickColumnTest);
                MapV.put(map3Vert, MapV.get(pickColumnTest));
                MapV.remove(pickColumnTest);
                map3Vert = pickColumnTest;
                break;
        }
    }

    public void calculateV(Hashtable<String, Double> Map1, Hashtable<String, Double> Map2, Hashtable<String, Double> Map3, Hashtable<String, Double> MapV, String pickColumnTest) {
        ArrayList<String> list = new ArrayList<String>();
        double lambda = 0.0;
        Map1.forEach((k, v) -> {
            list.add(k);
        });

        //pickColumnTest = findPickColumn(MapV, list); // выбираем положительную не базисную гамму
        if (pickColumnTest.equals("none")) {
            System.out.println("Нет положительных гамм, нет решения");
            System.exit(95);
        }
        int pickRow = findPickRow(pickColumnTest, Map1, Map2, Map3); // находим разрещающую строку
        //System.out.println(pickColumnTest);
        //System.out.println(pickRow);
        switch (pickRow) { // Находим лямбда
            case 1:
                lambda = 1 / Map1.get(pickColumnTest);
                break;
            case 2:
                lambda = 1 / Map2.get(pickColumnTest);
                break;
            case 3:
                lambda = 1 / Map3.get(pickColumnTest);
                break;
        }
        switch (pickRow) { // Заполняем разрешающую строку и столбец;
            case 1:
                Map2.put(pickColumnTest + "tmp", Map2.get(pickColumnTest) * (-lambda));
                Map3.put(pickColumnTest + "tmp", Map3.get(pickColumnTest) * (-lambda));
                MapV.put(pickColumnTest + "tmp", MapV.get(pickColumnTest) * (-lambda));
                String finalPickColumnTest = pickColumnTest;
                double finalLambda = lambda;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest)) Map1.put(i + "tmp", Map1.get(i) * finalLambda);
                    else Map1.put(i + "tmp", finalLambda);
                });
                break;
            case 2:
                Map1.put(pickColumnTest + "tmp", Map1.get(pickColumnTest) * (-lambda));
                Map3.put(pickColumnTest + "tmp", Map3.get(pickColumnTest) * (-lambda));
                MapV.put(pickColumnTest + "tmp", MapV.get(pickColumnTest) * (-lambda));
                String finalPickColumnTest1 = pickColumnTest;
                double finalLambda1 = lambda;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest1)) Map2.put(i + "tmp", Map2.get(i) * finalLambda1);
                    else Map2.put(i + "tmp", finalLambda1);
                });
                break;
            case 3:
                Map1.put(pickColumnTest + "tmp", Map1.get(pickColumnTest) * (-lambda));
                Map2.put(pickColumnTest + "tmp", Map2.get(pickColumnTest) * (-lambda));
                MapV.put(pickColumnTest + "tmp", MapV.get(pickColumnTest) * (-lambda));
                String finalPickColumnTest2 = pickColumnTest;
                double finalLambda2 = lambda;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest2)) Map3.put(i + "tmp", Map3.get(i) * finalLambda2);
                    else Map3.put(i + "tmp", finalLambda2);
                });
                break;
        }
        switch (pickRow) { // Заполняем остальные элементы;
            case 1:
                String finalPickColumnTest3 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest3)) {
                        Map2.put(i + "tmp", Map2.get(finalPickColumnTest3 + "tmp") * Map1.get(i));
                        Map3.put(i + "tmp", Map3.get(finalPickColumnTest3 + "tmp") * Map1.get(i));
                        MapV.put(i + "tmp", MapV.get(finalPickColumnTest3 + "tmp") * Map1.get(i));
                    }
                });
                break;
            case 2:
                String finalPickColumnTest4 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest4)) {
                        Map1.put(i + "tmp", Map1.get(finalPickColumnTest4 + "tmp") * Map2.get(i));
                        Map3.put(i + "tmp", Map3.get(finalPickColumnTest4 + "tmp") * Map2.get(i));
                        MapV.put(i + "tmp", MapV.get(finalPickColumnTest4 + "tmp") * Map2.get(i));
                    }
                });
                break;
            case 3:
                String finalPickColumnTest5 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest5)) {
                        Map2.put(i + "tmp", Map2.get(finalPickColumnTest5 + "tmp") * Map3.get(i));
                        Map1.put(i + "tmp", Map1.get(finalPickColumnTest5 + "tmp") * Map3.get(i));
                        MapV.put(i + "tmp", MapV.get(finalPickColumnTest5 + "tmp") * Map3.get(i));
                    }
                });
                break;
        }
        switch (pickRow) { // Замена;
            case 1:
                String finalPickColumnTest6 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest6)) {
                        //Map1.replace(i,Map1.get(i+"tmp")+Map1.get(i));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp") + Map2.get(i));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp") + Map3.get(i));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp") + MapV.get(i));
                    } else {
                        Map1.replace(i, Map1.get(i + "tmp"));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp"));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp"));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp"));
                    }
                    MapV.remove(i + "tmp");
                });
                Map1.put(map1Vert, Map1.get(pickColumnTest));
                Map1.remove(pickColumnTest);
                Map2.put(map1Vert, Map2.get(pickColumnTest));
                Map2.remove(pickColumnTest);
                Map3.put(map1Vert, Map3.get(pickColumnTest));
                Map3.remove(pickColumnTest);
                map1Vert = pickColumnTest;
                MapV.put(map1Vert, MapV.get(pickColumnTest));
                MapV.remove(pickColumnTest);
                break;
            case 2:
                String finalPickColumnTest7 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest7)) {
                        Map1.replace(i, Map1.get(i + "tmp") + Map1.get(i));
                        Map1.remove(i + "tmp");
                        //Map2.replace(i,Map2.get(i+"tmp")+Map2.get(i));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp") + Map3.get(i));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp") + MapV.get(i));
                    } else {
                        Map1.replace(i, Map1.get(i + "tmp"));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp"));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp"));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp"));
                    }
                    MapV.remove(i + "tmp");

                });
                Map1.put(map2Vert, Map1.get(pickColumnTest));
                Map1.remove(pickColumnTest);
                Map2.put(map2Vert, Map2.get(pickColumnTest));
                Map2.remove(pickColumnTest);
                Map3.put(map2Vert, Map3.get(pickColumnTest));
                Map3.remove(pickColumnTest);
                MapV.put(map2Vert, MapV.get(pickColumnTest));
                MapV.remove(pickColumnTest);
                map2Vert = pickColumnTest;
                break;
            case 3:
                String finalPickColumnTest8 = pickColumnTest;
                list.forEach((i) -> {
                    if (!i.equals(finalPickColumnTest8)) {
                        Map1.replace(i, Map1.get(i + "tmp") + Map1.get(i));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp") + Map2.get(i));
                        Map2.remove(i + "tmp");
                        //Map3.replace(i,Map3.get(i+"tmp")+Map3.get(i));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp") + MapV.get(i));
                    } else {
                        Map1.replace(i, Map1.get(i + "tmp"));
                        Map1.remove(i + "tmp");
                        Map2.replace(i, Map2.get(i + "tmp"));
                        Map2.remove(i + "tmp");
                        Map3.replace(i, Map3.get(i + "tmp"));
                        Map3.remove(i + "tmp");
                        MapV.replace(i, MapV.get(i + "tmp"));
                    }
                    MapV.remove(i + "tmp");
                });
                Map1.put(map3Vert, Map1.get(pickColumnTest));
                Map1.remove(pickColumnTest);
                Map2.put(map3Vert, Map2.get(pickColumnTest));
                Map2.remove(pickColumnTest);
                Map3.put(map3Vert, Map3.get(pickColumnTest));
                Map3.remove(pickColumnTest);
                MapV.put(map3Vert, MapV.get(pickColumnTest));
                MapV.remove(pickColumnTest);
                map3Vert = pickColumnTest;
                break;
        }
    }

    public void findGamma(Hashtable<String, Double> MapC, Hashtable<String, Double> MapGamma) {
        list.clear();
        Map1.forEach((k, v) -> {
            list.add(k);
        });

        list.forEach((i) -> { // Находим гамма коэфиценты V
            double test = MapC.get(map1Vert) * Map1.get(i) + MapC.get(map2Vert) * Map2.get(i) + MapC.get(map3Vert) * Map3.get(i) - MapC.get(i);
            MapGamma.put(i, test);
        });
    }

    public int findPickRow(String pickColumn, Hashtable<String, Double> Map1, Hashtable<String, Double> Map2, Hashtable<String, Double> Map3) {
        //System.out.println(list.toString());
        double M1 = Map1.get(pickColumn) > 0 ? Map1.get("b") / Map1.get(pickColumn) : 10000;
        double M2 = Map2.get(pickColumn) > 0 ? Map2.get("b") / Map2.get(pickColumn) : 10000;
        double M3 = Map3.get(pickColumn) > 0 ? Map3.get("b") / Map3.get(pickColumn) : 10000;
        //System.out.println(M1+" "+M2+" "+M3);
        if (M1 < M2 && M1 < M3) {
            return 1; // 1 строка
        } else if (M2 < M1 && M2 < M3) {
            return 2; // 2 строка
        } else {
            return 3; // 3 строка
        }
    }

    public String findPickColumn(Hashtable<String, Double> MapV) { // Выбираем разрещающий столбец
        ArrayList <String> lists = new ArrayList<>();
        MapV.forEach((k,v) -> {
            lists.add(k);
        });
        for (String s : lists) {
            if (!s.equals("b")) {
                System.out.println(MapV+"findPick635");
                if (MapV.get(s) > 0.0) {
                    return s;
                }
            }
        }
        return "nonew";
    }

    public void initMaps(Hashtable<String, Double> map, int[] array) { // Выбираем разрещающую строку
        if (array.length == 6) {
            map.put("x1", (double) array[0]);
            map.put("x2", (double) array[1]);
            map.put("x3", (double) array[2]);
            map.put("x4", (double) array[3]);
            map.put("x5", (double) array[4]);
            map.put("b", (double) array[5]);
        }
        if (array.length == 5) {
            map.put("x1", (double) -array[0]);
            map.put("x2", (double) -array[1]);
            map.put("x3", (double) -array[2]);
            map.put("x4", (double) -array[3]);
            map.put("x5", (double) -array[4]);
            map.put("b", 0.0);
        }
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
