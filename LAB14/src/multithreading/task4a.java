package multithreading;
import java.util.Random;

public class task4a {
    // Shared bank account class
    static class BankAccount {
        private int balance;

        // Constructor to initialize balance
        public BankAccount(int initialBalance) {
            this.balance = initialBalance;
        }

        // Synchronized method for deposit
        public synchronized void deposit(int amount) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount + ", Balance: " + balance);
        }

        // Synchronized method for withdrawal
        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount + ", Balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " attempted to withdraw: " + amount + " but insufficient balance.");
            }
        }

        // Get final balance
        public synchronized int getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) {
        // Create a shared bank account with an initial balance of 1000
        BankAccount account = new BankAccount(1000);

        // Runnable task for clients
        Runnable clientTask = () -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int amount = random.nextInt(200) + 1; // Random amount between 1 and 200
                if (random.nextBoolean()) {
                    account.deposit(amount);
                } else {
                    account.withdraw(amount);
                }

                // Simulate a delay
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Create and start multiple client threads
        Thread client1 = new Thread(clientTask, "Client-1");
        Thread client2 = new Thread(clientTask, "Client-2");
        Thread client3 = new Thread(clientTask, "Client-3");

        client1.start();
        client2.start();
        client3.start();

        // Wait for all threads to finish
        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final balance
        System.out.println("Final Account Balance: " + account.getBalance());
    }
}
