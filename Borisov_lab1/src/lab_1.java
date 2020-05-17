
import java.io.*;

public class lab_1 {
    public static Student student = new Student();// объект студент
    public static Study study = new Study();
    public static Exam exam = new Exam();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // считывание с клавиатуры
    public static String s;

    public static void main(String[] args) throws Exception {
        init();
        if (student.mode == 1) {
            study();
        } else exam_enter();
    }

    public static void init() throws Exception {
        PrintText("Пожалуйста представься");
        student.name = reader.readLine();
        PrintText("Привет, " + student.name);
        PrintText("Это программа поможет выучить тебе таблицу умножения, а также пройти экзамен.");
        PrintText(student.name + " ,выбери режим: \n1) Обучение  \n2) Экзамен");
        s = reader.readLine();
        student.mode = Integer.parseInt(s);
    } // инициализация

    public static void study() throws Exception {
        PrintText("Круто! Давай учиться!");
        PrintText("Введите количество примеров для проверки: ");
        s = reader.readLine();
        study.level = Integer.parseInt(s);
        PrintText("Решите " + study.level + " примеров");
        for (int i = 0; i < study.level; i++) {
            int a = Gen_Random();
            int b = Gen_Random();
            int c = a * b;
            System.out.println("Сколько будет: " + a + " * " + b + " ?");
            s = reader.readLine();
            int sum = Integer.parseInt(s);
            if (sum == c) {
                System.out.println("Верно!");
                study.right += 1;
            } else {
                System.out.println("Не верно! Правильный ответ: " + c);
                study.wrong += 1;
            }
        }
        System.out.println(student.name + ", из " + study.level + " примеров ты решил верно " + study.right);
    } // режим обучения

    public static void exam_enter() throws Exception {
        PrintText(student.name + ", добро пожаловать на экзамен. Давай проверим твои знания таблицы умножения.");
        PrintText(" Для начала выбери из списка своего преподователя:\n 1) Петрова Наталия Владимировна\n 2) Степанов Егор Егорьевич\n 3) Борисов Дмитрий Яковлевич");
        s = reader.readLine();
        exam.teacher = Integer.parseInt(s);
        PrintText("Преступим к экзамену! Решите 10 примеров: ");
        for (int i = 0; i < 10; i++) {
            int a = Gen_Random();
            int b = Gen_Random();
            int c = a * b;
            System.out.println("Сколько будет: " + a + " * " + b + " ?");
            s = reader.readLine();
            int sum = Integer.parseInt(s);
            if (sum == c) {
                exam.right += 1;
            } else {
                exam.wrong += 1;
            }
        }
        exam.ball_interest = exam.right * 10;
        System.out.println("Процент правильных ответов = " + exam.ball_interest);
        switch (exam.teacher) {
            case (1):
                hyper(5);
                break;
            case (2):
                hyper(10);
                break;
            case (3):
                hyper(15);
                break;
        }
        System.out.println("Ваша оценка: " + exam.ball);
    } // режим экзамена

    public static void hyper(int a) {
        int ball;
        int interest1 = 100 - a;
        int interest2 = 90 - a;
        int interest3 = 80 - a;
        if (exam.ball_interest >= interest1) {
            ball = 5;
        } else if (exam.ball_interest >= interest2 && exam.ball_interest < interest1) {
            ball = 4;
        } else if (exam.ball_interest >= interest3 && exam.ball_interest < interest2) {
            ball = 3;
        } else ball = 2;
        exam.ball = ball;
    } // вычесление оценки исходя из коэфицента учителя.

    public static void PrintText(String text) {
        System.out.println(text);
    }

    public static int Gen_Random() {
        int max = 9, min = 1;
        max -= min;
        double gen = (Math.random() * ++max) + min;
        return (int) gen;
    } // генерация рандомных чисел от 1 до 9

    public static class Student {
        String name;
        int mode;
    }

    public static class Study {
        int level;
        int right = 0;
        int wrong = 0;
    }

    public static class Exam {
        int ball_interest;
        int ball;
        int right = 0;
        int wrong = 0;
        int teacher;
    }
}
