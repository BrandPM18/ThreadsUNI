package com.uni.threads.bfs;

/*
 * Carpeta donde se deben comparar ambos codigos importanto ParallelBFS y SecuencialBFS
 */

import java.util.Calendar;

public class MainBFS {
    public static void main(String[] args) {

        int n = 10000; // n = 2,10,100,1000,10000,100000
        SequentialBFS FirstBFS = new SequentialBFS(n);
        FirstBFS.generateEdge();

        int searchI = (int) (Math.random() * n);

        // double start = System.currentTimeMillis();
        double start = Calendar.getInstance().getTimeInMillis();
        System.out.println("Number : " + searchI);

        FirstBFS.searchBFS(searchI);
        double end = Calendar.getInstance().getTimeInMillis();

        double time = (end - start)/100;
        System.out.println("Generate :" + time+" s");
    }
}