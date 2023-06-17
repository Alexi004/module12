package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FizzBuzz {
    private final int n;
    private final BlockingQueue<String> queue;

    public FizzBuzz(int n) {
        this.n = n;
        this.queue = new ArrayBlockingQueue<>(n);
    }

    public void fizz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                try {
                    queue.put("fizz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void buzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                try {
                    queue.put("buzz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fizzBuzz() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                try {
                    queue.put("fizzbuzz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                try {
                    queue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;

        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
        Thread threadD = new Thread(fizzBuzz::number);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}