package ec.edu.ups.ppw.biblioteca.dao;

public class UsuarioDAO {
	
	public static boolean validar(String username, String password) {
		return (username.equals("admin") && password.equals("admin"));
	}

}
