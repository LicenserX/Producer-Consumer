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
import org.openjdk.jcstress.infra.results.ZZZ_Result;

import java.util.LinkedList;
import java.util.Queue;

@JCStressTest
@Outcome(id = "true, true, false", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Size is 200, Thread wait.")
@Outcome(id = "true, false, false", expect = Expect.ACCEPTABLE, desc = "Size <= 200 but > 0. Normal state.")
@Outcome(id = "true, false, true", expect = Expect.FORBIDDEN, desc = "Size > 200. Illegal state.")
@State
public class TestThatMaxNumberOfQueueElementsNoMoreThanMaxAmountVariable {
    private Queue<Car> conveyor = new LinkedList<>();

    @Actor
    public void writer_1() {
        CarProducer carProducer = new CarProducer(conveyor, 200, 0);
        new Thread(carProducer).start();
    }

    @Actor
    public void reader_1() {
        CarConsumer carConsumer = new CarConsumer(conveyor, 300);
        new Thread(carConsumer).start();
    }

    @Arbiter
    public void arbiter(ZZZ_Result b) {
        int i = conveyor.size();
        b.r1 = (i >= 0);
        b.r2 = (i == 200);
        b.r3 = (i > 200);
    }
}
