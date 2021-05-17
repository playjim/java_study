package cinema;

public class Cinema {
    public void printCinemaHall() {
        System.out.println("Cinema:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.printCinemaHall();
    }
}