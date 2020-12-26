import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import java.sql.*;
import java.util.ArrayList;


public class Advanced{
	private JFrame frame ;
	private JPanel panel;
	private JLabel titulo;
	private JButton play;
	private JTextField display;
	private JButton delete;
	private JTextArea  contador;
	private JTextArea area;
	private JButton nuevoJuego;
	private JButton historial;
	private int identificador;
	private String idColumna;
	private int contLines;
	private String imprimirResultado="";
	private String  numeroInt="";
	private String   bienp="";
	private String  malp="" ;
	private String tabla012="";
	private int numero_partida;
	private boolean seguir = true;
	
	ClassInter handler = new ClassInter();	
	Partida  juego= null;
	BasesDatos bases =null;

	public Advanced(String idpd){
		
			bases = new BasesDatos();
			idColumna=idpd;	
			bases.conectarse();
			bases.crearPartidaBd();
			identificador=bases.getIdPartida();
							
			frame = new JFrame("MasterMind");
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			panel = new JPanel(new GridBagLayout());
			panel.setBackground(new Color(49,166,234));
			frame.setSize(450,700);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.add(panel);
			
			GridBagConstraints gbc = new GridBagConstraints();
			BufferedImage myPicture=null;
			JLabel titulo  = null;
			
			//logotipo de masterMind
			try{
				myPicture = ImageIO.read(new File("logo.png"));
				titulo = new JLabel(new ImageIcon(myPicture));
			}catch(IOException e){e.printStackTrace();}
			
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridwidth=4;
			gbc.insets = new Insets(8,8,8,8);
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			
			panel.add(titulo,gbc);
			
			//*información encima de textArea
			JLabel introducido= new JLabel("       Introduced");
			introducido.setForeground(Color.white);
			introducido.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,12));
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridwidth=1;
			panel.add(introducido,gbc);
			JLabel bien_p= new JLabel("|Good position");
			bien_p.setForeground(Color.white);
			bien_p.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,12));
			gbc.gridx=1;
			gbc.gridy=1;
			gbc.gridwidth=1;
			panel.add(bien_p,gbc);
			JLabel mal_p= new JLabel("|Bad position");
			mal_p.setForeground(Color.white);
			mal_p.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,12));
			gbc.gridx=2;
			gbc.gridy=1;
			gbc.gridwidth=1;
			panel.add(mal_p,gbc);
			
			//si el parametero recibido por el constructor no es null, significa que viene del hisotorial.
			if(idColumna!=null){
				imprimirResultado=mostrarTiradas();
				area = new JTextArea(mostrarTiradas());
				String splitCadTiradas[]=imprimirResultado.split("\n");
				juego=new Partida(bases.getRandom(idColumna));
			
			
				for(int i = 0 ;i<splitCadTiradas.length;i++){
					if(!(splitCadTiradas[i].isEmpty())){
						contLines++;
					}
				}
				contador = new JTextArea(String.valueOf(10-contLines));
				numero_partida=10-contLines;
			
			}else { //en caso contrario significa un partida nueva
				area = new JTextArea(imprimirResultado);
				contador = new JTextArea("10");
				numero_partida=10;
				juego= new Partida();
				bases.insertarRandoms(identificador,juego.getNumAleato());
			}
			area.setPreferredSize( new Dimension( 400, 340 ) );
			area.setBackground(new Color(193,215,245));
			area.setBorder(BorderFactory.createMatteBorder( 6, 0, 0, 0,new Color(184,217,38)));
			area.setBorder(BorderFactory.createCompoundBorder(area.getBorder(), BorderFactory.createEmptyBorder(0,60, 0, 0)));
			area.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,13));
			area.setForeground(new Color(49,166,234));//cambiamos el color al text de area
			area.setEditable(false);
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.gridwidth=4;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(area,gbc);
		
			
			display = new JTextField("");
			display.setPreferredSize( new Dimension( 300, 40 ) );
			display.setBackground(new Color(193,215,245));
			display.setBorder(BorderFactory.createMatteBorder( 1, 1, 1, 1,new Color(255,215,0)));
			display.setBorder(BorderFactory.createCompoundBorder(display.getBorder(), BorderFactory.createEmptyBorder(2,5, 2, 2)));
			display.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,16));
			display.setForeground(new Color(49,166,234));//cambiamos el color al text de area
			gbc.gridx=0;
			gbc.gridy=3;
			gbc.gridwidth=3;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(display,gbc);
			
			//contador de intentos
			contador.setPreferredSize( new Dimension( 10, 40 ) );
			contador.setBackground(new Color(193,215,245));
			contador.setForeground(new Color(49,166,234));
			contador.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			contador.setEditable(false);
			contador.setBorder(BorderFactory.createCompoundBorder(contador.getBorder(), BorderFactory.createEmptyBorder(8,8 , 8, 8)));
			gbc.gridx=3;
			gbc.gridy=3;
			gbc.gridwidth=1;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(contador,gbc);
			
			
			play = new JButton("Play");
			play.setBackground(new Color(184,217,38));
			play.setForeground(Color.white);
			play.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			play.setPreferredSize( new Dimension( 300, 35 ) );
			gbc.gridx=0;
			gbc.gridy=4;
			gbc.gridwidth=3;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(play,gbc);
			
			delete = new JButton("Delete");
			delete.setBackground(new Color(255,0,0));
			delete.setForeground(Color.white);
			delete.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			gbc.gridx=3;
			gbc.gridy=4;
			gbc.gridwidth=1;
			panel.add(delete,gbc);
			
			historial = new JButton("Historial");
			historial.setBackground(new Color(60,88,125));
			historial.setForeground(Color.white);
			historial.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			gbc.gridx=0;
			gbc.gridy=5;
			gbc.gridwidth=2;
			panel.add(historial,gbc);
			
			nuevoJuego = new JButton("New game");
			nuevoJuego.setForeground(Color.white);
			nuevoJuego.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			nuevoJuego.setBackground(new Color(245,202,30));
			gbc.gridx=2;
			gbc.gridy=5;
			gbc.gridwidth=2;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(nuevoJuego,gbc);
		
			display.setEditable(true);
			display.requestFocusInWindow();
			nuevoJuego.addActionListener(handler);
			play.addActionListener(handler);
			delete.addActionListener(handler);
			historial.addActionListener(handler);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);	
	}
	
	
	
	
	//classe privada que implementa ActionListener
	private class ClassInter implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			
			if(obj==nuevoJuego){
				bases.cerrarConexion();
				new Principal(null);
				frame.dispose();
					
			}
					
			if(obj==delete){
				display.setText("");
			}
			if(obj==play){
				boolean seguir = true;
				int restantes=10-contLines;
				boolean verdadero = true;
				String num ="";
				String cadBD="";
				while(numero_partida>=1 && seguir && verdadero ){	
				
					if(estaFiltrado()==true){
							num=display.getText();
							juego.anadirTirada(num);
							boolean kk=true; //muestra Tabla de 01
							imprimirResultado+=juego.toString(kk);
							area.setText(imprimirResultado);
							//guardamos las tiradas en bd
							cadBD=juego.toString(kk).replace("\n","");
							setElementos(cadBD);
							
							//contador de partidas restantes
							restantes=numero_partida-1;
							contador.setText(Integer.toString(restantes));
														
							if(juego.isComplete(num)){ // método isComplete devuelve un booleando 
									JOptionPane.showMessageDialog(null, "You win :)");	
									seguir=false;
							}
							if(numero_partida==1 && ! juego.isComplete(num)){
									JOptionPane.showMessageDialog(null, "You lost :(");	
									seguir=false;
							}
							numero_partida--;
							verdadero=false;
					}
					if(estaFiltrado()==false){
							JOptionPane.showMessageDialog(null, "Introduce  a number of 5 digits");
							verdadero=false;
							break;
					}
					
					
			}
		
		}
		if(obj==historial){
				new Historial();
				frame.dispose();
		}
	}
}

