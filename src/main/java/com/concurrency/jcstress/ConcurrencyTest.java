package com.concurrency.jcstress;

import com.concurrency.consumers.CarConsumer;
import com.concurrency.genarator.SerialNumberGenerator;
import com.concurrency.products.Car;
import com.concurrency.produsers.CarProducer;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LL_Result;

import java.util.LinkedList;
import java.util.Queue;

@JCStressTest
@Outcome(id = "Car, Car", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Expected behavior")
@Outcome(id = "Car, null", expect = Expect.FORBIDDEN, desc = "Expected behavior")
@Outcome(id = "null, Car", expect = Expect.FORBIDDEN, desc = "That's wrong behavior")
@Outcome(id = "null, null", expect = Expect.FORBIDDEN, desc = "That's wrong behavior")
@State
public class ConcurrencyTest {
    Queue<Car> conveyor = new LinkedList<>();
    SerialNumberGenerator numberGenerator = new SerialNumberGenerator();

    @Actor
    public void writer(LL_Result s) {
        CarProducer carProducer = new CarProducer(conveyor);
        carProducer.run();
        s.r1 = conveyor.peek();
    }

    @Actor
    public void reader(LL_Result s) {
        CarConsumer carConsumer = new CarConsumer(conveyor, numberGenerator);
        carConsumer.run();
        s.r2 = conveyor.peek();
    }
}
