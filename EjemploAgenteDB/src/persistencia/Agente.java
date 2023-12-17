package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Agente {
	// instancia del agente
	protected static Agente mInstancia = null;
	// Conexion con la base de datos
	protected static Connection mBD;
	// Identificador ODBC de la base de datos
	private static String url = "jdbc:mysql://localhost:3306/usuarios?user=root&password=pass";
	// Driven para conectar con bases de datos MySQL
	private static String driver = "com.mysql.cj.jdbc.Driver";

	// Constructor
	private Agente() throws Exception {
		conectar();

	}

	/*Asignamos el cosntructor*/
	public static Agente getAgente() throws Exception {
		if (mInstancia == null) {
			mInstancia = new Agente();
		}
		return mInstancia;
	}

	// Metodo para realizar la conexion a la base de datos
	private void conectar() throws Exception {
		Class.forName(driver);
		mBD = DriverManager.getConnection(url);
	}

	// Metodo para desconectar de la base de datos
	public void desconectar() throws Exception {
		mBD.close();
	}

	// Metodo para realizar una insercion en la base de datos
	public int insert(String SQL) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement(SQL);
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	// Metodo para realizar una eliminacion en la base de datos
	public int delete(String SQL) throws SQLException, Exception {
		PreparedStatement stmt = mBD.prepareStatement(SQL);
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	// Metodo para realizar una eliminacion en la base de datos
	public int update(String SQL) throws SQLException, Exception {
		conectar();
		PreparedStatement stmt = mBD.prepareStatement(SQL);
		int res = stmt.executeUpdate();
		stmt.close();
		desconectar();
		return res;
	}

	/*Método ppara seleccionar información en la base de datos, devolviendo un vector*/
	public Vector<Object> select(String SQL) throws SQLException, Exception {

		/*
		 * Metodo para realizar una busqueda o seleccion de informacion enla base de
		 * datos El m todo select develve un vector de vectores, donde cada uno de los
		 * vectores que contiene el vector principal representa los registros que se
		 * recuperan de la base de datos.
		 */

		Vector<Object> vectoradevolver = new Vector<Object>();
		conectar();
		Statement stmt = mBD.createStatement();
		ResultSet res = stmt.executeQuery(SQL);
		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			v.add(res.getObject(1));
			v.add(res.getObject(2));
			vectoradevolver.add(v);
		}
		stmt.close();
		desconectar();
		return vectoradevolver;

	}
	
	/*Método ppara seleccionar la consulta seleccionadoa mediante el selector en la base de datos, devolviendo un vector*/
	public Vector<Object> selectConsultas(String SQL) throws SQLException, Exception {
		/*
		 * Metodo para realizar una busqueda o seleccion de informacion enla base de
		 * datos El m todo select develve un vector de vectores, donde cada uno de los
		 * vectores que contiene el vector principal representa los registros que se
		 * recuperan de la base de datos.
		 */

		Vector<Object> vectoradevolver = new Vector<Object>();
		conectar();
		Statement stmt = mBD.createStatement();
		ResultSet res = stmt.executeQuery(SQL);
		while (res.next()) {
			Vector<Object> v = new Vector<Object>();
			v.add(res.getObject(1));
			vectoradevolver.add(v);
		}
		stmt.close();
		desconectar();
		return vectoradevolver;

	}
	
	/*Método ppara seleccionar información en la base de datos, devolviendo un vector*/
	public Vector<Object> select2(String SQL, Vector<Object> params) throws SQLException, Exception {
	    Vector<Object> vectoradevolver = new Vector<Object>();
	    conectar();
	    PreparedStatement pstmt = mBD.prepareStatement(SQL);

	    for (int i = 0; i < params.size(); i++) {
	        pstmt.setObject(i + 1, params.elementAt(i));
	    }

	    ResultSet res = pstmt.executeQuery();

	    while (res.next()) {
	        Vector<Object> v = new Vector<Object>();
	        for (int i = 1; i <= res.getMetaData().getColumnCount(); i++) {
	            Object valor = res.getObject(i);
	            if (valor instanceof Boolean) {
	                // Convertir Boolean a Integer
	                v.add(((Boolean) valor) ? 1 : 0);
	            } else {
	                // Mantener otros tipos de datos sin cambios
	                v.add(valor);
	            }
	        }
	        vectoradevolver.add(v);
	    }

	    pstmt.close();
	    desconectar();
	    return vectoradevolver;
	}
	
	// Metodo para realizar una insercion en la base de datos, mediante un vector de objetos, para mejorar su preción y sencillez en el código
	public int insert2(String SQL, Vector<Object> params) throws SQLException, Exception {
	    conectar();
	    PreparedStatement stmt = mBD.prepareStatement(SQL);

	    // Configurar los parámetros de la consulta preparada
	    for (int i = 0; i < params.size(); i++) {
	        stmt.setObject(i + 1, params.elementAt(i));
	    }

	    int res = stmt.executeUpdate();
	    stmt.close();
	    desconectar();
	    return res;
	}
	
	// Metodo para realizar una actualización en la base de datos, mediante un vector de objetos, para mejorar su preción y sencillez en el código
	public int update2(String SQL, Vector<Object> params) throws SQLException, Exception {
	    conectar();
	    PreparedStatement stmt = mBD.prepareStatement(SQL);

	    // Configurar los parámetros de la consulta preparada
	    for (int i = 0; i < params.size(); i++) {
	        stmt.setObject(i + 1, params.elementAt(i));
	    }

	    int res = stmt.executeUpdate();
	    stmt.close();
	    desconectar();
	    return res;
	}

}
