import java.io.IOException;

public class main {
    public static final int threadCount = 3;
    public static final Object lock = new Object();
    public static int currentThread = 0;

    public static void main(String[] args) {
        System.out.println("С любовью Максимов Никита РИБО-05-22");
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new programm(i));
            threads[i].start();
        }


        try { // нажатие клавиши
            System.in.read();
            System.out.println("Программа завершает работу");
            for (Thread thread : threads) {
                thread.interrupt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

