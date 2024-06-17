package com.example.mobil_programming;
public class Contact {
    private long id;
    private String fName;
    private String phone;

    // Constructor, getters and setters
    public Contact(long id, String name, String phone) {
        this.id = id;
        this.fName = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getPhone() {
        return phone;
    }
}