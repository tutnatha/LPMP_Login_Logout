package com.journaldev.jsf.beans;

import java.util.concurrent.atomic.AtomicInteger;

//auto number class
public    class Sequence{
    private AtomicInteger counter = new AtomicInteger();

    public  int nextValue(){
        return counter.getAndIncrement();
    }

    public Sequence(){

    }
}