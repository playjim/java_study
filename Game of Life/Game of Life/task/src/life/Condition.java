package life;

import java.util.Random;

public class Condition {
<<<<<<< HEAD
    final int sizeUniverse;
    final long seedRandom;
    protected char[][] planetField;
=======
    int sizeUniverse;
    long seedRandom;
    char[][] planetField;
>>>>>>> 6ae9334216508aa070150915fb0c0124fe8c8bd7

    public Condition(int sizeUniverse, long seedRandom) {
        this.sizeUniverse = sizeUniverse;
        this.seedRandom = seedRandom;
        this.planetField = new char[sizeUniverse][sizeUniverse];
        bigExplosion();
    }
    private void bigExplosion(){
        Random random = new Random(seedRandom);
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                if (random.nextBoolean()) {
                    planetField[i][j] = 'O';
                } else planetField[i][j] = ' ';
<<<<<<< HEAD
            }
=======
                //System.out.print(planetField[i][j]);
            }
            //System.out.println("");
>>>>>>> 6ae9334216508aa070150915fb0c0124fe8c8bd7
        }
    }
}