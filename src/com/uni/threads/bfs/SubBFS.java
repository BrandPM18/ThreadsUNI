package com.uni.threads.bfs;

import java.util.PriorityQueue;
import java.util.Queue;

public class SubBFS extends Thread {

    private final int threadNumber;
    public ParallelBFS bfs;

    public SubBFS(int threadNumber,ParallelBFS parallel) {
        this.threadNumber = threadNumber;
        this.bfs = parallel;
    }

    @Override
    public long getId() {
        return threadNumber;
    }

    @Override
    public void run() {
        while (!bfs.isDone()) {
            bfs.bfs();
            //yield();
            subBfs(bfs.getLocalQueues().get(threadNumber));
        }
    }

    public void subBfs(Queue<Integer> localQueue) {
        Queue<Integer> tmpQueue = new PriorityQueue();

        while (!localQueue.isEmpty()) {
            int vertex = localQueue.poll();
            if (!bfs.isVisited(vertex)) {
                bfs.setVisited(vertex, true);
                bfs.incrementCounter();
                boolean toLocal = true;
                for (int i = 0; i < bfs.getCountVertex(); i++) {
                    if (vertex == i) {
                        continue;
                    }
                    if (bfs.isNeighbour(vertex, i) && !toLocal && !bfs.isVisited(i)) {
                        tmpQueue.add(i);
                    }
                    if (bfs.isNeighbour(vertex, i) && toLocal && !bfs.isVisited(i)) {
                        localQueue.add(i);
                        toLocal = false;
                    }
                }
            }
        }
        bfs.addQueue(tmpQueue);
    }
}