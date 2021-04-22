package com.uni.threads.bfs;

/*
 * Carpeta donde se deben comparar ambos codigos importanto ParallelBFS y SecuencialBFS
*/
import java.lang.*;

public class MainBFS {
    public static void main(String[] args) {
        int n = 1000;
        SecuencialBFS FirstBFS = new SecuencialBFS(n);
        FirstBFS.generateEdge();
        long start = System.currentTimeMillis();
        int searchI= (int) Math.random()*n;
        System.out.println("Start : "+searchI);
        FirstBFS.searchBFS(searchI);
        long end = System.currentTimeMillis();
        System.out.println("Generate :"+(end-start));
    }
}
