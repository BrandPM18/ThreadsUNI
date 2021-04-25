/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uni.threads.comparesort;

import java.io.*;

/**
 *
 * @author Cesar
 */
public class CSV {
    
    private String archivo;
    
    public void setArchivo(String archivo){
        this.archivo = archivo;
    }
    
    public void limpiar() throws IOException{
        File file = new File(archivo);
        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            bw.close();
            
        }catch(IOException e) {};
    }
    
    public void crear(String variables) throws IOException{    
        File file = new File(archivo);
        
        try {
            
            FileWriter w = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            
            wr.write(variables);
            
            wr.close();
            bw.close();
            
        } catch (IOException e) {};
    }
}
