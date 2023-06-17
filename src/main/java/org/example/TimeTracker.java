package org.example;

import java.time.Duration;
import java.time.Instant;

public class TimeTracker {

    public static void main(String[] args) {
        Thread timeThread = new Thread(TimeTracker::displayElapsedTime);
        Thread messageThread = new Thread(TimeTracker::displayMessage);

        timeThread.start();
        messageThread.start();

        try {
            Thread.sleep(Duration.ofMinutes(5).toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeThread.interrupt();
        messageThread.interrupt();
    }

    private static void displayElapsedTime() {
        Instant startTime = Instant.now();

        while (!Thread.interrupted()) {
            Instant currentTime = Instant.now();
            Duration elapsedTime = Duration.between(startTime, currentTime);
            long seconds = elapsedTime.getSeconds();

            System.out.println("Пройшло часу: " + seconds + " секунд");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void displayMessage() {
        int count = 0;

        while (!Thread.interrupted()) {
            if (count % 5 == 0) {
                System.out.println("Минуло 5 секунд");
            }
            count++;

            try {
                Thread.sleep(1000); // Затримка 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}