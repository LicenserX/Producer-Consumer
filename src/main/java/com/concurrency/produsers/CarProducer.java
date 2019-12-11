package com.concurrency.produsers;

import com.concurrency.products.Car;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CarProducer implements Runnable {

    private final Queue<Car> conveyor;
    private int i = 0;

    public CarProducer(Queue<Car> conveyor) {
        this.conveyor = conveyor;
    }

    public void run() {
        while (i < 500) {
            synchronized (conveyor) {
                delayBetweenOperations();

                conveyor.add(new Car());
                System.out.println(i++ + " New car body was created");

                if (conveyor.size() > 10) {
                    startConveyor();
                }
            }
        }
    }

    private void delayBetweenOperations() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startConveyor() {
        System.out.println("More then 10 car bodies was created, notifyAll...");
        conveyor.notifyAll();
    }
}
