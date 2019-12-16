package com.concurrency.jcstress;

import com.concurrency.consumers.CarConsumer;
import com.concurrency.products.Car;
import com.concurrency.produsers.CarProducer;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.ZZ_Result;

import java.util.LinkedList;
import java.util.Queue;

@JCStressTest
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Size is 0, Thread wait.")
@Outcome(id = "false, true", expect = Expect.ACCEPTABLE, desc = "Size <= 200 but > 0. Normal state.")
@State
public class TestThatCarConsumerHasWaiteStateIfQueueIsEmpty {
    private Queue<Car> conveyor = new LinkedList<>();

    @Actor
    public void writer_1() {
        CarProducer carProducer = new CarProducer(conveyor, 200, 300);
        new Thread(carProducer).start();
    }

    @Actor
    public void reader_1() {
        CarConsumer carConsumer = new CarConsumer(conveyor, 200);
        new Thread(carConsumer).start();
    }

    @Arbiter
    public void arbiter(ZZ_Result b) {
        int i = conveyor.size();
        b.r1 = (i == 0);
        b.r2 = (i <= 200);
    }
}
