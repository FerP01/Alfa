package ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Menu {
	public static JTextArea areaTexto;
	public static JTextArea areaInfo;
	public static JMenuBar barraMenu;
	private JMenu menuArchivo;
	private JMenu menuHerramientas;
	private JMenu menuEditar;
	private Procedimientos procedimientos;

	public Menu() throws FileNotFoundException{
		barraMenu = new JMenuBar();
		areaTexto = new JTextArea(25,80);
		areaInfo = new JTextArea(1,80);
		menuArchivo = new JMenu("File");
		menuHerramientas = new JMenu("Tools");
		menuEditar = new JMenu("Edit");
		
		procedimientos = new Procedimientos(areaTexto,areaInfo);
		procedimientos.crearArbol();
	}
	
	public void menuArhivo(){
		
		
		JMenuItem accionNuevo = new JMenuItem("New");
		accionNuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					procedimientos.nuevo();
			}
		}
		);
		menuArchivo.add(accionNuevo);
		
		JMenuItem accionBorrar = new JMenuItem("Delete");
		accionBorrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				procedimientos.borrar();
			}
		}
				);
		menuArchivo.add(accionBorrar);
		
		JMenuItem accionAbrir = new JMenuItem("Open");
		accionAbrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				procedimientos.abrir();
			}
		}
		);
		menuArchivo.add(accionAbrir);
		
		
		JMenuItem accionCerrar = new JMenuItem("Close");
		accionCerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				procedimientos.cerrar();
			}
		}
		);
		menuArchivo.add(accionCerrar);
		
		
		JMenuItem accionGuardar = new JMenuItem("Save");
		accionGuardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					procedimientos.guardar();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
				);
		menuArchivo.add(accionGuardar);
		
		barraMenu.add(menuArchivo);
	}
	
	public void menuHerramientas(){
		
		JMenuItem accionCalcular = new JMenuItem("Calculator");
		accionCalcular.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				procedimientos.ejecutarCalculadora();
			}
		}
		);
		menuHerramientas.add(accionCalcular);
		
		
		JMenuItem accionLeerURL = new JMenuItem("URL");
		accionLeerURL.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				procedimientos.leerURL();
			}
		}
		);
		menuHerramientas.add(accionLeerURL);
		
		barraMenu.add(menuHerramientas);
		}
	
	
	public void menuEditar(){
		JMenuItem accionLimpiar = new JMenuItem("Clear");
		accionLimpiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				areaTexto.setText("");
			}
		}
		);
		menuEditar.add(accionLimpiar);
		barraMenu.add(menuEditar);
		}
	
}
