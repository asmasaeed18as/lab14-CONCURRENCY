package multithreading;

import java.util.concurrent.CopyOnWriteArrayList;

public class task3 {
    public static void main(String[] args) {
        // Create a thread-safe CopyOnWriteArrayList
        CopyOnWriteArrayList<Integer> sharedList = new CopyOnWriteArrayList<>();

        // Task to add numbers to the list
        Runnable addTask = () -> {
            for (int i = 1; i <= 5; i++) {
                sharedList.add(i);
                System.out.println(Thread.currentThread().getName() + " added: " + i);
            }
        };

        // Task to read numbers from the list
        Runnable readTask = () -> {
            for (Integer number : sharedList) {
                System.out.println(Thread.currentThread().getName() + " read: " + number);
            }
        };

        // Create and start multiple threads
        Thread writer1 = new Thread(addTask, "Writer-1");
        Thread writer2 = new Thread(addTask, "Writer-2");
        Thread reader = new Thread(readTask, "Reader");

        writer1.start();
        writer2.start();
        reader.start();

        // Wait for threads to finish
        try {
            writer1.join();
            writer2.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final list
        System.out.println("Final List: " + sharedList);
    }
}
