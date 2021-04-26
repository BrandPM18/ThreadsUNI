package com.uni.threads.comparesort.parallel;

import java.util.Arrays;
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
    
    public void merge(int left[], int right[], int a[])
    {
        int nL=left.length;
	int nR=right.length;
	int i,j,k;
        
	i=j=k=0;
        
	while(i<nL&&j<nR)
	{
            if(left[i]<=right[j])
            {
                a[k]=left[i];
                i++;
                k++;
            }
            else
            {
                a[k]=right[j];
                j++;
                k++;
            }
	}
	while(i<nL)
	{
            a[k]=left[i];
            i++;
            k++;
	}
	while(j<nR)
	{
            a[k]=right[j];
            j++;
            k++;
	}
    }
    
    public void mergesort(int a[], int n)
    {
        if(n<=1){
            return;
        }
        
	int mid=n/2;
	int left[]=new int[mid];
	int right[]=new int[n-mid];
        
        for(int i=0; i<mid; i++)
        {
            left[i]=a[i];
        }
        
	for(int i=mid;i<n;i++)
        {
            right[i-mid]=a[i];
        }
        
	mergesort(left,mid);
	mergesort(right,n-mid);
	merge(left,right,a);
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
            mergesort(A, A.length);
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
        
        int n;
        n = A.length;
        
	int mid=n/2;
	int left[]=new int[mid];
	int right[]=new int[n-mid];
        
        for(int i=0; i<mid; i++)
        {
            left[i]=A[i];
        }
        
	for(int i=mid;i<n;i++)
        {
            right[i-mid]=A[i];
        }
        
	mergesort(left,mid);
	mergesort(right,n-mid);
	merge(left,right,A);
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
        
        this.tiempo = end - start;
    }
    
}

