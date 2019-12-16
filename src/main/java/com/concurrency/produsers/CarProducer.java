package com.concurrency.produsers;

import com.concurrency.products.Car;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CarProducer implements Runnable {

    private final Queue<Car> conveyor;
    private final int maxAmount;
    private final int timeout;

    public CarProducer(Queue<Car> conveyor, int maxAmount, int timeout) {
        this.conveyor = conveyor;
        this.maxAmount = maxAmount;
        this.timeout = timeout;
    }

    public void run() {
        while (true) {
            synchronized (conveyor) {
                try {
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    while (conveyor.size() >= maxAmount) {
                        conveyor.wait();
                    }
                    Car car = new Car();
                    conveyor.add(car);
                    conveyor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
