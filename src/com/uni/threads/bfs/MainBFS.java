package com.uni.threads.bfs;

/*
 * Carpeta donde se deben comparar ambos codigos importanto ParallelBFS y SecuencialBFS
 */

import java.lang.reflect.GenericArrayType;
import java.util.Calendar;

public class MainBFS {
    public static int[][] generateMatrix(Integer size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != j && Math.random() > 0.5) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                }
            }
        }
        return matrix;
    }
    public static void main(String[] args) {
        /*
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

         */
        int countVertex=10000; // n = 2,10,100,1000,10000,100000
        int countThreads=100; // y = 1,2,10,100,1000,10000
        System.out.println("Vertices = " + countVertex + ", Hilos = " + countThreads);
        double time,start, finish;
        final int[][] matrix = generateMatrix(countVertex);

        start = Calendar.getInstance().getTimeInMillis();
        SequentialBFS sequenceBFS = new SequentialBFS(matrix);
        sequenceBFS.make();
        finish = Calendar.getInstance().getTimeInMillis();
        time = (finish-start)/1000;
        System.out.println("Sequence Time " + time + " s");


        start = Calendar.getInstance().getTimeInMillis();
        ParallelBFS parallelBFS = new ParallelBFS(matrix, countThreads);
        Thread[] subBFS = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            subBFS[i] = new SubBFS(i, parallelBFS);
            subBFS[i].start();
        }
        for (int i = 0; i < countThreads; i++) {
            try {
                subBFS[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finish = Calendar.getInstance().getTimeInMillis();
        time = (finish-start)/1000;
        System.out.println("Parallel Time " + time +" s");
    }
}