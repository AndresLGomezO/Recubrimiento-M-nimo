package DatosXpath;

import java.awt.Point;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import Ventanas.VentanaPrincipal;
import Grafo.Arco;
import Grafo.Nodo;
import Grafo.PanelGrafo;

public class NodosXPath extends PanelGrafo {

	public static List<Nodo> nodos;

	static int contxd;
	static int contyd;
	static int contxi;
	static int contyi;

	public static boolean LeerAtributos(File xMLPath) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(xMLPath);
			XPath xp = XPathFactory.newInstance().newXPath();
			NodeList at = (NodeList) xp.compile("//Atributo").evaluate(d, XPathConstants.NODESET);

			if (at.getLength() > 0) {

				for (int i = 0; i <= at.getLength(); i++) {
					VentanaPrincipal.PonerTextoAtributos(xp.compile("./Nombre").evaluate(at.item(i)));
					// raul
				}

			}
			// else if (at.getLength() == 0) {
			//
			// }

		} catch (Exception e) {
			System.out.println("LeerAtributos " + e.getMessage());
			return false;
		}

		return true;
	}

	public static void AgregarNodo(Nodo n) {
		Grafo.PanelGrafo.nodos.add(n);
	}

	public static void AgregarArco(Arco a) {
		Grafo.PanelGrafo.arcos.add(a);
		VentanaPrincipal.TamanoPanel();
	}

	public static Nodo VerificarNodoD(String Nombre) {
		Nodo m = new Nodo();
		if (Grafo.PanelGrafo.nodosD.contains(Nombre)) {

			for (int i = 0; i <= Grafo.PanelGrafo.nodos.size(); i++) {
				// System.out.println("Dentro del Ciclo for " + i + " Nombre buscado = " +
				// Nombre + " Nombre Evaluado = " + Grafo.PanelGrafo.nodos.get(i).getNombre());
				if (Grafo.PanelGrafo.nodos.get(i).getNombre().toString().equalsIgnoreCase(Nombre)) {
					// System.out.println("encontre el nodo " + Grafo.PanelGrafo.nodos.get(i));
					m = Grafo.PanelGrafo.nodos.get(i);

					break;
				}
			}

			return m;
		} else {
			// System.out.println("No estaba "+ Nombre);
			Grafo.PanelGrafo.nodosD.add(Nombre);
			m.setNombre(Nombre);
			m.setPunto(new Point(contxd * 100 + 500, contyd * 80 + 20));
			contyd++;

			AgregarNodo(m);
			return m;

		}

	}

	public static Nodo VerificarNodoI(String Nombre) {
		Nodo m = new Nodo();
		if (Grafo.PanelGrafo.nodosI.contains(Nombre)) {
			// System.out.println("NodosXpath.VerificarNodoI Si está " + Nombre + " tam "
			// +Grafo.PanelGrafo.nodos.size() );

			for (int i = 0; i <= Grafo.PanelGrafo.nodos.size(); i++) {
				if (Grafo.PanelGrafo.nodos.get(i).getNombre().toString().equalsIgnoreCase(Nombre)) {
					m = Grafo.PanelGrafo.nodos.get(i);
					break;
				}
			}

			for (String nombre : nodosI)
				System.out.println("el nombre izq es " + nombre);

			return m;
		} else {
			// System.out.println("No estaba "+ Nombre); // llenar
			Grafo.PanelGrafo.nodosI.add(Nombre);
			m.setNombre(Nombre);
			m.setPunto(new Point(contxi * 50 + 220, contyi * 80 + 20));
			contyi++;
			AgregarNodo(m);
			return m;

		}

	}

	public static void VerificarAtributo(int id, String Nombre) {

		if (Grafo.PanelGrafo.atributos.contains(Nombre)) {
			;

		} else {
			// System.out.println("No estaba "+ Nombre);
			Grafo.PanelGrafo.atributos.add(Nombre);
			String ide = id + "";
			VentanaPrincipal.PonerTextoAtributos(Nombre);
		}

	}

	public static void AgregarArco(Nodo Iz, Nodo Dr) {

		Arco ar = new Arco();
		ar.setOrigen(Iz);
		ar.setDestino(Dr);
		ar.setPeso(1);
		AgregarArco(ar);

	}

	public static boolean LeerDependencias(File xMLPath) {
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(xMLPath);
			XPath xp = XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xp.compile("//DF").evaluate(d, XPathConstants.NODESET);

			if (nl.getLength() > 0) {

				for (int i = 0; i <= nl.getLength(); i++) {

					Nodo ii = new Nodo();

					Nodo dd = new Nodo();

					VentanaPrincipal.PonerTextoDF(xp.compile("./Izq").evaluate(nl.item(i)),
							xp.compile("./Der").evaluate(nl.item(i)));
					String nombreIz = xp.compile("./Izq").evaluate(nl.item(i));
					String nombreDer = xp.compile("./Der").evaluate(nl.item(i));

					System.out.println(" implicantes " + nombreIz + "-->" + nombreDer);

					ii = VerificarNodoI(nombreIz);

					dd = VerificarNodoD(nombreDer);

					AgregarArco(ii, dd);

				}
			} else if (nl.getLength() == 0) {
				return false;
			}

		} catch (Exception e) {
			System.out.println("LeerDependencias" + e.getMessage());
			return false;
		}

		return true;
	}

}
