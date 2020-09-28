/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_8;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Carrera {
    Atleta[] atletas;
    
    public Carrera(int nroAtletas, int nroEquipos) throws Exception{
        int atletasPorEquipo, contAtletas;
        if(nroAtletas % nroEquipos != 0) throw new Exception ("Numero invalido de atletas o equipos.");
        atletasPorEquipo = nroAtletas / nroEquipos;
        contAtletas = 0;
        
        Semaphore testigoAux = new Semaphore(1);
        atletas = new Atleta[nroAtletas];        
        for(int i = 0; i < nroAtletas; i++){
            if(contAtletas == atletasPorEquipo){
                contAtletas = 0;
                testigoAux = new Semaphore(1);
            }
            atletas[i] = new Atleta(testigoAux);
            contAtletas++;
        }
    }
    
    public Atleta[] getAtletas(){
        return atletas;
    }
}
