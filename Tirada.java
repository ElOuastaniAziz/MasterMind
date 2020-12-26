import java.util.*;
import java.io.*;

public class Tirada implements Serializable{
	//Declaracion de miembros
	private byte[] tabla012={48,48,48,48,48};
	private byte[] numeroIntroducido;
	private byte 	numBienPosicionados;
	private byte 	numMalPosicionados;
	//constructores 
	public Tirada(){};
	public Tirada(String cadtoInt, byte[] numeroAleatorio){
		//convertimos la cadena cadtoInt recibida como parámetero a int mediante una llamada al método aTablaInt
		numeroIntroducido=aTablaByte(cadtoInt);
		//Llamo al método bienPosicionados y le paso como argumentos  el numeroAleatorio
		bienPosicionados(numeroAleatorio);
		//Llamo al método malPosicionados y le paso como argumentos  el numeroAleatorio
		malPosicionados(numeroAleatorio);
	}
	////////////////////////////////////////////////////
	// Este método devuelve los números bien posicionados
	private void bienPosicionados(byte numRandom[]){ 
		String cad[]= new String[numRandom.length];
		for(int i= 0; i<numRandom.length; i++){
			if(numRandom[i]==numeroIntroducido[i]){
				tabla012[i]=49;
				numBienPosicionados++;
			}		
		}
	}
	// Método que devuelve los números mal posicionados 
	private void malPosicionados(byte numRandom[]){
		String cad[]= new String[numRandom.length];
		boolean salir;
		for(int i=0; i<numRandom.length;i++){
			salir=true;
			for(int j=0; j<numRandom.length && salir;j++){
				if(numeroIntroducido[i]==numRandom[j] && tabla012[i]==48 && tabla012[j]!=49 ){
					tabla012[i]=50;
					numMalPosicionados++;
					salir=false;
					
				}
			}
		}
	}
	//////////////////////////
	public String toString(){
		return "\n"+new String(numeroIntroducido)+"\t"+numBienPosicionados+"\t"+numMalPosicionados+"\t"+new String(tabla012);
	}
	// Este método convierte las cadenas en tablas de tipo int
	private byte [] aTablaByte(String cadena){
		byte cadenaTnt[]=new byte[cadena.length()];
		for(int i=0;i<cadena.length();i++){
			//cadenaTnt[i]=(byte)Character.getNumericValue(cadena.charAt(i));
			cadenaTnt[i]=(byte)(cadena.charAt(i));
		}
		return cadenaTnt;
	}
}
