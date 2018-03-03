package Grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class ActualizarGrafo extends PanelGrafo {

	
	
public void paint (Graphics g)
{
    super.paint(g);

    //PARA DIBUJAR NODOS
    for (int i = 0; i < nodos.size(); i++) {
        
        g.setColor(Color.BLUE);
        g.fillOval(nodos.get(i).getPunto().x, nodos.get(i).getPunto().y, 20, 20);
        g.setColor(Color.white);
        g.setFont( new Font( "Calibri", Font.BOLD, 12 ) );
        g.drawString(nodos.get(i).getNombre(), nodos.get(i).getPunto().x+1, nodos.get(i).getPunto().y+30);
        g.setColor(Color.white);
        g.drawOval(nodos.get(i).getPunto().x, nodos.get(i).getPunto().y, 20, 20);
                  
    }
    //PARA DIBUJAR ARCOS
    Point p1 = new Point();
    Point p2 = new Point();
    //PARA DIBUJAR ARCOS
    for (int i = 0; i < arcos.size(); i++) {
        System.out.println(""+arcos.get(i).getDestino().getNombre());
        p1.x=arcos.get(i).getDestino().getPunto().x;
        p1.y=arcos.get(i).getDestino().getPunto().y;
        p2.x=arcos.get(i).getOrigen().getPunto().x;
        p2.y=arcos.get(i).getOrigen().getPunto().y;
       
        
                        
        g.setColor(Color.DARK_GRAY);
        g.drawString(String.valueOf(arcos.get(i).getPeso()), (p1.x+p2.x)/2,(p1.y+p2.y)/2);
        
        g.setColor(Color.black);
        
        double ang=0.0, angSep=0.0;
        double tx,ty;
        int dist=0;
        Point punto1=null,punto2=null;

        //defino dos puntos extremos
        punto1=new Point(p2.x+25,p2.y+25);
        punto2=new Point(p1.x+15,p1.y+15);

        //tamaño de la punta de la flecha
        dist=10;

        /* (la coordenadas de la ventana es al revez)
            calculo de la variacion de "x" y "y" para hallar el angulo
         **/

        ty=-(punto1.y-punto2.y)*1.0;
        tx=(punto1.x-punto2.x)*1.0;
        //angulo
        ang=Math.atan (ty/tx);

        if(tx<0)
        {// si tx es negativo aumentar 180 grados
           ang+=Math.PI;
        }

        //puntos de control para la punta
        //p1 y p2 son los puntos de salida
        Point punto=punto2;

        //angulo de separacion
        angSep=25.0;

        p1.x=(int)(punto.x+dist*Math.cos (ang-Math.toRadians (angSep)));
        p1.y=(int)(punto.y-dist*Math.sin (ang-Math.toRadians (angSep)));
        p2.x=(int)(punto.x+dist*Math.cos (ang+Math.toRadians (angSep)));
        p2.y=(int)(punto.y-dist*Math.sin (ang+Math.toRadians (angSep)));

        Graphics2D g2D=(Graphics2D)g;

        //dale color a la linea
        g.setColor (Color.LIGHT_GRAY);
        // grosor de la linea
        g2D.setStroke (new BasicStroke(1.2f));
        //dibuja la linea de extremo a extremo
        g.drawLine (punto1.x,punto1.y,punto.x,punto.y);
        //dibujar la punta
        g.drawLine (p1.x,p1.y,punto.x,punto.y);
        g.drawLine (p2.x,p2.y,punto.x,punto.y);  
    }
    
}
//PARA OBTENER EL NODO SELECCIONADO MEDIANTE LAS CORDENADAD XY DE MOUSE LISTENER
public Nodo getNodoPorXY(int x, int y) {
    for (int i = 0; i < nodos.size(); i++) {
        System.out.println(x+"x  nod"+nodos.get(i).getPunto().x);
       
        System.out.println(y+"y nod"+nodos.get(i).getPunto().y);
    if ((x>= nodos.get(i).getPunto().x  && x  <= nodos.get(i).getPunto().x + 50) && (y >= nodos.get(i).getPunto().y&&  y  <= nodos.get(i).getPunto().y + 50))
        return nodos.get(i); 
    }
    return null;
}

@SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 300, Short.MAX_VALUE)
    );
}// </editor-fold>//GEN-END:initComponents

private void AgregarNodo() {
	
}
// Variables declaration - do not modify//GEN-BEGIN:variables
// End of variables declaration//GEN-END:variables
}

