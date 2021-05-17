package life;

import java.util.Arrays;

public class Generation {
<<<<<<< HEAD
    final int countGeneration;
    private char[][] planetField;
    private char[][] newGeneration;

    private int neighborsCount(int i, int j, int count) {
        int io = i;
        int jo = j;
        switch (count) {
            case 0: //NW
                io = i - 1 < 0 ? planetField.length - 1 : i - 1;
                jo = j - 1 < 0 ? planetField.length - 1 : j - 1;
                break;
            case 1: //N
                io = i - 1 < 0 ? planetField.length - 1 : i - 1;
                break;
            case 2: //NE
                io = i - 1 < 0 ? planetField.length - 1 : i - 1;
                jo = j + 1 > planetField.length - 1 ? 0 : j + 1;
                break;
            case 3: //W
                jo = j - 1 < 0 ? planetField.length - 1 : j - 1;
                break;
            case 4: //E
                jo = j + 1 > planetField.length - 1 ? 0 : j + 1;
                break;
            case 5: //SW
                io = i + 1 > planetField.length - 1 ? 0 : i + 1;
                jo = j - 1 < 0 ? planetField.length - 1 : j - 1;
                break;
            case 6: //S
                io = i + 1 > planetField.length - 1 ? 0 : i + 1;
                break;
            case 7: //SE
                io = i + 1 > planetField.length - 1 ? 0 : i + 1;
                jo = j + 1 > planetField.length - 1 ? 0 : j + 1;
                break;
        }
        if (planetField[io][jo] == 'O') {
            return 1;
        } else return 0;
    }

    public Generation(int countGeneration, char[][] planetField) {
        this.planetField = planetField.clone();
        this.newGeneration = new char[planetField.length][planetField.length];
        this.countGeneration = countGeneration;
        for (int i = 0; i < countGeneration; i++) {
            generation();
            //System.out.println(" ");
        }
    }

    private void generation() {
        int check = 0;
        for (int i = 0; i < planetField.length; i++) {
            for (int j = 0; j < planetField.length; j++) {
                check = neighborsCheck(i, j);
                if (planetField[i][j] == 'O') {
                    newGeneration[i][j] = check == 2 || check == 3 ? 'O' : ' ';
                } else {
                    newGeneration[i][j] = check == 3 ? 'O' : ' ';
                }
            }
        }
        for (int i = 0; i < planetField.length; i++) {
            planetField[i] = Arrays.copyOf(newGeneration[i], newGeneration[i].length);
        }
    }

    private int neighborsCheck(int line, int column) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            count += neighborsCount(line, column, i);
        }
        return count;
    }

    public void getNewGeneration() {
        if (countGeneration == 0) {
            for (char[] chars : planetField) {
                for (int j = 0; j < planetField.length; j++) {
                    System.out.print(chars[j]);
                }
                System.out.println();
            }
        } else {
            for (char[] chars : newGeneration) {
                for (int j = 0; j < newGeneration.length; j++) {
                    System.out.print(chars[j]);
                }
                System.out.println();
            }
        }
    }
=======
    int countGeneration;
    static char[][] planetField;
    char[][] newGeneration;

    int neighborsCount(int i, int j, int count) {
        int io = 0;
        int jo = 0;
        switch (count) {
            case 0: //NW
                io = i - 1 < 0 ? planetField.length : i - 1;
                jo = j - 1 < 0 ? planetField.length : j - 1;
                break;
            case 1: //N
                io = i - 1 < 0 ? planetField.length : i - 1;
                break;
            case 2: //NE
                io = i - 1 < 0 ? planetField.length : i - 1;
                jo = j + 1 > planetField.length ? 0 : j + 1;
                break;
            case 3: //W
                jo = j - 1 < 0 ? planetField.length : j - 1;
                break;
            case 4: //E
                jo = j + 1 > planetField.length ? 0 : j + 1;
                break;
            case 5: //SW
                io = i + 1 > planetField.length ? 0 : i + 1;
                jo = j - 1 < 0 ? planetField.length : j - 1;
                break;
            case 6: //S
                io = i + 1 > planetField.length ? 0 : i + 1;
                break;
            case 7: //SE
                io = i + 1 > planetField.length ? 0 : i + 1;
                jo = j + 1 > planetField.length ? 0 : j + 1;
                break;
        }
        if (planetField[io][jo] == 'O') {
            return 1;
        } else return 0;
    }

    public Generation(int countGeneration, char[][] planetField) {
        this.countGeneration = countGeneration;
        this.planetField = planetField;
        generation();
    }

    void generation() {
        for (int i = 0; i <= planetField.length; i++) {
            for (int j = 0; j <= planetField.length; j++) {
                if (planetField[i][j] == 'O') {
                    neighborsCheck(i, j);
                } else {

                }
            }
        }
    }

    int neighborsCheck(int line, int column) {
        int count = 0;
        int step = 0;
        for (int i = 0; i < 8; i++) {
            count+=neighborsCount(line,column,i);
        }
        return count//
    }
>>>>>>> 6ae9334216508aa070150915fb0c0124fe8c8bd7
}