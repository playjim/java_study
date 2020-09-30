package example;

class MyRun1 implements Runnable{
    Thread thread;
    MyRun1(String name){
        thread = new Thread(this, name);
        thread.start();
    }
    public void run()
    {
        System.out.println("Запуск потока " + thread.getName());
/*
В этом месте следует реализовать циклический вычислительный процесс необходимой длительности.
Процесс должен выводить информацию в консоль. Длительность процесса и периодичность вывода информации
подбирается опытным путем так чтобы в консоли было видно переключение процессора с одного потока на другой.
При этом интенсивность вывода должна быть умеренной.
*/
        try {
            Thread.sleep(0);
            for(int i=0;i < 32;i++){
                System.out.println("Работа потока "+thread.getName()+" счет:"+i);
                System.out.printf("Квадрат числа %d равен %d \n", i, i * i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Завершение потока " + thread.getName());
    }
}

class MyRun2 implements Runnable{
    Thread thread;
    MyRun2(String name){
        thread = new Thread(this, name);
        thread.start();
    }
    public void run()
    {
        System.out.println("Запуск потока " + thread.getName());
        try {
            Thread.sleep(0);
            for(int i=0;i < 10;i++){
                System.out.println("Работа потока "+thread.getName()+" счет:"+i);
                System.out.printf("Квадрат числа %d равен %d \n", i, i * i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/*
В этом месте следует реализовать циклический вычислительный процесс необходимой длительности.
Процесс должен выводить информацию в консоль. Длительность процесса и периодичность вывода информации
подбирается опытным путем так чтобы в консоли было видно переключение процессора с одного потока на другой.
При этом интенсивность вывода должна быть умеренной.
*/
        System.out.println("Завершение потока " + thread.getName());
    }
}

public class example {
    public static void main(String[] args) {
        MyRun1 mR1 = new MyRun1("Child1");
        MyRun2 mR2 = new MyRun2("Child2");
        try{
            mR1.thread.join();
            mR2.thread.join();
        }
        catch(InterruptedException exc)
        {
            System.out.println("прерывание основногот потока");
        }
        Thread mainThread = Thread.currentThread();
        System.out.println("Текущий поток: " + mainThread.getName());
    }
}