package com.example.demo.serializable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yusuf on 4/2/2018.
 */
public class CustomerObject implements Serializable {

    private long id;
    private String status;
    private Date creationDate;
    private String name;
    private String address;
    private String phone;

    public CustomerObject() {
    }

    public CustomerObject(long id, String status, Date creationDate, String name, String address, String phone) {
        this.id = id;
        this.status = status;
        this.creationDate = creationDate;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
