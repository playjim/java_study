public class test {
    public static void main(String[] args){
        int x = generateRandomInt(9);
        System.out.println(x);
    }
    public static double getRandomIntegerBetweenRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
    public static int generateRandomInt(int upperRange){
        Random random = new test();
        return random.nextInt(upperRange);
    }
    public static class
}
