package com.chochae.afes;


import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

@Service
public class Singleton {

    private static AtomicReference<Singleton> INSTANCE = new AtomicReference<Singleton>();
    private int i = 0;

    public Singleton() {
        final Singleton previous = INSTANCE.getAndSet(this);
        if(previous != null)
            throw new IllegalStateException("Second singleton " + this + " created after " + previous);
    }

    public static Singleton getInstance() {
        return INSTANCE.get();
    }

    public void test() {
    	System.out.println("####################" + i++);
    }
}