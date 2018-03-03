/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.soap.Node;

import DatosXpath.NodosXPath;
import Ventanas.VentanaPrincipal;



public class PanelGrafo  extends javax.swing.JPanel {
    
	boolean nodo2 =false;
    Nodo origen;
    Nodo destino;
    public static List <Nodo> nodos;
    public static List <Arco> arcos;
    public static ArrayList <String> nodosD = new ArrayList<String>();
    public static ArrayList <String> nodosI = new ArrayList<String>();
    public static ArrayList <String> atributos = new ArrayList<String>();

    public static boolean getNodos(Nodo n) {
    	
    	if (nodos.size()>0) {
    		System.out.println("Si entro aqui");
        for (int i = 0 ; i <= nodos.size() ;i++) {
        	System.out.println(nodos.size());
        	System.out.println(n.getNombre());
        	String nombre = nodos.get(i).getNombre();
        	System.out.println(nombre);
        	
        	if(nodos.get(i).getNombre() == n.getNombre()) {
        		System.out.println("Si entro aquitrue");
        		return true;
            }
         }
    	}
    return false;
		
    }

    public List<Arco> getArcos() {
        return arcos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    public void setArcos(List<Arco> arcos) {
        this.arcos = arcos;
    }

   
    public PanelGrafo() {
        origen=null;
        destino=null;
        this.nodos = new ArrayList<>();
        this.arcos = new ArrayList<>();
        initComponents();

        
        addMouseListener(new MouseAdapter() { 
            
        	public void mousePressed(MouseEvent e) { 
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //AQUI CREAMO UN NODO MEDIANTE UN NOMBRE 
                    //y un punto(X,y)
                    
                    System.out.println(e); 
                    Nodo n = new Nodo();
                    
                    try {
                        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del nodo");
                        
                        if (!nombre.equals(null) && !nombre.replace(" ", "").equals("")) {
                            System.out.println(nombre); 
                            n.setNombre(nombre);
                            n.setPunto(e.getPoint());
                            nodos.add(n);//agregamos ala lista
                            NodosXPath.VerificarAtributo(atributos.size()+1,nombre);
                            
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Esciba un nombre valido para el nodo");
                    }
                    
                   
                }

                repaint();
                
                
        } 
            
          public void mouseClicked(MouseEvent e) { 
            System.out.println(e); 
            if (e.getButton() == MouseEvent.BUTTON3) {
                //AQUI CREAMO UN ARCO MEDIANTE UN NODO ORIGEN 
                //UNO DESTINO Y SU PESO
                
                try {
                    if (!nodo2) {
                        try {
                            origen=getNodoPorXY(e.getX(),e.getY());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Seleccione un nodo correcto");
                        }
                        
                        if (origen!=null) {
                        nodo2=true;
                        JOptionPane.showMessageDialog(null, "Seleccione el nodo destino");
                        }
                    }
                    else if (nodo2) {
                        try {
                            destino=getNodoPorXY(e.getX(),e.getY());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Seleccione un nodo correcto");
                        }
                        
                        if (destino!=null) {
                            Arco a= new Arco();
                            a.setOrigen(origen);//guardamos origien 
                        
                            a.setDestino(destino);//guardamos destino
                            nodo2=false;
                            String peso="";
                            int p = 1;
                            /*
                            while (p==0) {                                
                                try {
                                     peso= JOptionPane.showInputDialog("Ingrese el peso >0");
                                     p=Integer.parseInt(peso);
                                } catch (Exception ex) {
                                }
                               }*/
                            
                            a.setPeso(p);// guardamos el peso
                            arcos.add(a);//Guardamos el arco en la lista de arcos
                            VentanaPrincipal.PonerTextoDF(origen.getNombre(),destino.getNombre());
                            origen=null;
                            destino=null;
                        }
                         
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error brutal");
                }
                
                repaint();
               
                }
            System.out.println(e.getButton());
          } 
        }); 
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //SE ENCARGA DE LA GRAFICA DE LOS NODOS ARCOS Y TEXTOS EN EL PANEL
    public void paint (Graphics g)
    {
        super.paint(g);

        //PARA DIBUJAR NODOS
        for (int i = 0; i < nodos.size(); i++) {
            
            g.setColor(Color.BLUE);
            g.fillOval(nodos.get(i).getPunto().x, nodos.get(i).getPunto().y, 20, 20);
            g.setColor(Color.white);
            g.setFont( new Font( "Calibri", Font.PLAIN, 11 ) );
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
          
            
            /*                
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(arcos.get(i).getPeso()), (p1.x+p2.x)/2,(p1.y+p2.y)/2);
            */

            g.setColor(Color.black);
            
            double ang=0.0, angSep=0.0;
            double tx,ty;
            int dist=0;
            Point punto1=null,punto2=null;

            //defino dos puntos extremos
            punto1=new Point(p2.x+20,p2.y+10);
            punto2=new Point(p1.x,p1.y+10);

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
            g.setColor (Color.lightGray);
            // grosor de la linea
            g2D.setStroke (new BasicStroke(1.5f));
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
    public Point getNodoPorNombre(Nodo n) {
        for (int i = 0; i < nodos.size(); i++) {
            
        if (n.getNombre() == nodos.get(i).getNombre()) {
            return nodos.get(i).getPunto(); }
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
