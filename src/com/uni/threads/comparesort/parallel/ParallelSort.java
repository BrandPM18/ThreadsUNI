package com.uni.threads.comparesort.parallel;

import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author Cesar
 */
public class ParallelSort {
    
    private double datos;
    private double tiempo;
    
    public void setDatos(double datos)
    {
        this.datos = datos;
    }
    
    public double getTiempo(){
        return tiempo;
    }
    
    public void ordenar()
    {
        int ints[] = new int[(int)datos];
        
        for (int i=0; i<ints.length; i++)
        {
            ints[i]= ints.length - i;
        }
        
        double start;
        double end;
        
        start = Calendar.getInstance().getTimeInMillis();
        Arrays.parallelSort(ints);
        end = Calendar.getInstance().getTimeInMillis();
        
        this.tiempo = end - start;
    }
}
