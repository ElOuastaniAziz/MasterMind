import java.sql.*;
import java.util.*;
class BasesDatos{
	//private Connection coneccion;
	Connection con=null;
	Statement st = null;
	Statement st2 = null;
	ResultSet rset = null;
	ResultSet rset2 = null;
	String url ="jdbc:mysql://localhost:3306";
	String user = "root";
	String pwd ="";
	String crearBd ="create database if not exists aelouastani";
	String baseDatos ="use aelouastani";
	String crearTablePartida= "create table if not exists partida("+"Id int AUTO_INCREMENT not null primary key,"+"Date date,"+"Status varchar(100))";
	String crearTableTirada= "create table if not exists tirada (idtirada int not null,numeroIntroducido char(10),bienP char(10),malP char(10),tabla char(10))";
	String crearTableRandom = "Create table if not exists randoms( idRandom int,random char(5))";
	String selectTiradas ="select * from tirada";
	String selectPartidas = "select id,Date,Status from partida  inner join tirada on tirada.idtirada=partida.id;";
	String selectnRandom = "select * from randoms";
	String insertInPartida = "insert into partida values(NULL,now(),'finished?')";
	//String crearVista ="create or replace view partidas(id, Date,status) as select id,date,Status from partida inner join tirada on tirada.idtirada=partida.id;";
	String insertInTirada;
	String insertarInRandom;
	String getRandomId;
	String selectVista="select id,Date,Status from partidas  inner join tirada on tirada.idtirada=0;";
	String selectPartidas2 = "select * from Partida";
	


	//método que devuelve tipo de exceptiones
	public void mostraSQLException(SQLException ex) {
		ex.printStackTrace(System.err);
		System.err.println("SQLState: " + ex.getSQLState());
		System.err.println("Error Code: " + ex.getErrorCode());
		System.err.println("Message: " + ex.getMessage());
		Throwable t=ex.getCause();
		while(t!=null) {
			System.out.println("Cause: " + t);
			t=t.getCause();
		}
	}
	
	
	public void conectarse(){
			
		try{
			//carga del controlador
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//crea conexion
			con=DriverManager.getConnection(url,user,pwd);//lod datos del usuario
			//crea objeto  para ejecutar sentencias SQL
			st=con.createStatement();
			//podemos llamar  al metodo insertarDatos para insertar 
			insertaDatos(insertInPartida);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
				mostraSQLException(e);
		}
	}
	
	
	
	//método que creaPartida
	public void crearPartidaBd(){
		try{
			st.executeUpdate(insertInPartida);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//método getIdPartida
	public int getIdPartida(){
	
		int  numId =0;
		try{
			//PARA LEER DE UNA BASE DE DATOS.
			st.executeUpdate(baseDatos);
			rset =st.executeQuery(selectPartidas2);
				while(rset.next()){
				 numId = rset.getInt("id");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return numId;
	}

	//método getIdTirada
	public int getIdTirada(){
	
		int  numId =0;
		try{
			//PARA LEER DE UNA BASE DE DATOS.
			st.executeUpdate(baseDatos);
			
			rset =st.executeQuery(selectTiradas);
				while(rset.next()){
				 numId = rset.getInt("idpartida");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return numId;
	}
	
	
	//método que recibe tiradas
	public void  setTiradas(int idPartida ,String  numeroInt,String   bienp,String  malp,String tabla012){
						
		try{
					insertInTirada = "insert into tirada values('"+idPartida+"','"+numeroInt+"','"+bienp+"','"+ malp+"','"+tabla012+"')";
					st.executeUpdate(insertInTirada);
				
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void insertarRandoms(int idpartida,String random){
		try{
					insertarInRandom= "insert into randoms values('"+idpartida+"','"+random+"')";
					st.executeUpdate(insertarInRandom);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	//insertar datos en tablas
	public void insertaDatos(String cadenaTirada){
		try{
			
			st.executeUpdate(crearBd);
			st.executeUpdate(baseDatos);
			st.executeUpdate(crearTablePartida);
			st.executeUpdate(crearTableTirada);
			st.executeUpdate(crearTableRandom);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//método que lee partidas
	public ResultSet leerPartidas(){
			
		try{
			st.executeUpdate(baseDatos);
			rset =st.executeQuery(selectPartidas2);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rset;
		
	}
	//método que selecciona numero random generado por cada partida 
	public String  getRandom(String idpartida){
		String  num=null;
		try{
			//PARA LEER DE UNA BASE DE DATOS.
			st.executeUpdate(baseDatos);
			
			//PARA LEER DE UNA BASE DE DATOS.
			st.executeUpdate(baseDatos);
			selectnRandom = "select random from randoms where idRandom='"+idpartida+"'";
			rset =st.executeQuery(selectnRandom);
				while(rset.next()){
				 num = rset.getString("random");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return num;	
	}

	//método que lee tiradas nivel principiante
	public ArrayList leerTiradasBeginner(String idPartidabd){
		ArrayList<String> listaTiradas = new ArrayList<String>();
		String resultado ="";
		String numInt ="";
		String bp="";
		String mp ="";
		String tabla ="";
		try{
			st.executeUpdate(baseDatos);
			selectTiradas = "select idtirada,numeroIntroducido,bienP,malP,tabla from tirada where idtirada='"+idPartidabd+"'";
			rset =st.executeQuery(selectTiradas);
				while(rset.next()){
				 numInt = rset.getString("numeroIntroducido");
				bp = rset.getString("bienP");
				mp = rset.getString("malP");
				tabla = rset.getString("tabla");
				listaTiradas.add(resultado="\n"+numInt+"\t"+bp+"\t"+mp+"\t"+tabla);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listaTiradas;
		
	}

	//método que lee tiradas nivel Avanzado
	public ArrayList leerTiradasAdvanced(String idPartidabd){
		ArrayList<String> listaTiradas = new ArrayList<String>();
		String resultado ="";
		String numInt ="";
		String bp="";
		String mp ="";
		String tabla ="";
		try{
			st.executeUpdate(baseDatos);
			selectTiradas = "select idtirada,numeroIntroducido,bienP,malP,tabla from tirada where idtirada='"+idPartidabd+"'";
			rset =st.executeQuery(selectTiradas);
				while(rset.next()){
				 numInt = rset.getString("numeroIntroducido");
				bp = rset.getString("bienP");
				mp = rset.getString("malP");
				listaTiradas.add(resultado="\n"+numInt+"\t"+bp+"\t"+mp);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listaTiradas;
		
	}

	//método para cerrar conexión de BD
	public void cerrarConexion(){
		try{
			if(con!=null)con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
	

