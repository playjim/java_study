import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Hashtable<String,Integer> map = new Hashtable<String,Integer>();
        int i = 0;
        Scanner sc = new Scanner(System.in);
        while(true){
            i++;
            int enter = sc.nextInt();
            if(enter == 0){
                return;
            }
            map.put("x"+Integer.toString(i), enter);
            System.out.println(map.toString());
        }
        //System.out.println(map.toString());
    }
}
