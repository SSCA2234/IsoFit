package legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;

import persistencia.Agente;

public class Usuario {
	
	public String mLogin;
    public String mPassword;
    public boolean esEspecialista;
	
	
	//Constructor para la creaci n de un objeto Usuario vacio
	public Usuario(String login, String password, boolean esEspecialista) {
        this.mLogin = login;
        this.mPassword = password;
        this.esEspecialista = esEspecialista;
    }
	
	//Constructor para la creación de un Usuario
	public Usuario(String login, String password) {
        this.mLogin = login;
        this.mPassword = password;
        this.esEspecialista = false; // Por defecto, no es especialista
    }
	
	//Selección de un usuario de la base de datos a partir del login y el password
	public static Usuario read(String login, String password) throws Exception{
		String l,g;
		Usuario u = null;
		Vector<Object> aux = null;

		String SQL_Consulta = "SELECT email, password FROM login WHERE email = '"+login+"' AND password = '"+password+"'";
		aux = Agente.getAgente().select(SQL_Consulta);
		Vector<Object> vectoradevolver=new Vector<Object>();

		Vector aux1 = new Vector <Object>();
		if (aux.size() == 1){
			aux1 = (Vector<Object>) aux.elementAt(0);
			u = new Usuario((String) aux1.elementAt(0), (String) aux1.elementAt(1));
		}
		return u;
	}
	
	//Inserción de un nuevo usuario en la base de datos
	public int insert() throws Exception {
        int res = Agente.getAgente().insert("INSERT INTO login VALUES('" + mLogin + "','" + mPassword + "', " + (esEspecialista ? 1 : 0) + ")");
        return res;
    }
	
	/*Agregar una nueva consulta a nuestra base de datos a traves de una consulta y un vector de objetos*/
	public int agregarConsulta(String consulta, String respuesta, String email) {
	    try {
	        // Lógica para agregar la consulta a la base de datos, similar a tu método original
	        String SQL_Insercion = "INSERT INTO consultas_deportivas (consulta, respuesta, email) VALUES (?, ?, ?)";
	        Vector<Object> params = new Vector<>();
	        params.add(consulta);
	        params.add(respuesta);
	        params.add(email);
	        return Agente.getAgente().insert2(SQL_Insercion, params);
	    } catch (Exception e) {
	        e.printStackTrace();//Si no es posible realizar dicho método, que imprima donde y cuales son los distintos errores
	        return -1; //El valor -1, al ser negativo, nos indica que es un error
	    }
	}
	/*Agrega una respuesta  a nuestra base de datos, mediante un vector de objetos y una consulta de my sql*/
	public int agregarRespuesta(String respuesta, String consulta,String email) throws Exception {

	        try {
	        String SQL_Actualizacion = "UPDATE consultas_deportivas SET Consulta = ? WHERE Respuesta = ? AND email = ?";
	        Vector<Object> params = new Vector<>();
	        params.add(consulta);
	        params.add(respuesta);
	        params.add(email);

	        return Agente.getAgente().update2(SQL_Actualizacion, params);
	        }catch(Exception e){
	        e.printStackTrace();//Si no es posible realizar dicho método, que imprima donde y cuales son los distintos errores
	        return -1;//El valor -1, al ser negativo, nos indica que es un error
	        }
	    }
	

	/*Obtiene las disitintas consultas de nuestra base de datose, recorriendo toda la base de datos*/
	public List<String> getConsultas() throws Exception {
	    String SQL_Consulta = "SELECT consulta FROM consultas_deportivas";
	    Vector<Object> v = Agente.getAgente().selectConsultas(SQL_Consulta);
	    List<String> lista = new ArrayList<>();

	    for (Object consultaObj : v) {
	        Vector<Object> consulta = (Vector<Object>) consultaObj;
	        String pregunta = (String) consulta.elementAt(0);
	        lista.add(pregunta);
	    }
	    return lista;
	}

     /*Nos indica si es o no un especialista, leyendo la columna correspondiente donde se alamcenan dichos valores*/
    public boolean isEspecialista(String login) throws Exception {
        String SQL_Consulta = "SELECT es_especialista FROM login WHERE email = ?";
        Vector<Object> params = new Vector<>();
        params.add(login);

        Vector<Object> resultado = Agente.getAgente().select2(SQL_Consulta, params);
        /*Si el ressultado es 1, entonces da lugar a que es especialista*/
        if (resultado.size() == 1) {
            Vector<Object> fila = (Vector<Object>) resultado.elementAt(0);
            Object esEspecialistaObj = fila.elementAt(0);
            if (esEspecialistaObj instanceof Integer) {
                Integer esEspecialista = (Integer) esEspecialistaObj;
                return esEspecialista == 1;
            }
        }

        return false;
    }
    
    /*Obtendgo las disitntas consultas para mostrarlas al usurio y mueda responder las consultas realizadas por los usuarios vip*/
    public List<String> obtenerCuestionesSinRespuesta2() throws Exception {
        List<String> cuestionesSinRespuesta = new ArrayList<>();

        List<String> consultas = this.getConsultas();
        return consultas;
    }

    /*obtengo el mail del usuario logeado*/
    public String getEmail() {
		
		return this.mLogin;
	}


}
