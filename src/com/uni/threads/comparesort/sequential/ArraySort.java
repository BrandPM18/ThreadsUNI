/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tareagrupal;

import java.util.Arrays;

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
        
        start = System.currentTimeMillis();
        Arrays.sort(ints);
        end = System.currentTimeMillis();
        
        this.tiempo = (end - start)/100;
    }
    
}
