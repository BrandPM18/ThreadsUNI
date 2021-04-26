package com.uni.threads.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private final int size;
    private final int[][] vertices;
    private final LinkedList<Integer>[] adjacencyLists;
    private final Stack<Integer> globalStack;
    private final boolean[] visitedNodes;
    private final List<Stack<Integer>> localStacks;
    private boolean isDone;
    private int counter;

    @SuppressWarnings("unchecked")
    Graph(int size, int[][] adjacencyMatrix) {
        // initialize the adjacencyLists and visitedNodes
        this.size = size;
        adjacencyLists = new LinkedList[size];
        visitedNodes = new boolean[size];

        // fill adjacencyLists with LinkedLists using the size
        for (int i = 0; i < size; i++)
            adjacencyLists[i] = new LinkedList();

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[i][j] == 1)
                    addEdge(i, j);
            }

        // unused attributes
        vertices = null;
        globalStack = null;
        localStacks = null;
    }
    Graph(int size, int[][] adjacencyMatrix, int numberOfThreads) {
        this.size = size;
        localStacks = new ArrayList<>(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++)
            localStacks.add(new Stack<>());
        vertices = new int[size][size];
        visitedNodes = new boolean[size];
        isDone = false;
        globalStack = new Stack<>();
        globalStack.push(0);
        counter = 0;

        for (int i = 0; i < size; i++)
            System.arraycopy(adjacencyMatrix[i], 0, vertices[i], 0, size);

        // unused attributes
        adjacencyLists = null;

    }

    public List<Stack<Integer>> getLocalStacks() {
        return localStacks;
    }

    private void addEdge(int src, int dest) {
        adjacencyLists[src].add(dest);
    }

    private void getSequentialDFS(int node) {
        visitedNodes[node] = true;

        // check the adjacencyLists
        for (int adjacentNode : adjacencyLists[node]) {
            if (!visitedNodes[adjacentNode])
                getSequentialDFS(adjacentNode);
        }
    }

    void checkSuccess() {
        // check if all nodes were visited'
        boolean success = true;
        for (boolean wasVisited :
                visitedNodes) {
            if (!wasVisited) {
                success = false;
                break;
            }
        }

        System.out.println(success ? "DFS algorithm succeeded" : "DFS algorithm failed");
    }

    void sequentialDFS() {
        int initialNode = adjacencyLists[0].get(0);
        getSequentialDFS(initialNode);
    }

    public int getSize() {
        return size;
    }

    public synchronized boolean getVisited(int index) {
        return visitedNodes[index];
    }

    public synchronized void setVisited(int index, boolean value) {
        visitedNodes[index] = value;
    }

    public synchronized void pushStack(Stack<Integer> tmp) {
        while (!tmp.isEmpty()) {
            globalStack.push(tmp.pop());
        }
    }

    public boolean isNeighbour(int node, int neighbour) {
        return vertices[node][neighbour] == 1;
    }

    public synchronized void incrementCounter() {
        counter++;
    }

    public boolean isDone() {
        return isDone;
    }

    public synchronized void dfs() {
        while (!isDone && globalStack.empty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int index = (int) (Thread.currentThread().getId());
        if (!globalStack.isEmpty()) {
            boolean popped;
            int node = globalStack.pop();
            popped = true;
            while (visitedNodes[node]) {
                if (globalStack.empty()) {
                    isDone = true;
                    popped = false;
                    break;
                } else {
                    node = globalStack.pop();
                    popped = true;
                }
            }
            if (popped) {
                visitedNodes[node] = true;
                counter++;
                boolean flag = false;
                for (int i = 0; i < size; i++) {
                    if (node == i) continue;
                    if (isNeighbour(node, i) && !visitedNodes[i] && !flag) {
                        localStacks.get(index).push(i);
                        flag = true;
                    }
                    if (isNeighbour(node, i) && !visitedNodes[i] && flag) {
                        globalStack.push(i);
                    }
                }
            }
        }
        if (globalStack.empty())
            isDone = true;
        if (isDone && counter < size) {
            isDone = false;
            for (int i = 0; i < size; i++) {
                if (!visitedNodes[i])
                    globalStack.push(i);
            }
        }
        notifyAll();
    }
}
