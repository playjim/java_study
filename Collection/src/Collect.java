import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

public class Collect {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CatsTree tree = new CatsTree();
        Scanner scanner = new Scanner(System.in);
        String cmd = "";
        boolean cont = true;
        while (cont) {
            System.out.print(">");
            cmd = scanner.next();
            switch (cmd) {
                case "add":
                    tree.add(scanner.nextLine());
                    break;
                case "quit":
                    cont = false;
                    break;
                case "show":
                    tree.show();
                    break;
                case "delete":
                    tree.del(scanner.next());
                    break;
                case "save":
                    tree.save();
                    break;
                case "get":
                    tree.get();
                    break;
                case "find":
                    break;
            }
        }
    }


    static class Cat implements Comparable<Cat>, Serializable { // дерево и серелизация для сохранения в файле
        String name;
        int tails;
        int paws;

        public String toString() {
            return name + " " + tails + " " + paws;
        }

        Cat(String n, int t, int p) {
            name = n;
            tails = t;
            paws = p;
        }

        Cat(String n) {
            name = n;
        }

        @Override
        public int compareTo(Cat cat) {
            return (cat.name.compareTo(name));
        }
    }

}

class CatsTree implements Serializable {
    TreeSet<Collect.Cat> cats = new TreeSet<>();

    void add(String cmd) {
        Scanner scanner = new Scanner(cmd);
        String name = scanner.next();
        int tails = scanner.nextInt();
        int paws = scanner.nextInt();
        cats.add(new Collect.Cat(name, tails, paws));
    }

    void show() {
        for (Collect.Cat cat : cats) {
            System.out.println(cat.toString());
        }
    }

    void del(String cmd) {
        Scanner scanner = new Scanner(cmd);
        String name = scanner.next();
        Collect.Cat catDelete = new Collect.Cat(name);

        boolean remCat = cats.remove(catDelete);
        if (remCat) {
            System.out.println(catDelete.name + " объект удален");
        } else System.out.println("Такого объекта нет");
    }

    void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("catsTree.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cats);
        oos.flush();
    }

    void get() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("catsTree.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        cats = (TreeSet<Collect.Cat>) ois.readObject();
    }
}

