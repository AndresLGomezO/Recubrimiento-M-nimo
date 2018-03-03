/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.awt.Point;

public class Nodo {
    
    public Nodo() {
        punto = new Point();
    }
    private String nombre;//identificacion del nodo
    private Point punto;//localizacion del nodo en el plano

    //getters setters

 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Point getPunto() {
        return punto;
    }

   

    public void setPunto(Point punto) {
        this.punto = punto;
    }
    
    
}
