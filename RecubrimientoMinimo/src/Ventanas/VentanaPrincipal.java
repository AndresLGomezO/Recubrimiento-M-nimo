package Ventanas;

import DatosXpath.NodosXPath;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import Grafo.*;
import operaciones.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton CargarArchivoBtn = new JButton("Cargar XML");
	private static String[] ColumnasDF = { "ID", "Implicante", "Signo", "Implicado" };
	private static String[][] dataDF = {};
	private static String[] ColumnasAt = { "ID", "Nombre" };
	private static String[][] dataAt = {};
	static DefaultTableModel modeloDF = new DefaultTableModel(dataDF, ColumnasDF);
	static DefaultTableModel modeloAt = new DefaultTableModel(dataAt, ColumnasAt);
	static DefaultTableModel modeloProyeccion = new DefaultTableModel(dataAt, ColumnasAt);
	private static JTable DFTable = new JTable(modeloDF) {
		/**
		 * 
		 */

		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	};
	/*
	 * private static JTable AtTable = new JTable(modeloAt){ public boolean
	 * isCellEditable(int row, int column){ return false; }
	 * 
	 * };
	 */

	private static JTable AtributosTable = new JTable(modeloAt) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private static JTable tableProyeccion = new JTable(modeloProyeccion) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	static int yi = 0;
	private final JLabel UDFJC = new JLabel(
			"    Universidad Distrital Francisco Jos\u00E9 de Caldas - Maestr\u00EDa Ciencias de la Informaci\u00F3n y las Comunicaciones -  Bases de Datos");
	private final JLabel lblAaa = new JLabel("RECUBRIMIENTO M\u00CDNIMO BASES DE DATOS");
	private final JLabel lblId = new JLabel("ID");
	private final JLabel lblNombre = new JLabel("Nombre");
	private final JLabel lblImplicante = new JLabel("Implicante");
	private final JLabel lblImplicado = new JLabel("Implicado");
	static PanelGrafo jPanel1 = new PanelGrafo();
	Graphics g;
	static int ct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 544);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		CargarArchivoBtn.setFont(new Font("Calibri", Font.PLAIN, 11));
		CargarArchivoBtn.setBackground(new Color(152, 251, 152));

		JButton btnRmin = new JButton("Cubrimiento");
		JButton btnClaves = new JButton("Llaves Candidatas");

		CargarArchivoBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Boolean a;
				Boolean b;
				JFileChooser fl = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fl.setDialogTitle("Seleccione el archivo XML");

				int returnValue = fl.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					VentanaPrincipal.BorrarDatos();
					btnRmin.setEnabled(true);
					btnClaves.setEnabled(true);

					File XMLPath = fl.getSelectedFile();
					a = NodosXPath.LeerAtributos(XMLPath);
					b = NodosXPath.LeerDependencias(XMLPath);

					// Test cierre raul
					// String [] dependXML = new String [NodosXPath.dependences.size()];
					// dependXML= NodosXPath.dependences.toArray(dependXML);
					//

					// for (String s : dependXML)
					// System.out.println("arreglo nuevo " + s);
					//
					// System.out.println("Lista de atributos cargados " + NodosXPath.atributtes);
					// System.out.println("Lista de dependencias cargadas " +
					// NodosXPath.dependences);

					// System.out.println("cierre ejemplo: AB+");
					// System.out.println("dependencias : " + "A,B-->C; D-->E,F; C-->A; B,E-->C;
					// B,C-->D;"
					// + "C,F-->B,D; A,C,D-->B; C,E-->A,F");
					//
					// Set<Atributos> attrs = Atributos.getSet("A,B");
					// Set<FuncDep> fds = FuncDep
					// .getSet("A,B-->C; D-->E,F; C-->A; B,E-->C; B,C-->D;" + "C,F-->B,D; A,C,D-->B;
					// C,E-->A,F");
					//
					// System.out.println("cierre " + Operaciones.cierre(attrs, fds));

					// Test L0 raul
					// System.out.println("Paso L0, ejemplo . Dependences= "
					// + "A-->B;"
					// + "A,B-->CB,C;"
					// + "A-->BC; "
					// + "B,C-->D; "
					// + "B,C-->C,E");
					//
					// Set<FuncDep> depend = FuncDep.getSet(
					// "A-->B;"
					// + "A,B-->CB,C; "
					// + "A-->BC; "
					// + "B,C-->D; "
					// + "B,C-->C,E");
					// System.out.println("Paso L0= " + Operaciones.l0(depend));
					//
					// System.out.println("L0 para dependencias cargadas ");
					//

					// Set<FuncDep> fdCarg = FuncDep.getSet(dependXML);
					// System.out.println(" L0= " + Operaciones.l0(fdCarg));

				}
			}

		});
		
		JButton FNBCodd = new JButton("FNBC");
		
		FNBCodd.setFont(new Font("Calibri", Font.PLAIN, 11));
		FNBCodd.setBounds(275, 48, 87, 23);
		contentPane.add(FNBCodd);
		
		JButton btnBernstein = new JButton("Bernstein");
		
		btnBernstein.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnBernstein.setBounds(371, 49, 87, 23);
		contentPane.add(btnBernstein);

		JButton btnGenerarScript = new JButton("Script");
		btnGenerarScript.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnGenerarScript.setBackground(new Color(135, 206, 250));
		btnGenerarScript.setBounds(114, 49, 76, 23);
		contentPane.add(btnGenerarScript);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPane.setSelectedIndex(2);
		tabbedPane.setBounds(10, 83, 870, 400);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Datos Iniciales", null, panel, null);

		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(10, 39, 336, 293);
		panel.add(scrollPane);
		scrollPane.setViewportView(AtributosTable);
		AtributosTable.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int steps = e.getWheelRotation();
				if (ct + steps >= 0) {
					ct = ct + steps;
				}
			}
		});
		AtributosTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		AtributosTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		AtributosTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		AtributosTable.getPreferredScrollableViewportSize();

		AtributosTable.setShowVerticalLines(false);
		AtributosTable.setFont(new Font("Calibri", Font.PLAIN, 12));
		AtributosTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		AtributosTable.setAutoscrolls(true);

		AtributosTable.setEditingRow(-1);

		JLabel lblAtributos = new JLabel("Atributos");
		lblAtributos.setBounds(10, 11, 336, 18);
		panel.add(lblAtributos);
		lblAtributos.setOpaque(true);
		lblAtributos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtributos.setForeground(Color.WHITE);
		lblAtributos.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblAtributos.setBackground(new Color(255, 0, 0));
		lblNombre.setBounds(72, 28, 274, 12);
		panel.add(lblNombre);
		lblNombre.setOpaque(true);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblNombre.setBackground(new Color(240, 128, 128));
		lblId.setBounds(10, 28, 66, 12);
		panel.add(lblId);
		lblId.setOpaque(true);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblId.setBackground(new Color(240, 128, 128));
		lblImplicado.setBounds(616, 28, 239, 12);
		panel.add(lblImplicado);
		lblImplicado.setOpaque(true);
		lblImplicado.setHorizontalAlignment(SwingConstants.CENTER);
		lblImplicado.setForeground(Color.WHITE);
		lblImplicado.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblImplicado.setBackground(new Color(240, 128, 128));

		JLabel lblDependenciasFuncionales = new JLabel("Dependencias Funcionales");
		lblDependenciasFuncionales.setBounds(367, 11, 488, 18);
		panel.add(lblDependenciasFuncionales);
		lblDependenciasFuncionales.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblDependenciasFuncionales.setForeground(Color.WHITE);
		lblDependenciasFuncionales.setOpaque(true);
		lblDependenciasFuncionales.setBackground(new Color(255, 0, 0));
		lblDependenciasFuncionales.setHorizontalAlignment(SwingConstants.CENTER);
		lblImplicante.setBounds(367, 28, 254, 12);
		panel.add(lblImplicante);
		lblImplicante.setOpaque(true);
		lblImplicante.setHorizontalAlignment(SwingConstants.CENTER);
		lblImplicante.setForeground(Color.WHITE);
		lblImplicante.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblImplicante.setBackground(new Color(240, 128, 128));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(367, 40, 488, 292);
		panel.add(scrollPane_1);
		scrollPane_1.setViewportView(DFTable);

		DFTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DFTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		DFTable.getColumnModel().getColumn(1).setPreferredWidth(190);
		DFTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		DFTable.getColumnModel().getColumn(3).setPreferredWidth(195);
		DFTable.setShowVerticalLines(false);
		DFTable.setFont(new Font("Calibri", Font.PLAIN, 12));
		DFTable.setAutoscrolls(true);

		DFTable.setEditingRow(0);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarAtributo();
			}
		});
		btnAgregar.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAgregar.setBounds(252, 337, 94, 23);
		panel.add(btnAgregar);

		JButton btnBorrar_1 = new JButton("Borrar");
		btnBorrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BorrarAtributo();

			}
		});
		btnBorrar_1.setBackground(new Color(240, 128, 128));
		btnBorrar_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnBorrar_1.setBounds(10, 336, 79, 23);
		panel.add(btnBorrar_1);

		JButton button = new JButton("Agregar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarDF();
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 11));
		button.setBounds(761, 337, 94, 23);
		panel.add(button);

		JButton button_1 = new JButton("Borrar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarDF();
			}
		});
		button_1.setBackground(new Color(240, 128, 128));
		button_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_1.setBounds(367, 338, 79, 23);
		panel.add(button_1);

		JPanel panel_2 = new JPanel();

		tabbedPane.addTab("Grafico", null, panel_2, null);

		panel_2.setLayout(null);
		panel_2.setAutoscrolls(true);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(new Rectangle(0, 0, 400, 2000));

		scrollPane_2.setBounds(0, 0, 865, 372);
		panel_2.add(scrollPane_2);
		jPanel1.setBounds(new Rectangle(0, 0, 400, 2000));
		scrollPane_2.setViewportView(jPanel1);

		jPanel1.setBackground(new java.awt.Color(153, 153, 153));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 842, Short.MAX_VALUE));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGap(0, 464, Short.MAX_VALUE));
		jPanel1.setLayout(jPanel1Layout);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Resultado", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 40, 329, 268);
		panel_1.add(scrollPane_4);

		scrollPane_4.setViewportView(tableProyeccion);

		JLabel lblAtributosProyeccion = new JLabel("Atributos Proyeccion");
		lblAtributosProyeccion.setOpaque(true);
		lblAtributosProyeccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtributosProyeccion.setForeground(Color.WHITE);
		lblAtributosProyeccion.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblAtributosProyeccion.setBackground(Color.RED);
		lblAtributosProyeccion.setBounds(10, 11, 329, 18);
		panel_1.add(lblAtributosProyeccion);

		JLabel label_2 = new JLabel("Nombre");
		label_2.setOpaque(true);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Calibri", Font.PLAIN, 10));
		label_2.setBackground(new Color(240, 128, 128));
		label_2.setBounds(67, 29, 272, 12);
		panel_1.add(label_2);

		JLabel lblLlaves = new JLabel("Resultados");
		lblLlaves.setOpaque(true);
		lblLlaves.setHorizontalAlignment(SwingConstants.CENTER);
		lblLlaves.setForeground(Color.WHITE);
		lblLlaves.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblLlaves.setBackground(Color.RED);
		lblLlaves.setBounds(375, 13, 411, 18);
		panel_1.add(lblLlaves);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(374, 40, 411, 296);
		panel_1.add(scrollPane_3);

		JTextArea textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		textArea.setLineWrap(true);

		JButton button_2 = new JButton("Borrar");
		button_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_2.setBackground(new Color(240, 128, 128));
		button_2.setBounds(10, 313, 79, 23);
		panel_1.add(button_2);

		JButton button_3 = new JButton("Agregar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarAtributoProyeccion();
			}
		});
		button_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_3.setBounds(245, 313, 94, 23);
		panel_1.add(button_3);

		JLabel label = new JLabel("ID");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri", Font.PLAIN, 10));
		label.setBackground(new Color(240, 128, 128));
		label.setBounds(10, 29, 66, 12);
		panel_1.add(label);

		CargarArchivoBtn.setBounds(10, 49, 94, 23);
		contentPane.add(CargarArchivoBtn);

		UDFJC.setForeground(Color.WHITE);
		UDFJC.setBackground(Color.GRAY);
		UDFJC.setOpaque(true);
		UDFJC.setBounds(0, 494, 890, 23);

		contentPane.add(UDFJC);
		lblAaa.setHorizontalAlignment(SwingConstants.CENTER);
		lblAaa.setHorizontalTextPosition(SwingConstants.LEADING);
		lblAaa.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblAaa.setBackground(Color.GRAY);
		lblAaa.setForeground(Color.WHITE);
		lblAaa.setOpaque(true);
		lblAaa.setBounds(0, 0, 890, 38);

		contentPane.add(lblAaa);

		// cubrimi mal raul
		// JButton btnRmin = new JButton("Cubrimiento");
		btnRmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

				Set<FuncDep> fdCarg = NodosXPath.fds;
				Set<FuncDep> L0 = Operaciones.l0(fdCarg);
				// System.out.println(" L0= " + L0);
				Set<FuncDep> L1 = Operaciones.getL1(L0);
				// //System.out.println(" L1= " + L1);
				Set<FuncDep> L2 = Operaciones.getl2(L1);
				// //System.out.println(" L2= " + L2);

				JOptionPane.showMessageDialog(null,
						"L0: " + L0.toString() + '\n' + "L1: " + L1.toString() + '\n' + "L2: " + L2.toString(),
						"Recubrimiento MÃ­nimo", JOptionPane.INFORMATION_MESSAGE);

				textArea.setText("Cubrimiento Minimo: \n" + "====================================== \n \n" + "L0: "
						+ L0.toString() + '\n' + "-------------------------------- \n" + "L1: " + L1.toString() + '\n'
						+ "-------------------------------- \n" + "L2: " + L2.toString());
			}
		});
		btnRmin.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnRmin.setBounds(467, 49, 94, 23);
		contentPane.add(btnRmin);
		FNBCodd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Set<FuncDep> l2 = new HashSet<>();
				Set<Atributos> attrs = new HashSet<>();
				l2 = Operaciones.getl2(Operaciones.getL1(Operaciones.l0(NodosXPath.fds)));
				attrs = NodosXPath.attrs;
				textArea.setText(Operaciones.FNBC(l2, attrs).toString());
			}
		});
		btnBernstein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(EjecutarBernstein());
			}
		});
		btnClaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Set<FuncDep> fdCarg = NodosXPath.fds;
				Set<FuncDep> L0 = Operaciones.l0(fdCarg);
				System.out.println(" L0= " + L0);
				Set<FuncDep> L1 = Operaciones.getL1(L0);
				Set<FuncDep> L2 = Operaciones.getl2(L1);
				Set<Atributos> Obligatorios = new HashSet<>(NodosXPath.attrs);
				Set<Set<Atributos>> Todas = new HashSet<>();
				Set<Atributos> Implicados = new HashSet<>();
				Todas.addAll(Operaciones.TodasLasLlaves(L2, NodosXPath.attrs));
				for (FuncDep fd : L2) {
					Implicados.addAll(fd.getRight());
					Implicados.addAll(fd.getLeft());
				}
				Obligatorios.removeAll(Implicados);
				System.out.println("OBLIGATORIOS " + Obligatorios);
				String Result = "Conjunto de SuperLlaves " + '\n' + "===============================" + '\n'
						+ Todas + '\n' + '\n';
				Operaciones.candid.clear();
				String Result2 = "Conjunto de Llaves Candidatas " + '\n' + "===============================" + '\n'
						+ Operaciones.LlavesCandidatas(Todas, L2, Obligatorios, NodosXPath.attrs);

				textArea.setText(Result + Result2);
				/*
				 * JOptionPane.showMessageDialog (null, "Llave candidata: " +
				 * Operaciones.LlavesCandidatas() +'\n' + Operaciones.TodasLasLlaves() ,
				 * "Llaves" , JOptionPane.INFORMATION_MESSAGE);
				 */
				// panel_1
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnClaves.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnClaves.setBounds(668, 49, 134, 23);
		contentPane.add(btnClaves);

		JButton btnProyeccion = new JButton("Proyeccion");
		btnProyeccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Set<Atributos> Atr = new HashSet<>();
				for (int i = 0; i <= modeloProyeccion.getRowCount() - 1; i++) {
					Atributos at = new Atributos(modeloProyeccion.getValueAt(i, 1).toString());
					Atr.add(at);
				}
				textArea.setText(
						"El conjunto de dependencias para la Proyección es: \n ================================================ \n \n"
								+ "X = " + Atr + " \n" + "DEP(" + Atr + ") = " + EjecutarProyeccion().toString());
			}
		});
		btnProyeccion.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnProyeccion.setBounds(571, 49, 87, 23);
		contentPane.add(btnProyeccion);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal.BorrarDatos();

			}
		});
		btnBorrar.setBackground(new Color(240, 128, 128));
		btnBorrar.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnBorrar.setBounds(812, 49, 68, 23);
		contentPane.add(btnBorrar);

		JLabel lblTexto = new JLabel("");
		lblTexto.setBackground(Color.LIGHT_GRAY);
		lblTexto.setOpaque(true);
		lblTexto.setBounds(0, 38, 890, 38);
		contentPane.add(lblTexto);

	}

	public static void PonerTextoDF(String Izq, String Der) {
		int ID = modeloDF.getRowCount() + 1;
		modeloDF.addRow(new Object[] { ID, Izq, "-->", Der });
	}

	public static void PonerTextoAtributos(String Atrib) {
		int ID = modeloAt.getRowCount() + 1;
		modeloAt.addRow(new Object[] { ID, Atrib });

		// System.out.println("VentanaPrincipal.PonerTextoAtributos " + Atrib
		// + " dato " + modeloAt.getColumnName(0)
		// );
	}

	public static void TamanoPanel() {

		jPanel1.setPreferredSize(new Dimension(400, 2000));

	}

	public static void BorrarDatos() {

		DefaultTableModel modeloAt = (DefaultTableModel) AtributosTable.getModel();
		modeloAt.getDataVector().removeAllElements();
		modeloAt.fireTableDataChanged();

		DefaultTableModel modeloDF = (DefaultTableModel) DFTable.getModel();
		modeloDF.getDataVector().removeAllElements();
		modeloDF.fireTableDataChanged();
		
		DefaultTableModel modeloProyeccion = (DefaultTableModel) tableProyeccion.getModel();
		modeloProyeccion.getDataVector().removeAllElements();
		modeloProyeccion.fireTableDataChanged();

		Grafo.PanelGrafo.nodos.clear();
		Grafo.PanelGrafo.arcos.clear();
		Grafo.PanelGrafo.nodosD.clear();
		Grafo.PanelGrafo.nodosI.clear();
		Grafo.PanelGrafo.atributos.clear();
		NodosXPath.attrs.clear();
		NodosXPath.fds.clear();
	
		yi = 0;

	}

	public static void AgregarAtributo() {
		String nombreatr = null;
		try {
			nombreatr = JOptionPane.showInputDialog("Ingrese el nombre del atributo ");

			if (!nombreatr.equals(null) && !nombreatr.replace(" ", "").equals("")) {
				int cant = AtributosTable.getRowCount() + 1;
				NodosXPath.VerificarAtributo(cant, nombreatr);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Esciba un nombre valido para el atributo");
		}

	}

	public static Set<FuncDep> EjecutarProyeccion() {
		Set<FuncDep> Result = new HashSet<>();
		if (modeloProyeccion.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Debe agregar un conjunto de atributos para realizar la proyección");
		} else {
			Set<Atributos> Att = new HashSet<>();
			for (int i = 0; i <= modeloProyeccion.getRowCount() - 1; i++) {
				Atributos a = new Atributos(modeloProyeccion.getValueAt(i, 1).toString());
				Att.add(a);
			}
			Result.addAll(Operaciones.Proyeccion(Att));
		}
		return Result;
	}
	public static String EjecutarBernstein() {
		Set<Set<FuncDep>> Resultado = new HashSet<>();
		Set<FuncDep> Minimal = new HashSet<>();
		Set<Set<Atributos>> Llc = new HashSet<>();
		Set<Atributos> Oblig = new HashSet<>();
		Oblig.addAll(NodosXPath.attrs);
		String Res = "Algoritmo de Bernstein: \n ==================================== \n";
		Minimal.addAll(Operaciones.getl2(Operaciones.getL1(Operaciones.l0(NodosXPath.fds))));
		Res = Res + "Conjunto con reducción minimal: " + Minimal + "\n ---------------------------------- \n";
		for (FuncDep f : Minimal) {
			Oblig.removeAll(f.getLeft());
			Oblig.removeAll(f.getRight());
		}
		Set<Set<Atributos>> Ll1 = new HashSet<>();
		Ll1.addAll(Operaciones.TodasLasLlaves(Minimal, NodosXPath.attrs));
		Llc.addAll(Operaciones.LlavesCandidatas(Ll1, Minimal, Oblig, NodosXPath.attrs));
		Res = Res + "Llave(s) Candidata(s): " + Llc + "\n ==================================== \n Subconjuntos: \n";
		Resultado.addAll(Operaciones.AlgBernstein(NodosXPath.fds));
		int i = 1;
		for (Set<FuncDep> r1 : Resultado) {
			Set<Set<Atributos>> Llc1 = new HashSet<>();
			Set<Atributos> Obl1 = new HashSet<>();
			Set<Atributos> Todos1 = new HashSet<>();
			Res = Res + i + ") Dependencias " + r1 + "\n";
			for (FuncDep r2 : r1) {
				Todos1.addAll(r2.getLeft());
				Todos1.addAll(r2.getRight());
			}
			Operaciones.candid.clear();
			Set<Set<Atributos>> Ll2 = new HashSet<>();
			Ll2.addAll(Operaciones.TodasLasLlaves(r1, Todos1));
			Llc1.addAll(Operaciones.LlavesCandidatas(Ll2, r1, Obl1, Todos1));
			System.out.println("Conjunto DFs " + r1 + " Obl " + Obl1 + " Todos " + Todos1 + " Llaves " + Llc1);
			Res = Res + "Llave(s) Candidata(s): " + Llc1 + "\n ---------------------------------- \n";
		i++;
		}
		
		return Res;
		
	}

	public static void AgregarAtributoProyeccion() {
		String nombreatr = null;
		try {
			nombreatr = JOptionPane.showInputDialog("Ingrese el nombre del atributo ");

			if (!nombreatr.equals(null) && !nombreatr.replace(" ", "").equals("")) {
				Atributos a = new Atributos(nombreatr);

				if (NodosXPath.attrs.contains(a)) {

					PonerTextoProyeccion(nombreatr);

				} else {

					JOptionPane.showMessageDialog(null, "Esciba un nombre valido para el atributo");
				}

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Esciba un nombre valido para el atributo...");
		}

	}

	public static void PonerTextoProyeccion(String Atrib) {
		int ID = modeloProyeccion.getRowCount() + 1;
		modeloProyeccion.addRow(new Object[] { ID, Atrib });
		System.out.println("Texto Agregado " + ID + Atrib);
	}

	public static void BorrarAtributo() {
		int Campo;
		String Nombre;
		Campo = AtributosTable.getSelectedRow();
		if (Campo >= 0) {
			Nombre = AtributosTable.getValueAt(Campo, 1).toString();
			PanelGrafo.atributos.remove(Nombre);
			modeloAt.removeRow(Campo);
			for (int i = Campo; i <= modeloAt.getRowCount() - 1; i++) {
				modeloAt.setValueAt(i + 1, i, 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione un atributo de la tabla ");
		}
	}

	public static void BorrarDF() {
		int Campo;
		String NombreIzqu;
		String NombreDere;
		Campo = DFTable.getSelectedRow();
		NombreIzqu = DFTable.getValueAt(Campo, 1).toString();
		NombreDere = DFTable.getValueAt(Campo, 3).toString();
		if (Campo >= 0) {
			for (int e = 0; e <= PanelGrafo.arcos.size(); e++) {
				String Origen = PanelGrafo.arcos.get(e).getOrigen().getNombre().toString();
				String Destino = PanelGrafo.arcos.get(e).getDestino().getNombre().toString();
				if ((Origen == NombreIzqu) && (Destino == NombreDere)) {
					PanelGrafo.nodos.remove(PanelGrafo.arcos.get(e).getOrigen());
					PanelGrafo.nodosI.remove(NombreIzqu);
					PanelGrafo.nodos.remove(PanelGrafo.arcos.get(e).getDestino());
					PanelGrafo.nodosD.remove(NombreDere);
					PanelGrafo.arcos.remove(PanelGrafo.arcos.get(e));
					break;
				}
			}

			modeloDF.removeRow(Campo);
			for (int i = Campo; i <= modeloDF.getRowCount() - 1; i++) {
				modeloDF.setValueAt(i + 1, i, 0);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione una Dependencia Funcional de la tabla ");
		}
	}

	public static void AgregarDF() {
		Nodo Iz = null;
		Nodo Dr = null;
		String implicado = null;
		String implicante = null;

		try {
			implicante = JOptionPane.showInputDialog("Ingrese el nombre del implicante ");

			if (!implicante.equals(null) && !implicante.replace(" ", "").equals("")) {

				Iz = NodosXPath.VerificarNodoI(implicante);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Esciba un nombre valido para el implicante ");
		}
		try {
			implicado = JOptionPane.showInputDialog("Ingrese el nombre del implicado ");
			if (!implicado.equals(null) && !implicado.replace(" ", "").equals("")) {

				Dr = NodosXPath.VerificarNodoD(implicado);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Esciba un nombre valido para el implicado ");
		}

		String id = DFTable.getRowCount() + 1 + "";
		PonerTextoDF(implicante, implicado);
		NodosXPath.AgregarArco(Iz, Dr);

	}
}