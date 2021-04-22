package com.uni.threads.dfs;

import java.util.*;

class Graph {
    /*
        declare the adjacency lists and the
        statuses of the nodes (visited or not)
    */
    private final LinkedList<Integer>[] adjacencyLists;
    private final boolean[] visitedNodes;

    @SuppressWarnings("unchecked")
    Graph(int size) {
        // initialize the adjacencyLists and visitedNodes
        adjacencyLists = new LinkedList[size];
        visitedNodes = new boolean[size];

        // fill adjacencyLists with LinkedLists using the size
        for (int i = 0; i < size; i++) {
            adjacencyLists[i] = new LinkedList();
        }
    }

    // method that adds edges to graph
    void addEdge(int src, int dest) {
        adjacencyLists[src].add(dest);
    }

    // method that performs the sequential DFS algorithm
    void DFS(int node) {
        visitedNodes[node] = true;
        System.out.print(node + " ");

        // check the adjacencyLists
        for (int adjacentNode : adjacencyLists[node]) {
            if (!visitedNodes[adjacentNode])
                DFS(adjacentNode);
        }
    }
}
