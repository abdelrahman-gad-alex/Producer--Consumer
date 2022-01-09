package com.example.socket;

public class HelloMessage {
    private  int age;
    private String name;

    public HelloMessage() {}

    public HelloMessage(String name , int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
