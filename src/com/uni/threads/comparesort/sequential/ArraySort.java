package com.uni.threads.comparesort.sequential;

import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author Cesar
 */
public class ArraySort {
    
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
            ints[i]= (int)(Math.random()*datos);
        }
        
        double start;
        double end;

	start = Calendar.getInstance().getTimeInMillis();
        Arrays.sort(ints);
        end = Calendar.getInstance().getTimeInMillis();
        
        this.tiempo = end - start;
    }
    
}
