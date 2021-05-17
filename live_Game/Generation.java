package life;

public class Generation {
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
}