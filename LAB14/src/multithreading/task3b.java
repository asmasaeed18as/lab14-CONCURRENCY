package multithreading;
import java.util.concurrent.ConcurrentHashMap;

public class task3b {
    public static void main(String[] args) {
        // Create a thread-safe ConcurrentHashMap
        ConcurrentHashMap<Integer, String> sharedMap = new ConcurrentHashMap<>();

        // Task to write to the map
        Runnable writeTask = () -> {
            for (int i = 1; i <= 5; i++) {
                sharedMap.put(i, "Value" + i);
                System.out.println(Thread.currentThread().getName() + " added: " + i + " -> Value" + i);
            }
        };

        // Task to read from the map
        Runnable readTask = () -> {
            for (Integer key : sharedMap.keySet()) {
                System.out.println(Thread.currentThread().getName() + " read: " + key + " -> " + sharedMap.get(key));
            }
        };

        // Create and start multiple threads
        Thread writer1 = new Thread(writeTask, "Writer-1");
        Thread writer2 = new Thread(writeTask, "Writer-2");
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

        // Print the final map
        System.out.println("Final Map: " + sharedMap);
    }
}
