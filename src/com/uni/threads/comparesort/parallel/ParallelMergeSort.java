/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tareagrupal;

import java.util.Calendar;

/**
 *
 * @author Cesar
 */
public class ParallelMergeSort {
    
    private double datos;
    private long tiempo;
    private int numOfThreads;
    
    public void setNumOfThreads(int numOfThreads)
    {
        this.numOfThreads = numOfThreads;
    }
    
    public void setDatos(double datos)
    {
        this.datos = datos;
    }
    
    public long getTiempo(){
        return tiempo;
    }
    
    public void merge(int A[], int izq, int m, int der)
    {
        int i, j, k;
        int[] B = new int[A.length];
        
        for(i=izq; i<=der; i++){
            B[i] = A[i];
        }
        
        i=izq;
        j=m+1;
        k=izq;
        
        while(i<=m && j<=der){
            if(B[i] <= B[j]){
                A[k++] = B[i++];
            }else{
                A[k++] = B[j++];
            }
        }
        
        while(i<=m){
            A[k++] = B[i++];
        }
    }
    
    public void mergesort(int A[], int izq, int der)
    {
        if(izq < der){
            int m=(izq+der)/2;
            mergesort(A, izq, m);
            mergesort(A, m+1, der);
            merge(A, izq, m, der);
        }
    }
    
    private Thread mergeSortParallel(int[] A, int low, int high, int numOfThreads)
    {
        return new Thread()
        {
            @Override
            public void run()
            {
                ParallelMergeSort(A, low, high, numOfThreads/2);
            }
        };
    }
    
    public void ParallelMergeSort(int[] A, int izq, int der, int numOfThreads)
    {
        if(numOfThreads <= 1)
        {
            mergesort(A, izq, der);
            return;
        }
        
        int m = (izq + der)/2;
        
        Thread leftSorter = mergeSortParallel(A, izq, m, numOfThreads);
        Thread rightSorter = mergeSortParallel(A, m+1, der, numOfThreads);
        
        leftSorter.start();
        rightSorter.start();
        
        try{
            leftSorter.join();
            rightSorter.join();
        }catch(InterruptedException e){}
        
        merge(A, izq, m, der);
        
    }
    
    public void ordenar()
    {
        int ints[] = new int[(int)datos];
        
        for (int i=0; i<ints.length; i++)
        {
            ints[i]= (int)(Math.random()*datos);
        }
        
        long start;
        long end;
        
        int der = ints.length/2;
        int izq = der-1;
        
        start = Calendar.getInstance().getTimeInMillis();
        ParallelMergeSort(ints, izq, der, numOfThreads);
        end = Calendar.getInstance().getTimeInMillis();
        
        this.tiempo = (end - start)/100;
    }
    
}
