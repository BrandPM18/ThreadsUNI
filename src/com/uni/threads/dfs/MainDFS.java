package com.uni.threads.dfs;

/*
    comparison between ParallelDFS y SequentialDFS
*/

import java.util.Calendar;
import java.util.Random;

public class MainDFS {
    public static void main(String[] args) {
        int size = 2000;
        int numberOfThreads = 10;
        int[][] adjacencyMatrix = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                Random boolNumber = new Random();
                boolean connected = boolNumber.nextBoolean();
                if (i == j)
                    adjacencyMatrix[i][j] = 1;
                else
                    adjacencyMatrix[i][j] = connected ? 1 : 0;
            }

        runSequentialDFS(size, adjacencyMatrix);
        System.out.println();
        runParallelDFS(size, adjacencyMatrix, numberOfThreads);
    }

    public static void runSequentialDFS(int size, int[][] adjacencyMatrix) {
        double start, finish;
        Graph graph = new Graph(size, adjacencyMatrix);

        start = Calendar.getInstance().getTimeInMillis();
        graph.sequentialDFS();
        finish = Calendar.getInstance().getTimeInMillis();

        System.out.println("Sequential DFS:");
        graph.checkSuccess();
        System.out.println((finish - start) / 1000);

    }

    public static void runParallelDFS(int size, int[][] adjacencyMatrix, int numberOfThreads) {
        double start, finish;
        Graph graph = new Graph(size, adjacencyMatrix, numberOfThreads);

        start = Calendar.getInstance().getTimeInMillis();
        Thread[] processors = new Processor[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            processors[i] = new Processor(graph, i);
            processors[i].start();
        }
        finish = Calendar.getInstance().getTimeInMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                processors[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Parallel DFS:");
        graph.checkSuccess();
        System.out.println((finish - start) / 1000);
    }
}