import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

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
	JTable tabla =null;
	
	
	ResultSet rset= null;
	ResultSetMetaData rsm = null;
	BasesDatos bd=null;

	public Historial(){
			bd = new BasesDatos();
			bd.conectarse();
			ResultSet rset=bd.leerPartidas();
			frame = new JFrame("MasterMind");
			frame.setLayout(new FlowLayout(FlowLayout.LEFT));
			panel = new JPanel(new GridBagLayout());
			panel.setBackground(new Color(49,166,234));
			frame.setSize(500,700);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.insets = new Insets(10,10,15,10);
			panel.add(titulo,gbc);
			int numCol=0;
				
			modelo = new DefaultTableModel();
			tabla = new JTable();
			//System.out.println(bd2.getIdTirada());
			try{
				//System.out.println(bd.getIdTirada());	
				rsm=rset.getMetaData();
				 numCol =rsm.getColumnCount();
				 for(int i = 1; i<=numCol;i++){
					modelo.addColumn(rsm.getColumnLabel(i));
				}
				while(rset.next()){
						String fila[]=new String[numCol];
						for(int j=0;j<numCol;j++){
							
								
								fila[j]=rset.getString(j+1);
							
						}
						modelo.addRow(fila);
					
				}
				tabla.setModel(modelo);
			
			}catch(SQLException e){
				bd.mostraSQLException(e);
			}
		
			tabla.setBackground(new Color(193,215,245));
			tabla.setForeground(new Color(49,166,234));
			tabla.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,12));
			
			JScrollPane scrol = new JScrollPane(tabla);
			scrol.setBackground(new Color(193,215,245));
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridwidth=4;
			gbc.fill=GridBagConstraints.REMAINDER;
			gbc.insets = new Insets(10,10,15,10);
			panel.add(scrol,gbc);
	
			nuevoJuego = new JButton("New game");
			nuevoJuego.setForeground(Color.white);
			nuevoJuego.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,18));
			nuevoJuego.setBackground(new Color(245,202,30));
			gbc.gridx=3;
			gbc.gridy=2;
			gbc.gridwidth=1;
			panel.add(nuevoJuego,gbc);
	
			nuevoJuego.addActionListener(new ClassInter());
			tabla.getSelectionModel().addListSelectionListener(new ClassInter());
			frame.add(panel);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
	}

	//classe privada
	private class ClassInter implements ActionListener,ListSelectionListener{
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
		
			if(obj==nuevoJuego){
				bd.cerrarConexion();
				new Principal(null);
				frame.dispose();	
			}
		}
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {//This line prevents double events
				String idColumna = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
				new Principal(idColumna);
				System.out.println("HISTOR PRIN0 "+idColumna);
				bd.cerrarConexion();
				frame.dispose();
				
			}	 
        }
	}

	//clase privada dirivada de adaptadores
	private class BWinListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			//cerramos la conexion
			bd.cerrarConexion();	
			System.exit(0);
			
		}
	}	
}





