package com.concurrency.consumers;

import com.concurrency.products.Car;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class CarConsumer implements Runnable {

    private final Queue<Car> conveyor;
    private final int timeout;

    public CarConsumer(Queue<Car> conveyor, int timeout) {
        this.conveyor = conveyor;
        this.timeout = timeout;
    }

    public void run() {
        while (true) {
            synchronized (conveyor) {
                try {
                    while (conveyor.isEmpty()) {
                        conveyor.wait();
                    }
                    Car car = conveyor.poll();
                    TimeUnit.MILLISECONDS.sleep(timeout);
                    System.out.println("Name and serial were added. Car is ready.");
                    conveyor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
