package ejercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class SopaDeLetra {
	private char[][] sopaDeLetras;
	private String[] palabras;
	
	public SopaDeLetra(String fileInput) {
		try {
			Scanner sc = new Scanner(new File(fileInput));
			sc.useLocale(Locale.ENGLISH);

			int ancho_alto = sc.nextInt(); 	
			int cantidad_de_palabras = sc.nextInt(); 
			
			sopaDeLetras = new char[ancho_alto][ancho_alto];
			palabras = new String[cantidad_de_palabras];

			for (int i = 0; i < ancho_alto; i++) {					
				sopaDeLetras[i] = sc.next().toCharArray();				
			}

			for (int i = 0; i < cantidad_de_palabras; i++) {					
				palabras[i] = sc.next();
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public char[][] getSopaDeLetras() {
		return sopaDeLetras;
	}

	public String[] getPalabras() {
		return palabras;
	}

	public void prepararResultado() {
		String[] todasLasPalabrasOE = new String[this.sopaDeLetras.length];
		String[] todasLasPalabrasEO = new String[this.sopaDeLetras.length];
		String[] todasLasPalabrasNS = new String[this.sopaDeLetras.length];
		String[] todasLasPalabrasSN = new String[this.sopaDeLetras.length];
		
		for(int i=0; i<this.sopaDeLetras.length; i++)
		{
			String palabraOE = ""; 
			String palabraEO = ""; 
			String palabraNS = ""; 
			String palabraSN = ""; 
			
			for(int j=0; j<this.sopaDeLetras.length; j++)
			{
				palabraOE+= this.sopaDeLetras[i][j];
				palabraEO+= this.sopaDeLetras[i][this.sopaDeLetras.length-j-1];
				palabraNS+= this.sopaDeLetras[j][i];
				palabraSN+= this.sopaDeLetras[this.sopaDeLetras.length-j-1][i];
			}
			/*
			System.out.println("----------------------------");
			System.out.println("palabraOE: " + palabraOE);
			System.out.println("palabraEO: " + palabraEO);
			System.out.println("palabraNS: " + palabraNS);
			System.out.println("palabraSN: " + palabraSN);
			*/
			todasLasPalabrasOE[i] = palabraOE;
			todasLasPalabrasEO[i] = palabraEO;
			todasLasPalabrasNS[i] = palabraNS;
			todasLasPalabrasSN[i] = palabraSN;
		}

		try {
			PrintWriter salida = new PrintWriter(new FileWriter("salida.out"));
			
			for(int i=0; i<this.palabras.length; i++) {
				String palabra = this.palabras[i];
				Boolean loencontre = false;
				String enqueDireccion = "";
	
				for(int j=0; j<this.sopaDeLetras.length && !loencontre; j++) {
					if(todasLasPalabrasOE[j].contains(palabra)) {
						loencontre = true;
						enqueDireccion = "E";					
					}
					else if(todasLasPalabrasEO[j].contains(palabra)) {
						loencontre = true;
						enqueDireccion = "O";					
					}
					else if(todasLasPalabrasNS[j].contains(palabra)) {
						loencontre = true;
						enqueDireccion = "S";					
					}
					else if(todasLasPalabrasSN[j].contains(palabra)) {
						loencontre = true;
						enqueDireccion = "N";					
					}
				}
				
				if(loencontre) {
					salida.println((i+1) + " " + enqueDireccion);
				}
			}
		    salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void prepararResultadoOptimizado() {		

		try {
			PrintWriter salida = new PrintWriter(new FileWriter("salida2.out"));
			
			//Guardo el indice de la palbra a buscar que ya encontre para hacer menos comparaciones.
			LinkedList<String> YaEncontrados = new LinkedList<>();
			
			for(int i=0; i<this.sopaDeLetras.length && YaEncontrados.size() != this.palabras.length; i++)
			{
				String palabraOE = ""; 
				String palabraEO = ""; 
				String palabraNS = ""; 
				String palabraSN = ""; 
				
				for(int j=0; j<this.sopaDeLetras.length; j++)
				{
					palabraOE+= this.sopaDeLetras[i][j];
					palabraEO+= this.sopaDeLetras[i][this.sopaDeLetras.length-j-1];
					palabraNS+= this.sopaDeLetras[j][i];
					palabraSN+= this.sopaDeLetras[this.sopaDeLetras.length-j-1][i];
				}

				for(int k=0; k<this.palabras.length; k++) {
					String palabra = this.palabras[k];
					
					if(YaEncontrados.contains(palabra))
						continue;
					
					Boolean loencontre = false;
					String enqueDireccion = "";
					
					if(palabraOE.contains(palabra)) {
						loencontre = true;
						enqueDireccion = "E";					
					}
					else if(palabraEO.contains(palabra)) {
						loencontre = true;
						enqueDireccion = "O";					
					}
					else if(palabraNS.contains(palabra)) {
						loencontre = true;
						enqueDireccion = "S";					
					}
					else if(palabraSN.contains(palabra)) {
						loencontre = true;
						enqueDireccion = "N";					
					}

					if(loencontre) {
						YaEncontrados.add(palabra);
						salida.println((k+1) + " " + enqueDireccion);
					}
				}
			}
		    salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
