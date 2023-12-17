package legacy;

public class GestorUsuario {
	/*Comprueba si el usuario existe en nuestra base de datos*/
	public static boolean autenticar(String login, String password) throws Exception{
		boolean autenticado = false;
		
		if(Usuario.read(login, password) != null)
			autenticado = true;
		return autenticado;
	}
	/*Crea un nuevo usuario*/
	public static boolean nuevoUsuario(String login, String password) throws Exception{
		boolean insertado = false;
		
		Usuario u = new Usuario(login, password);
		if(u.insert() ==1)
			insertado = true;
		return insertado;		
	}

}
