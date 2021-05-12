package life;

import java.util.Random;

public class Condition {
    int sizeUniverse;
    long seedRandom;
    char[][] planetField;

    public Condition(int sizeUniverse, long seedRandom) {
        this.sizeUniverse = sizeUniverse;
        this.seedRandom = seedRandom;
        this.planetField = new char[sizeUniverse][sizeUniverse];
        bigExplosion();
    }
    void bigExplosion(){
        Random random = new Random(seedRandom);
        for (int i = 0; i < sizeUniverse; i++) {
            for (int j = 0; j < sizeUniverse; j++) {
                if (random.nextBoolean()) {
                    planetField[i][j] = 'O';
                } else planetField[i][j] = ' ';
                //System.out.print(planetField[i][j]);
            }
            //System.out.println("");
        }
    }
}