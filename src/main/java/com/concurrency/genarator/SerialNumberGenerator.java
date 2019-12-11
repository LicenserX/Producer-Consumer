package com.concurrency.genarator;

public class SerialNumberGenerator {
    private long serialNumberGenerator = 0;

    public long nextSerialNumber() {
        return serialNumberGenerator++;
    }
}
