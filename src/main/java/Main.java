import consumers.Consumer;
import genarator.SerialNumberGenerator;
import products.Car;
import produsers.Producer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue<Car> conveyor = new LinkedList<>();
        SerialNumberGenerator numberGenerator = new SerialNumberGenerator();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Producer(conveyor));

        executorService.submit(new Consumer(conveyor, numberGenerator));
        executorService.shutdown();

    }
}
