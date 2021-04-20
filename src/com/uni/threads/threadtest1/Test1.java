package com.uni.threads.threadtest1;

public class Test1 extends Thread{
    public Test1(String test) {
        super(test);
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++)
           System.out.println(i+"--"+getName());
        System.out.println("End thread "+ getName());
    }

}
