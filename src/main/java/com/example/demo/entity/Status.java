package com.example.demo.entity;

/**
 * Created by yusuf on 4/2/2018.
 */
public enum Status {

    PROSPECTIVE(0),
    CURRENT(1),
    NON_ACTIVE(2);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}