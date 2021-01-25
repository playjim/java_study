import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class test {
    public static void main(String[] args) {
        MyTokenTree tree = new MyTokenTree();
        tree.add("test");
        tree.add("asd");
        tree.show();
    }
    public void parserLine(String str) {
        String token;
        str = str.toLowerCase();
        Scanner sc = new Scanner(str);
        while (true) {
            token = sc.next();
            if (filter(token) != null) {
                MyToken md = new MyToken(filter(token));
                MyTokenTree tree = new MyTokenTree();
                tree.add(filter(token));
            } else ;
        }
    }

    public String filter(String token) {
        StringBuilder str = new StringBuilder();
        if (token.length() < 2) {
            return null;
        }
        char[] result = token.toCharArray();
        ArrayList<Character> chr = new ArrayList<>();
        for (char c : result) {
            chr.add(c);
        }
        if ((int) chr.get(0) < 97 || (int) chr.get(0) > 122) {
            chr.remove(0);
        }
        if ((int) chr.get(chr.size() - 1) < 97 || (int) chr.get(chr.size() - 1) > 122) {
            chr.remove(chr.size() - 1);
        }
        if (chr.size() == 0) {
            return null;
        }
        for (char c : chr) {
            if ((int) c < 97 || (int) c > 122) {
                return null;
            }
        }
        for (char c : chr) {
            str.append(c);
        }
        return str.toString();
    }


    static class MyToken implements Comparable<MyToken>{
        String token;

        MyToken(String token) {
            this.token = token;
        }
        public String toString() {
            return token;
        }

        @Override
        public int compareTo(MyToken o) {
            return token.compareTo(o.token);
        }
    }

    static class MyTokenTree {
        TreeSet<MyToken> tokens = new TreeSet<>();
        void add(String cmd) {
            tokens.add(new MyToken(cmd));
        }
        void show() {
            for (MyToken o : tokens) {
                System.out.println(o.toString());
            }
        }
    }
}
