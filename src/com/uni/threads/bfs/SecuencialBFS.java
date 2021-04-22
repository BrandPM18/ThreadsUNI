package com.uni.threads.bfs;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SecuencialBFS {
    private  int vertx; // Numero de vertices
    private LinkedList<Integer>[]  graph; // Matriz de abyacencia
    private LinkedList<Integer> solution = new LinkedList<>(); // Almacen de solucion

    public SecuencialBFS(int size) {
        this.vertx = size;
        this.graph = new LinkedList[vertx];
        for(int i= 0;i<vertx;i++)
            graph[i] = new LinkedList<>();
    }
    public void addEdges(int[] conex){
        graph[conex[0]].add(conex[1]);
    }

    public boolean notExistEdges(int vxt1,int vxt2){
        return  (graph[vxt1].indexOf(vxt2)==-1 )|| (graph[vxt2].indexOf(vxt1)==-1 ) ;
    }
    public void generateEdge() {
        int rand1=0,rand2=0;
        int edgeMax = vertx*(vertx-1)/2;
        for (int i = 0; i < edgeMax; i++) {
            rand1 = (int) (Math.random() * vertx);
            rand2 = (int) (Math.random() * vertx);
            System.out.println(rand1 + "..." + rand2);
            if ((rand1 == rand2) || !(notExistEdges(rand1, rand2))) {
                i--;
                System.out.println("Pass");
                continue;
            }
            addEdges(new int[]{rand1, rand2});
        }
    }


    public void searchBFS(int num){
        boolean pass[] = new boolean[vertx];
        pass[num] = true;
        solution.addLast(num);
        while(!solution.isEmpty()){
            int temp = solution.poll();
            System.out.print(temp + "-");
            for(int i=0;i<graph[temp].size();i++){
               if(!pass[graph[temp].get(i)]){
                   solution.addLast(graph[temp].get(i));
                   pass[graph[temp].get(i)] = true;
               }
            }
        }
        System.out.println(" ");
    }


}
