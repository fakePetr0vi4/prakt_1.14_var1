public class programm implements Runnable {
    private final int id;

    public programm(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (main.lock) {
                while (id != main.currentThread) {
                    try {
                        main.lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.println("Thread-" + id);
                main.currentThread = (main.currentThread + 1) % main.threadCount;
                main.lock.notifyAll();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
