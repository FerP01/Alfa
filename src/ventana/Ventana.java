package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class Ventana {
	
	public Ventana(){
	}
	
	public void crearVentana() throws FileNotFoundException{
		
		
		JFrame ventana = new JFrame("");
		Menu menu = new Menu();
		menu.menuArhivo();
		menu.menuHerramientas();
		menu.menuEditar();
		
		ventana.getContentPane().add(menu.barraMenu, BorderLayout.NORTH);
		//el menu se coloca en la parte superio(NORTH)
		
		menu.areaTexto.setLineWrap(true);//parte las lineas al llegar al limite de columnas
		menu.areaTexto.setWrapStyleWord(true);
		//menu.barraMenu.setBackground();
		//menu.areaTexto.setBackground(Color.black);
		menu.areaTexto.setForeground(Color.black);
		
		Font font = new Font("Verdana", Font.ROMAN_BASELINE, 15);
		menu.areaTexto.setFont(font);
		menu.barraMenu.setFont(font);

		JScrollPane scroll = new JScrollPane(Menu.areaTexto);
		ventana.getContentPane().add(scroll);
		ventana.getContentPane().add(menu.areaInfo, BorderLayout.SOUTH);
		
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//ventana.setSize(1000, 500);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Ventana ventana = new Ventana();
		ventana.crearVentana();
		
	}
}
