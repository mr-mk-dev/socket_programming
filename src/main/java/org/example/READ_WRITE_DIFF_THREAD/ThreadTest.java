package org.example.READ_WRITE_DIFF_THREAD;

public class ThreadTest {
    public static void main(String[] args) {

        // 🔴 Thread 1 (Heavy loop)
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("Hello World");
                
                // try commenting/uncommenting this 👇
//                 Thread.yield();

                // try this also 👇
                 try { Thread.sleep(1000); } catch (Exception e) {}
            }
        });

        // 🔵 Thread 2 (Delayed print)
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000); // wait 2 sec
                System.out.println("---------------------------------------------------------------------------------------------------------===== DATE: " + java.time.LocalDateTime.now() + " =====");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 🔥 Set priority
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}