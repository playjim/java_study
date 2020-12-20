import java.util.ArrayList;
import java.util.Scanner;

public class myFamily {
        public static class Human {
                String name;
                int age;
                String relationship;
                public Human(String relationship,String name,int age){
                        this.relationship = relationship;
                        this.name = name;
                        this.age = age;
                }
                public String toString(){
                        return relationship+","+name+","+age;
                }
        }
        public static void main(String[] args) {
                ArrayList<Human> member = new ArrayList<>();
                member.add(new Human("mother","Alena",48));
                member.add(new Human("father","Ivan",49));
                member.add(new Human("son","Dmitry",23));
                member.add(new Human("daughter","Nastya",24));
                Scanner sc = new Scanner(System.in);
                String command;
                while(true){
                        System.out.print(">");
                        command = sc.nextLine();
                        if(command.equals("q"))
                                break;
                        switch(command) {
                                case "1":
                                        System.out.print("1.Процесс добавление нового объекта в список: \nВведите родство > ");
                                        String relationship = sc.nextLine();
                                        System.out.print("Введите имя > ");
                                        String name = sc.nextLine();
                                        System.out.print("Введите возраст > ");
                                        String age = sc.nextLine();
                                        int ages = Integer.parseInt(age);
                                        member.add(new Human(relationship, name, ages));
                                        break;
                                case "2":
                                        System.out.println("2.Удаление объекта из списка. \nДля удаления объекта введите его характеристики:");
                                        System.out.print("Введите родство > ");
                                        relationship = sc.nextLine();
                                        System.out.print("Введите имя > ");
                                        name = sc.nextLine();
                                        System.out.print("Введите возраст > ");
                                        age = sc.nextLine();
                                        ages = Integer.parseInt(age);
                                        for (int i = 0; i < member.size(); i++) {
                                                if (member.get(i).name.equals(name) && member.get(i).relationship.equals(relationship) && member.get(i).age == ages) {
                                                        member.remove(i);
                                                }
                                        }
                                        break;
                                case "3":
                                        System.out.println("3.Вывод списка:");
                                        for (int i = 0; i < member.size(); i++) {
                                                System.out.println(member.get(i));
                                        }
                                        break;
                                case "help":
                                        help();
                                default:
                                        System.out.println("неверная команда");
                        }
                }
        }
        static void help()
        {
                System.out.println("q - Завершение программы");
                System.out.println("1. Добавить новый объект в список.");
                System.out.println("2. Удалить объект из списка.");
                System.out.println("3. Вывести список в консоль.");
                System.out.println("help - Показать помощь");
        }
}
