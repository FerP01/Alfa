package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Procedimientos {
	private JTextArea areaTexto;
	private JTextArea areaInfo;
	private File directorio ; 
	public String archivoAbierto ;
	
	private String [] valoresCalcular;
	
	private TreeSet arbol; 
	

	public Procedimientos(JTextArea a , JTextArea b){
		areaTexto = a;
		areaInfo = b;
		directorio = new File("C:\\Users\\Fer\\Desktop\\PruebasJava");
		directorio.mkdirs();
		archivoAbierto = null;
		
		valoresCalcular = new String [50];
	}
	
	
	//crea la ventana de la calculadora
	public void ejecutarCalculadora(){
		JFrame calculadora = new JFrame("Calculadora");
		JTextArea areaCalculadora = new JTextArea();
		
		areaCalculadora.setForeground(Color.blue);
		Font font = new Font("Verdana", Font.ROMAN_BASELINE, 20);
		areaCalculadora.setFont(font);
		
		JButton cerrar = new JButton("CLOSE");
		cerrar.setBackground(Color.red);
		cerrar.setFont(font);
		calculadora.getContentPane().add(cerrar, BorderLayout.SOUTH);
		
		JScrollPane scroll = new JScrollPane(areaCalculadora);
		calculadora.getContentPane().add(scroll);
		
		calculadora.setSize(800, 200);
		calculadora.setLocationRelativeTo(null);
		calculadora.setVisible(true);
		
		cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				calculadora.setVisible(false);
			}
    	}
    	);
		
		areaCalculadora.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode()==KeyEvent.VK_ENTER){
					
					valoresCalcular = new String [50];
					for(int i=0;i<valoresCalcular.length;i++){
						valoresCalcular[i] = null;
					}
					
					Scanner sc = new Scanner(areaCalculadora.getText());
					sc.useDelimiter(" ");
					int i=0;
					do{
						valoresCalcular[i] = sc.next();
						i++;
					}while(sc.hasNext());
					sc.close();
					
					double res = calcular(0, valoresCalcular); 
					
					areaCalculadora.setText(String.valueOf(res));
				}
			}
		});
	}
	
	
	
	//Realiza los calculos 
	public Float calcular(int i , String [] valoresCalcular){
		float res = 0;
		res = Float.parseFloat(valoresCalcular[i]);
		do{
			if( valoresCalcular[i+1].equals("+") ){
				res = res + Float.parseFloat(valoresCalcular[i+2]);
				i=i+2;
			}else if(valoresCalcular[i+1].equals("-")){
				res = res - Float.parseFloat(valoresCalcular[i+2]);
				i=i+2;
			}else if(valoresCalcular[i+1].equals("*")){
				res = res * Float.parseFloat(valoresCalcular[i+2]);
				i=i+2;
			}else if(valoresCalcular[i+1].equals("/")){
				res = res / Float.parseFloat(valoresCalcular[i+2]);
				i=i+2;
			}else if(valoresCalcular[i+1].equals("^")){
				res = (float) Math.pow(res, Float.parseFloat(valoresCalcular[i+2]));
				i=i+2;
			}  
		}while(valoresCalcular[i+1] != null);
		return res;
	}
	
	
	
	
	//Crea el arbol para las palabras en español
	public void crearArbol() throws FileNotFoundException {
		arbol = new TreeSet<String>();
		File archivo = new File("Diccionario.txt");
		Scanner sc = new Scanner(archivo);
		while(sc.hasNextLine()){
			arbol.add(sc.nextLine());
		}
		sc.close();
    	System.out.println(arbol);
    	System.out.println(arbol.size());
	}
	
	
	
	
	//Lee el texto de una web
	public void leerURL(){
		
		JFrame vOpen = new JFrame();
		JTextArea areaOpen = new JTextArea();
		JTextField entrada = new JTextField();
		vOpen.getContentPane().add(entrada, BorderLayout.SOUTH);
		
		String s = "Introduzca la URL que desea abrir : " + "\n";
		areaOpen.setText(s);
		
		entrada.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode()==KeyEvent.VK_ENTER){
					
					String url = entrada.getText();
					StringBuffer resultado = new StringBuffer();	 
					try {
						URL urlPagina = new URL(url);
						URLConnection urlConexion = urlPagina.openConnection();
						urlConexion.connect();
						
						// Creamos el objeto con el que vamos a leer
						BufferedReader lector = new BufferedReader(new InputStreamReader(
								urlConexion.getInputStream(), "UTF-8"));
						String linea = "";
						String contenido = "";
						
						while ((linea = lector.readLine()) != null) {
							//linea = escanearLinea(linea);
							resultado.append(String.valueOf(linea));
							resultado.append("\n");
						}
						
						
						areaTexto.setText(resultado.toString());
						
						vOpen.setVisible(false);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		vOpen.add(areaOpen);
		
		vOpen.setLocationRelativeTo(null);
		vOpen.pack();
		vOpen.setVisible(true);
	}
	
	
	
	
	//escanea el texto de la web para quedarse con las palabras en español
	public String escanearLinea(String linea) throws FileNotFoundException{
		String lineaFinal = "";
		String pal;
		
		Scanner sc = new Scanner(linea);
		sc.useDelimiter(" "); 
		while(sc.hasNext()){
			pal = sc.next().toUpperCase();
			if(arbol.contains(pal)){
				lineaFinal += pal + " ";
			}
		}
		return lineaFinal;
	}
	
	
	
	//abre un archivo de texto
	public void abrir(){
		/*JFrame vOpen = new JFrame();
		JTextArea areaOpen = new JTextArea();
		JTextField entrada = new JTextField();
		vOpen.getContentPane().add(entrada, BorderLayout.SOUTH);
		
		String s = "ARCHIVOS :" + "\n" + "\n";
		File[] ficheros = directorio.listFiles();
		for (int x=0;x<ficheros.length;x++){
		  s += ficheros[x].getName() + "\n";
		}
		areaOpen.setText(s);
		
		entrada.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode()==KeyEvent.VK_ENTER){
					String archivoString = "C:\\Users\\Fer\\Desktop\\PruebasJava\\" + entrada.getText();
					File archivo = new File(archivoString);
					if(!archivo.exists()){
						areaOpen.append("\n" + "\n" + "El archivo no existe");
					}else{
						try {
							archivoAbierto = entrada.getText();
							String s = escanearArchivo(archivo);
							vOpen.setVisible(false);
							areaTexto.setText(s);
							
							actualizarBarraInfo();
						} catch (FileNotFoundException e){
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		vOpen.add(areaOpen);
		
		vOpen.setLocationRelativeTo(null);
		vOpen.setSize(500,200);;
		vOpen.setVisible(true);*/
		
		JFileChooser archivoChooser = new JFileChooser();
		archivoChooser.showOpenDialog(null);
		File archivo = archivoChooser.getSelectedFile();
		Scanner sc = null;
		try {
			sc = new Scanner(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String texto = "";
		while(sc.hasNextLine()){
			texto += sc.nextLine() + "\n";
		}
		
		areaTexto.setText(texto);
		areaInfo.setText(archivoChooser.getSelectedFile().getPath());
		
	}
	
	//escanea un archivo para obtener su contenido
	public String escanearArchivo(File archivo) throws FileNotFoundException{
		String s = "";
		Scanner sc = new Scanner(archivo);
		while(sc.hasNextLine()){
			s += sc.nextLine() + "\n";
		}
		sc.close();
		return s;
	}
	
	
	
	//guarda el texto en un archivo
	public void guardar() throws IOException{
		/*if(archivoAbierto != null){
			
			File TextFile = new File(archivoAbierto); 
			TextFile.delete();
			
			File TextFile2 = new File(archivoAbierto); 
			FileWriter TextOut = new FileWriter(TextFile2, true);
			TextFile2.createNewFile();
			
			String s = areaTexto.getText();		
			TextOut.write(s + "\n");
			TextOut.close();
			
		}else{
			
			JFrame vOpen = new JFrame();
			JTextArea areaOpen = new JTextArea();
			JTextField entrada = new JTextField();
			vOpen.getContentPane().add(entrada, BorderLayout.SOUTH);
			
			String s = "No hay ningun archivo abierto.Guardar en ? : " +
			"\n" + "ARCHIVOS :" + "\n" + "\n";
			File[] ficheros = directorio.listFiles();
			for (int x=0;x<ficheros.length;x++){
			  s += ficheros[x].getName() + "\n";
			}
			areaOpen.setText(s);
			
			entrada.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent evt) {
					if (evt.getKeyCode()==KeyEvent.VK_ENTER){
						String archivoString = "C:\\Users\\Fer\\Desktop\\PruebasJava\\" + entrada.getText();
						File archivo = new File(archivoString);
						if(!archivo.exists()){
							areaOpen.append("\n" + "\n" + "El archivo no existe");
						}else{
							try {
								archivoAbierto = archivoString;
								String s1 = areaTexto.getText();
								String s = escanearArchivo(archivo);
								
								File TextFile = new File(archivoAbierto); 
								TextFile.delete();
								
								File TextFile2 = new File(archivoAbierto); 
								FileWriter TextOut = new FileWriter(TextFile2, true);
								TextFile2.createNewFile();
									
								TextOut.write(s + "\n" + s1);
								TextOut.close();
								vOpen.setVisible(false);
							} catch (FileNotFoundException e){
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
			
			vOpen.add(areaOpen);
			
			vOpen.setLocationRelativeTo(null);
			vOpen.setSize(500,200);;
			vOpen.setVisible(true);
		}*/
		
		JFileChooser archivoChooser = new JFileChooser();
		archivoChooser.showSaveDialog(null);
		try(PrintWriter pw = new PrintWriter(archivoChooser.getSelectedFile())){
			pw.println(areaTexto.getText());
		}
	}
	
	
	//crea un nuevo archivo
	public void nuevo(){
		JFrame vCrearArchivo = new JFrame();
		JTextArea areaCrear = new JTextArea();
		JTextField entrada = new JTextField();
		vCrearArchivo.getContentPane().add(entrada, BorderLayout.SOUTH);
		
		String s = "ARCHIVOS :" + "\n" + "\n";
		File[] ficheros = directorio.listFiles();
		for (int x=0;x<ficheros.length;x++){
		  s += ficheros[x].getName() + "\n";
		}
		
		areaCrear.setText(s + "\n" + "Introduzca el nombre del nuevo archivo:" + "\n");
		
		vCrearArchivo.setLocationRelativeTo(null);
		vCrearArchivo.getContentPane().add(areaCrear, BorderLayout.NORTH);
		vCrearArchivo.pack();
		vCrearArchivo.setVisible(true);
		
		entrada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String archivoString = "C:\\Users\\Fer\\Desktop\\PruebasJava\\" + entrada.getText();
				File archivo = new File(archivoString);
				try {
					archivo.createNewFile();
					
					File TextFile = new File(archivoAbierto); 
					FileWriter TextOut = new FileWriter(TextFile, true);		
					TextOut.write(archivoString + "\n");
					TextOut.close();
					
					vCrearArchivo.setVisible(false);
				} catch (IOException e1) {
					areaCrear.append("Error");
				}
			}
    	}
    	);
	}
	
	
	//cierra el archivo abierto y limpia la pantalla
	public void cerrar(){
		areaTexto.setText("");
		archivoAbierto = null;
		
		actualizarBarraInfo();
	}
	
	
	//elimina un archivo
	public void borrar(){
		JFrame vBorrarArchivo = new JFrame();
		JTextArea areaBorrar = new JTextArea();
		JTextField entrada = new JTextField();
		vBorrarArchivo.getContentPane().add(entrada, BorderLayout.SOUTH);
		
		String s = "ARCHIVOS :" + "\n" + "\n";
		File[] ficheros = directorio.listFiles();
		for (int x=0;x<ficheros.length;x++){
		  s += ficheros[x].getName() + "\n";
		}
		
		areaBorrar.setText(s + "\n" + "Introduzca el nombre del archivo que desea borrar:" + "\n");
		
		vBorrarArchivo.setLocationRelativeTo(null);
		vBorrarArchivo.getContentPane().add(areaBorrar, BorderLayout.NORTH);
		vBorrarArchivo.pack();
		vBorrarArchivo.setVisible(true);
		
		entrada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String archivoString = "C:\\Users\\Fer\\Desktop\\PruebasJava\\" + entrada.getText();
				File archivo = new File(archivoString);
				archivo.delete();
				vBorrarArchivo.setVisible(false);
			}
    	}
    	);
	}
	
	
	
	//saca el nombre del archivo abierto actualmente
	public void actualizarBarraInfo(){
		if(archivoAbierto == null){
			areaInfo.setText("Archivo: ");
		}else{
			areaInfo.setText("Archivo: " + archivoAbierto);
		}
	}
	
}