//conseguir elementos de  toString por separado y guardarlos en la BD.
	public  void  setElementos(String resu){
		String dividir[]=resu.split("\t");
		for(int i =0; i<dividir.length;i++){
			if(i==0){
				numeroInt =dividir[i];
			}
			if(i==1){
				bienp=dividir[i];
			}
			if(i==2){
				 malp =dividir[i];
			}
			if(i==3){
				tabla012 =dividir[i];
			}
		}
		bases.setTiradas(identificador,numeroInt,bienp,malp,tabla012);
	
	}

//método que muestra tiradas de la partida seleccionada
	public String  mostrarTiradas(){
			ArrayList<String> list =bases.leerTiradasAdvanced(idColumna);
			String cad="";
			for(String lis:list){
				cad+=lis+"\n";
				
			} 
					return cad;
		
		}
	
//clase privada dirivada de adaptadores
	private class BWinListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			//cerramos la conexion una vez recibido el evento de cerrar ventana
			bases.cerrarConexion();	
			System.exit(0);
			
		}
	}

	//método que la filtra la entrada por el teclado.
	public  boolean estaFiltrado(){
		boolean filtro=false;
		String cadAfiltrar = display.getText();
		String fullWord="";
		char c ='0';
		for(int i = 0; i<cadAfiltrar.length(); i++){
			if(cadAfiltrar.charAt(i)>=48 && cadAfiltrar.charAt(i)<=57 && !(cadAfiltrar.contains("."))){
				c=cadAfiltrar.charAt(i);
				fullWord+=c;
				filtro=true;
			}
		}	
		if(fullWord.length()!=5) filtro=false;
		if(fullWord==null) filtro=false;
		
		return filtro;
	}
}


