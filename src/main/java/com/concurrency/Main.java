package com.concurrency;

import com.concurrency.consumers.CarConsumer;
import com.concurrency.genarator.SerialNumberGenerator;
import com.concurrency.products.Car;
import com.concurrency.produsers.CarProducer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Queue<Car> conveyor = new LinkedList<>();
        SerialNumberGenerator numberGenerator = new SerialNumberGenerator();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new CarProducer(conveyor));
        executorService.submit(new CarConsumer(conveyor, numberGenerator));

        executorService.shutdown();

    }
}
