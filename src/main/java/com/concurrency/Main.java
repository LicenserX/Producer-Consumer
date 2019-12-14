package com.concurrency;

import com.concurrency.consumers.CarConsumer;
import com.concurrency.products.Car;
import com.concurrency.produsers.CarProducer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Queue<Car> conveyor = new LinkedList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new CarProducer(conveyor, 500, 200));
        executorService.submit(new CarConsumer(conveyor, 200));

        executorService.shutdown();

    }
}
