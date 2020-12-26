import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Principal{
	JFrame frame;
	JPanel panel;
	JButton principiante;
	JButton avanzado;
	String idColumna;

	public Principal(String idCol){
			
			idColumna=idCol;
			frame = new JFrame("MasterMind");
			frame.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel = new JPanel(new GridBagLayout());
			panel.setBackground(new Color(49,166,234));
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.setSize(400,350);
			panel.setPreferredSize( new Dimension( 400, 350 ) );
			GridBagConstraints gbc = new GridBagConstraints();
			 gbc.insets = new Insets(5,5,5,5); 
			BufferedImage myPicture=null;
			JLabel picLabel = null;
			
			//el logotipo de masterMind
			try{
				myPicture = ImageIO.read(new File("logo.png"));
				picLabel = new JLabel(new ImageIcon(myPicture));
			}catch(IOException e){e.printStackTrace();}
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridwidth=1;
			panel.add(picLabel,gbc);
			
			principiante =new JButton("Beginner");
			principiante.setBackground(new Color(245,202,30));
			principiante.setForeground(Color.white);
			principiante.setFocusable(false);
			principiante.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,20));
			principiante.setPreferredSize( new Dimension( 200, 40 ) );
			gbc.gridx=0;
			gbc.gridy=3;
			gbc.gridwidth=1;
			panel.add(principiante,gbc);
			
			avanzado=new JButton("Advanced");
			avanzado.setBackground(new Color(184,217,38));
			avanzado.setForeground(Color.white);
			avanzado.setFocusable(false);//quitar el border que sale interior
			avanzado.setFont(new Font("Tahoma",Font.PLAIN+Font.BOLD,20));
			avanzado.setPreferredSize( new Dimension( 200, 40 ) );
			gbc.gridx=0;
			gbc.gridy=4;
			gbc.gridwidth=1;
			panel.add(avanzado,gbc);
			
			principiante.addActionListener(new Eventos());
			avanzado.addActionListener(new Eventos());
			frame.add(panel);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
	}
	
	//clase interna que implementa ActionListener
	private class Eventos implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			
			if(obj==principiante){	
					new Beginner(idColumna);
					System.out.println(" PRINCIPAL"+idColumna);		
					frame.dispose();
			}
			if(obj==avanzado){
					new Advanced(idColumna);
					System.out.println(" PRINCIPAL"+idColumna);	
					frame.dispose();
					
			}
			
		}
		
	}
	
	//clase privada dirivada de adaptadores
	private class BWinListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
				System.exit(0);
		
		}
	}
	public static void main(String[] args){
		new Principal(null);
		
	}
	
}
