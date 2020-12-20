import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// C:\Users\dimbo\Documents\GitHub\java_study\lab4_2020\inputText.txt
// C:\Users\dimbo\Documents\GitHub\java_study\lab4_2020\inputTest.txt
// C:\Users\dimbo\Documents\GitHub\java_study\lab4_2020\inputLong.txt
public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<Character, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Укажите директорию файла: ");
        String dir = sc.nextLine();
        FileInputStream fin = new FileInputStream(dir);
        int i = -1;
        while ((i = fin.read()) != -1) {
            if (i >= 97 && i <= 122 || i >= 65 && i <= 90) {
                char c = Character.toLowerCase((char) i);
                if(map.containsKey(c)){
                    map.put(c, map.get(c)+1);
                }
                else map.put(c, 1);
            }
        }
        for(Map.Entry<Character, Integer> item : map.entrySet()){
            System.out.printf("%c: %d \n", item.getKey(), item.getValue());
        }
    }
}
