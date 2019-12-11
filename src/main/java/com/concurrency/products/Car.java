package com.concurrency.products;

import com.concurrency.utils.Converter;

public class Car {
    private String name;
    private String serialNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = Converter.getFormattedSerialNumber(serialNumber);
    }
}
