package com.example.demo.serializable;

import java.io.Serializable;

/**
 * Created by yusuf on 4/2/2018.
 */
public class NoteObject implements Serializable {

    private long id;
    private String text;
    private long customerId;

    public NoteObject() {
    }

    public NoteObject(long id, String text, long customerId) {
        this.id = id;
        this.text = text;
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
