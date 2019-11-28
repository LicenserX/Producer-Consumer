package produsers;

import products.Car;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private final Queue<Car> conveyor;
    private int i = 0;

    public Producer(Queue<Car> conveyor) {
        this.conveyor = conveyor;
    }

    public void run() {
        while (true) {
            synchronized (conveyor) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                conveyor.add(new Car());
                System.out.println(i++ + " New car body was created");

                if (conveyor.size() > 10) {
                    startConveyor();
                }
            }
        }
    }

    private void startConveyor() {
        System.out.println("More then 10 car bodies was created, notifyAll...");
        conveyor.notifyAll();
    }
}
