package thread;

class MyRun1 implements Runnable{
    Thread thread;
    MyRun1(String name){
        thread = new Thread(this, name);
        thread.start();
    }
    public void run()
    {
        System.out.println("Запуск потока " + thread.getName());
        try {
            for(int i=0;i < 32;i++){
                System.out.println("Работа потока "+thread.getName()+" счет:"+i);
                System.out.printf("Квадрат числа %d равен %d \n\n", i, i * i);
            }
        } catch (Exception e) {
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
            for(int i=0;i < 32;i++){
                System.out.println("Работа потока "+thread.getName()+" счет:"+i);
                System.out.printf("Квадрат числа %d равен %d \n\n", i, i * i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Завершение потока " + thread.getName());
    }

}

public class thread {
    public static void main(String[] args) {
        MyRun1 mR1 = new MyRun1("Поток1");
        MyRun2 mR2 = new MyRun2("Поток2");
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