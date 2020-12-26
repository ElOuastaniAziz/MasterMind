import java.util.*;
import java.io.*;
import java.math.BigInteger; 
public class Partida implements Serializable{
	protected byte[] numRandom;
	ArrayList <Tirada> juego= new ArrayList<Tirada>();
	

	public Partida(){
			numRandom=tablaAleatoria(9,5,0);
	}
	
	public Partida(String nomaAle){
			numRandom=nomaAle.getBytes();
			for(int i = 0; i<numRandom.length;i++){
				
				numRandom[i]=(byte)(numRandom[i]);
				//System.out.print(numRandom[i]);
				break;
			}
	}
	//@Override
	public String toString(boolean avanzado){
		String s ="";
		Iterator it = juego.iterator();
		if(avanzado){
			while(it.hasNext()){
				s=it.next()+"\n\t"; //s=s+it.next()+"\n\t";
			}
			return s;
		}
		else{
			String cad2="";
			String cad3="";
			for(Tirada lista1 : juego){
				cad2=lista1+"\n\t";
				cad2=cad2.substring(0,10);
				cad3=cad2+"\n\t";	 //cad3+=cad2+"\n\t";	
			}
			
			 return  cad3;
		}
		
	}
	//@Override
	public String toString(){
		String s ="";
		Iterator it = juego.iterator();
		
			while(it.hasNext()){
				s=it.next()+"\n\t"; //s=s+it.next()+"\n\t";
			}
			return s;
		}
		
		
	
	
	public void anadirTirada(String numero){
			juego.add(new Tirada(numero, numRandom));
			
	}
	private  byte[] tablaAleatoria( int numMax, int numElementos,int numMin){
		byte tabla[] = new byte [numElementos];
		byte rango =(byte)((numMax-numMin)+1);
		for(int i=0; i<numElementos; i++){
			tabla[i]=(byte)((Math.random()*rango)+numMin);
		}

		for(int i=0; i<tabla.length;i++){
			tabla[i]+=48;
			
		}
				
		return tabla;
	}
	public boolean isComplete(String numIntroducido){
		if(numIntroducido.equals(new String(numRandom))) return true;
		return false;
	}
	public 	String getNumAleato(){
		/*String cad1 = new String(numRandom);
		
		int  numRan= Integer.parseInt(cad1);
		return numRan;*/
		
		return new String(numRandom);
	}
	//public void  setNumAleatorio(String nomaAle){
		//String cad1 = new String(numRandom);
		//int numRan= Integer.parseInt(cad1);
		//return numRan;
		
			/*byte numeByte[]= new byte[5];
			for(int i=0; i<nomaAle.length();i++){
				numRandom[i]=(byte)(nomaAle.charAt(i)-48);
			}*/
		//	System.out.println("Hola :)");
		
		//numRandom=new Byte(nomaAle).toBt;
	//}
	
	
}

	
	
	

	
	
