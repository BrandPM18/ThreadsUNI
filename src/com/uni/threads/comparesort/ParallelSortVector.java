package com.uni.threads.comparesort;

import com.uni.threads.comparesort.parallel.*;
import com.uni.threads.comparesort.sequential.*;

import java.io.IOException;

/**
 *
 * @author Cesar
 */
public class ParallelSortVector{

    public static void main(String[] args) throws IOException {
        ArraySort vector_1 = new ArraySort();
        ParallelSort vector_2 = new ParallelSort();
        MergeSort vector_3 = new MergeSort();
        ParallelMergeSort vector_4 = new ParallelMergeSort();
        
        CSV csv = new CSV();
        
        csv.setArchivo("ResultadoArraySort.csv");
        csv.limpiar();
        csv.crear("Valores,Tiempo\n");
        
        for(int i=0; i<5; i++)
        {
            double dato = Math.pow(10, i+1);
            //System.out.println("Cantidad de valores: "+dato);
            vector_1.setDatos(dato);
            vector_1.ordenar();
            
            csv.crear(dato+","+vector_1.getTiempo()+"\n");
        }
        
        csv.setArchivo("ResultadoParallelSort.csv");
        csv.limpiar();
        csv.crear("Valores,Tiempo\n");
        
        for(int i=0; i<5; i++)
        {
            double dato = Math.pow(10, i+1);
            //System.out.println("Cantidad de valores: "+dato);
            vector_2.setDatos(dato);
            vector_2.ordenar();
            
            csv.crear(dato+","+vector_2.getTiempo()+"\n");
        }
        
        csv.setArchivo("ResultadoMergeSort.csv");
        csv.limpiar();
        csv.crear("Valores,Tiempo\n");
        
        for(int i=0; i<5; i++)
        {
            double dato = Math.pow(10, i+1);
            //System.out.println("Cantidad de valores: "+dato);
            vector_3.setDatos(dato);
            vector_3.ordenar();
            
            csv.crear(dato+","+vector_3.getTiempo()+"\n");
        }
        
        csv.setArchivo("ResultadoParallelMergeSort.csv");
        csv.limpiar();
        csv.crear("Valores,2 hilos,4 hilos,8 hilos,16 hilos,32 hilos\n");
        
        for(int i=0; i<5; i++)
        {
            double dato = Math.pow(10, i+1);
            System.out.println("Cantidad de valores: "+dato);
            vector_4.setDatos(dato);
            
            csv.crear(dato+"");
            
            for(int j=0; j<5; j++)
            {
                vector_4.setNumOfThreads((int) Math.pow(2, i+1));
                vector_4.ordenar();
                csv.crear(","+vector_4.getTiempo());
            }
            
            csv.crear("\n");
        }
    }

}
