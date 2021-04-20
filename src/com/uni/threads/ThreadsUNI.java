package com.uni.threads;

import com.uni.threads.threadtest1.Test1;

public class ThreadsUNI {
    public static void main(String[] args) {
        System.out.println("Testing");
        new Test1("Hola").start();
        new Test1("chau").start();
    }
}
