package com.uni.threads.comparesort.sequential;

import java.util.Calendar;

/**
 *
 * @author Cesar
 */
public class MergeSort {
    
    private double datos;
    private long tiempo;
    
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
    
    public void ordenar()
    {   
        int ints[] = new int[(int)datos];
        
        for (int i=0; i<ints.length; i++)
        {
            ints[i]= ints.length - i;
        }
        
        long start;
        long end;
        
        int der = ints.length/2;
        int izq = der-1;

	start = Calendar.getInstance().getTimeInMillis();
        mergesort(ints, (int) datos);
        end = Calendar.getInstance().getTimeInMillis();
        
        this.tiempo = end - start;
    }
}
