package consumers;

import genarator.SerialNumberGenerator;
import products.Car;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

    private final Queue<Car> conveyor;
    private final SerialNumberGenerator generator;
    private int i = 0;

    public Consumer(Queue<Car> conveyor, SerialNumberGenerator generator) {
        this.conveyor = conveyor;
        this.generator = generator;
    }

    public void run() {
        while (true) {
            synchronized (conveyor) {
                try {
                    if (conveyor.isEmpty()) {
                        stopConveyor();
                    }
                    creatingCar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Car car = conveyor.poll();
                System.out.println(i++ + " The car was taken from conveyor.");

                if (car != null) {
                    createTechnicalPassport(car);
                    System.out.println("Name and serial were added. Car is ready.");
                }
            }
        }
    }

    private void createTechnicalPassport(Car car) {
        car.setName("Nissan-GTR.");
        car.setSerialNumber(generator.nextSerialNumber());
    }

    private void creatingCar() throws InterruptedException {
        System.out.println("The car is being built...");
        TimeUnit.MILLISECONDS.sleep(500);
    }

    private void stopConveyor() throws InterruptedException {
        System.out.println("No cars at the conveyor, waite...");
        conveyor.wait();
    }
}
