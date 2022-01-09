package com.example.socket;

public interface Observable
{
    void notifyAllObservers() throws InterruptedException;
}
