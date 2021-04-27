package com.uni.threads.bfs;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SequentialBFS {
    private final int[][] matrix; // Matriz de abyacencia
    private final boolean[] visited; // Generado de puntos visitados
    private final Queue<Integer> queue; //
    private final int startVertex;
    public SequentialBFS(int[][] matrix){
        this.matrix = matrix;
        this.visited = new boolean[matrix.length];
        this.queue = new PriorityQueue();
        this.startVertex = 0;
        Arrays.fill(this.visited, false);
    }
    public void make() {
        queue.add(startVertex);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(!visited[node]){
                //System.out.println(node);
                visited[node] = true;
                for(int i = 0; i < matrix.length; i++){
                    if(node==i)continue;
                    if(matrix[node][i] == 1 && !visited[i]){
                        queue.add(i);
                    }
                }
            }
        }
    }
}







/*
public class SequentialBFS {
    private final int vertx; // Numero de vertices
    private final LinkedList<Integer>[]  graph; // Matriz de abyacencia
    private final LinkedList<Integer> solution = new LinkedList<>(); // Almacen de solucion

    public SequentialBFS(int size) {
        this.vertx = size;
        this.graph = new LinkedList[vertx];
        for(int i= 0;i<vertx;i++)
            graph[i] = new LinkedList<>();
    }
    public void addEdges(int[] conex){
        graph[conex[0]].add(conex[1]);
    }

    public boolean notExistEdges(int vxt1,int vxt2){
        return  (!graph[vxt1].contains(vxt2))|| (!graph[vxt2].contains(vxt1)) ;
    }
    public void generateEdge() {
        int rand1=0,rand2=0;
        int edgeMax = vertx*(vertx-1)/2;
        for (int i = 0; i < edgeMax; i++) {
            rand1 = (int) (Math.random() * vertx);
            rand2 = (int) (Math.random() * vertx);
            //System.out.println(rand1 + "..." + rand2);
            if ((rand1 == rand2) || !(notExistEdges(rand1, rand2))) {
                i--;
                //System.out.println("Pass");
                continue;
            }
            addEdges(new int[]{rand1, rand2});
        }
    }


    public void searchBFS(int num){
        boolean[] pass = new boolean[vertx];
        pass[num] = true;
        solution.addLast(num);
        int temp = 0;
        while(!solution.isEmpty()){
            temp = solution.poll();
            for(int i=0;i<graph[temp].size();i++){
               if(!pass[graph[temp].get(i)]){
                   solution.addLast(graph[temp].get(i));
                   pass[graph[temp].get(i)] = true;
               }
            }
            //System.out.print(temp + "-");
        }
        System.out.println(" ");
    }


}
*/