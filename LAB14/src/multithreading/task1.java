package multithreading;
public class task1 {
	public static void main(String[] args) {
        // Thread 1: Print numbers from 1 to 10
        Thread printNumbers = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Number: " + i);
                try {
                    Thread.sleep(50); // Pause for 50 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2: Print squares of numbers from 1 to 10
        Thread printSquares = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Square: " + (i * i));
                try {
                    Thread.sleep(50); // Pause for 50 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        printNumbers.start();
        printSquares.start();
    }
}
