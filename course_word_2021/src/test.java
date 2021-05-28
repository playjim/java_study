import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class test {
    Hashtable<String, Double> testede = new Hashtable<String, Double>();

    public static void main(String[] args) {
        test main = new test();
        main.tested(main.testede);
        System.out.println(main.testede.toString());
    }
    public void tested(Hashtable<String, Double> ABC){
        ABC.put("lol",123.0);
        for (int i =0; i < ABC.size(); i++){
            System.out.println(ABC.get(0));
        }
    }
}
