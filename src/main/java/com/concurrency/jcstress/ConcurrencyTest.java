package com.concurrency.jcstress;

import com.concurrency.products.Car;
import com.concurrency.produsers.CarProducer;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.I_Result;

import java.util.LinkedList;
import java.util.Queue;

@JCStressTest
@Outcome(id = "200", expect = Expect.ACCEPTABLE, desc = "Expected behavior")
@State
public class ConcurrencyTest {
    Queue<Car> conveyor = new LinkedList<>();

    @Actor
    public void writer_1(I_Result s) {
        CarProducer carProducer = new CarProducer(conveyor, 200, 0);
        carProducer.run();
    }

    @Actor
    public void writer_2(I_Result s) {
        CarProducer carProducer = new CarProducer(conveyor, 200, 0);
        carProducer.run();
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = conveyor.size();
    }
}
