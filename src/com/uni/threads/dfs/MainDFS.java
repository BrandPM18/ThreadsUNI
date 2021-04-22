package com.uni.threads.dfs;

/*
    comparison between ParallelDFS y SequentialDFS
*/

public class MainDFS {
    public static void main(String[] args) {
        int size = 4;
        int startNode = 1;
        Graph g = new Graph(size);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.printf(
                "Following is Depth First Traversal "
                        + "(starting from vertex %s)\n", startNode);

        g.DFS(startNode);
    }
}