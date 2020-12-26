import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;

 public class Historial{
	private JFrame frame ;
	private JPanel panel;
	private JLabel titulo;
	private JButton play;
	private JTextField display;
	private JTextField  display2;
	private JTextArea area;
	private JButton nuevoJuego;
	private String numero="12345";
	DefaultTableModel modelo=null;
	
	Bd1 bases3 = new Bd1();
	public Historial(){
	
			frame = new JFrame("MasterMind");
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			panel = new JPanel(new GridBagLayout());
			panel.setBackground(new Color(49,166,234));
			frame.setSize(500,700);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			GridBagConstraints gbc = new GridBagConstraints();
			BufferedImage myPicture=null;
			JLabel titulo  = null;
			try{
				myPicture = ImageIO.read(new File("logo.png"));
				titulo = new JLabel(new ImageIcon(myPicture));
			}catch(IOException e){e.printStackTrace();}
			
			
			//titulo = new JLabel("MasterMind");
			//titulo.setFont(new Font("Helvetica",Font.PLAIN,25));
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridwidth=4;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.insets = new Insets(10,10,15,10);
			panel.add(titulo,gbc);
			   // Data to be displayed in the JTable 
			//String[][] datos = new String[nomColumnas.length][nomColumnas.length];
			 modelo = new DefaultTableModel();
			// Column Names 
			//String[]  nomColumnas = { "Number introduced", "Bad position", "Good position","Table012" }; 
			Object[]  nomColumnas = { "Number", "Date", "Status" }; 
			//TableColumn columna = tabla.getColumn;
			//columna .setPreferredWidth(320);
			//String[][] datos = new String[3][3];
			//String datos[]=bases2.leerPartidas().split(" ");
			 //modelo= new DefaultTableModel( nomColumnas );
			 modelo.setColumnIdentifiers(nomColumnas);
			
			//seguir este tutorial
			//https://www.yoelprogramador.com/ajustar-el-tamano-de-las-columnas-de-un-jtable-en-java/
			
			JTable tabla = new JTable();
			tabla.setModel(modelo);
			//String partidas[]=bases2.leerPartidas().split(" ");
			//model.addRow(partidas);
			Object[] row = new Object[3];
			
			//for(int i = 0 ; i<datos.length;i++){
				//row[i]=datos[i];
			//	System.out.println(datos[i]);
			//}
			row[0]="hola1";
			row[1]="hola2";
			row[2]="hola3";
			//row[3]="hola";
			bases3.leerPartidas();
			modelo.addRow(row);
			tabla.setBackground(new Color(193,215,245));
			tabla.setForeground(new Color(49,166,234));
			tabla.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,12));
			

	
		//	DefaultTableModel dtm= new DefaultTableModel(datos, columnas);
			
			JScrollPane scrol = new JScrollPane(tabla);
			scrol.setBackground(new Color(193,215,245));
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridwidth=4;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.insets = new Insets(10,10,15,10);
			panel.add(scrol,gbc);
			
			//panel.add(scrol);
			
			/*
			area = new JTextArea("texto");
			area.setPreferredSize( new Dimension( 400, 300 ) );
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridwidth=2;
			gbc.fill=GridBagConstraints.CENTER;
			panel.add(area,gbc);
			
			
			display = new JTextField("01234");
			display.setPreferredSize( new Dimension( 400, 30 ) );
			gbc.gridx=0;
			gbc.gridy=3;
			gbc.gridwidth=2;
			gbc.fill=GridBagConstraints.REMAINDER;
			panel.add(display,gbc);
			
			
			*/
			play = new JButton("play");
			play.setBackground(new Color(50,205,50));
			play.setForeground(Color.white);
			play.setFont(new Font("Helvetica",Font.PLAIN,18));
			play.setPreferredSize( new Dimension( 70, 30 ) );
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.gridwidth=1;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			panel.add(play,gbc);
			
			JButton delete = new JButton("delete");
			delete.setBackground(new Color(255,0,0));
			delete.setForeground(Color.white);
			delete.setFont(new Font("Helvetica",Font.PLAIN,18));
			gbc.gridx=2;
			gbc.gridy=2;
			gbc.gridwidth=1;
			panel.add(delete,gbc);
		
			nuevoJuego = new JButton("New game");
			nuevoJuego.setForeground(Color.white);
			nuevoJuego.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			nuevoJuego.setBackground(new Color(245,202,30));
			gbc.gridx=3;
			gbc.gridy=2;
			gbc.gridwidth=1;
			panel.add(nuevoJuego,gbc);
			
			
			
			
			//nuevoJuego.addActionListener(new ClassInter());
			//play.addActionListener(new ClassInter());
			//display2.addActionListener(new ClassInter());
			
			frame.add(panel);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		
		
	}

	//rebirir datos de la tabla 
	//classe privada
	private class ClassInter implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
		
			if(obj==nuevoJuego){
				new Principal();
				frame.dispose();
					
			}
		/*	if(obj==play){
			
				numero=display.getText();
				area.setText(new MasterMindGui().getNumero()); //new PartidaAFichero().toString()
			
			}*/
		}
	}
	public String getNumero(){
			return numero;
	}
	
	
	public static void main(String[] args){
		new Historial();
		Bd1 bd = new Bd1();
		bd.leerTiradas();
		System.out.println("Hola");
	}
		
}



