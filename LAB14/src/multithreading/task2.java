package multithreading;

public class task2 {
    // Shared counter variable
    private int counter = 0;

    // Synchronized method to increment the counter
    public synchronized void increment() {
        counter++;
    }

    // Method to get the final counter value
    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        // Shared resource
    	 task2 sharedResource = new  task2();

        // Runnable task for threads
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                sharedResource.increment();
            }
        };

        // Create three threads
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final counter value
        System.out.println("Final Counter Value: " + sharedResource.getCounter());
    }
}
